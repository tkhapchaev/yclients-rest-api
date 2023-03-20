package ru.tkhapchaev.services.yclients.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.tkhapchaev.entities.Activity;
import ru.tkhapchaev.entities.Client;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class YclientsResponseParser {
    private boolean isFilterEnabled;
    private long activityId;
    private String activityTitle;

    public List<Activity> getActivities(String jsonResponse) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        JsonNode data = objectMapper.readTree(jsonResponse).get("data");
        ArrayList<Activity> activities = new ArrayList<>();

        for (JsonNode event : data) {
            JsonNode eventId = event.get("id"), labels = event.get("labels");

            for (JsonNode label : labels) {
                JsonNode labelId = label.get("id"), labelTitle = label.get("title");
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(event.get("date").asText(), dateTimeFormatter);
                long labelIdLong = Long.parseLong(labelId.asText());

                if (isFilterEnabled) {
                    if (Objects.equals(labelIdLong, activityId) && Objects.equals(labelTitle.asText(), activityTitle)) {
                        activities.add(new Activity(null, Long.parseLong(eventId.asText()), dateTime));
                    }
                }

                if (!isFilterEnabled) {
                    activities.add(new Activity(null, Long.parseLong(eventId.asText()), dateTime));
                }
            }
        }

        return activities;
    }

    public List<Client> getClientsEnrolledInActivity(String jsonResponse, long activityId) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

        JsonNode data = objectMapper.readTree(jsonResponse).get("data");
        ArrayList<Client> clients = new ArrayList<>();

        for (JsonNode record : data) {
            JsonNode id = record.get("activity_id");

            if (Long.parseLong(id.asText()) == activityId) {
                JsonNode clientJson = record.get("client"), yclientsId = clientJson.get("id"),
                        name = clientJson.get("name"), phone = clientJson.get("phone");

                clients.add(new Client(null,
                        Long.parseLong(yclientsId.asText()),
                        name.asText(),
                        phone.asText(),
                        Long.parseLong(id.asText())));
            }
        }

        return clients;
    }
}