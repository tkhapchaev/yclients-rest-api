package ru.tkhapchaev.services.yclients.services;

import ru.tkhapchaev.entities.Activity;
import ru.tkhapchaev.entities.Client;
import ru.tkhapchaev.services.client.ClientService;
import ru.tkhapchaev.services.activity.ActivityService;
import ru.tkhapchaev.services.yclients.api.YclientsApiWrapper;
import ru.tkhapchaev.services.yclients.api.YclientsResponseParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
@EnableScheduling
public class YclientsService {
    private final ClientService clientService;

    private final ActivityService activityService;

    @Value("${yclients.company-id}")
    private long companyId;

    @Value("${yclients.bearer-authentication}")
    private String bearerAuthentication;

    @Value("${yclients.user-token}")
    private String userToken;

    @Value("${yclients.content-type-header}")
    private String contentTypeHeader;

    @Value("${yclients.accept-header}")
    private String acceptHeader;

    @Value("${yclients.filter.is-enabled}")
    private boolean isFilterEnabled;

    @Value("${yclients.filter.activity-id}")
    private long activityId;

    @Value("${yclients.filter.activity-title}")
    private String activityTitle;

    @Autowired
    public YclientsService(ClientService clientService, ActivityService activityService) {
        this.clientService = clientService;
        this.activityService = activityService;
    }

    @Scheduled(fixedDelay = 5000, initialDelay = 10000)
    public void checkForUpdates() throws IOException {
        YclientsApiWrapper apiWrapper = new YclientsApiWrapper(
                companyId,
                bearerAuthentication,
                userToken,
                contentTypeHeader,
                acceptHeader);

        YclientsResponseParser responseParser = new YclientsResponseParser(isFilterEnabled, activityId, activityTitle);

        LocalDateTime localDateTime = LocalDateTime.now();
        Map<Long, List<Client>> clients = new HashMap<>();
        List<Activity> activities;

        activities = responseParser.getActivities(apiWrapper.getListOfActivities(localDateTime));

        for (Activity activity : activities) {
            List<Client> participants = responseParser.getClientsEnrolledInActivity(
                    apiWrapper.getListOfRecords(),
                    activity.getYclientsId());

            clients.put(activity.getYclientsId(), participants);
        }

        for (Activity activity : activities) {
            if (!activityService.existsByYClientsId(activity.getYclientsId())) {
                activityService.create(activity);
            }

            if (activityService.existsByYClientsId(activity.getYclientsId())) {
                Activity activityInDatabase = activityService.findByYclientsId(activity.getYclientsId());

                if (!Objects.equals(activityInDatabase, activity)) {
                    activityService.update(activity, activityInDatabase.getId());
                }
            }
        }

        for (Activity activity : activities) {
            List<Client> participants = clients.get(activity.getYclientsId());
            List<Client> participantsInDatabase = clientService.findByYclientsActivityId(activity.getYclientsId());

            for (Client participantInDatabase : participantsInDatabase) {
                if (!participants.contains(participantInDatabase)) {
                    clientService.delete(participantInDatabase.getId());
                }
            }

            for (Client participant : participants) {
                if (!clientService.existsByYClientsId(participant.getYclientsId())) {
                    clientService.create(participant);
                }
            }
        }
    }
}