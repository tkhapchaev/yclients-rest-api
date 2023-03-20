package ru.tkhapchaev.services.yclients.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class YclientsApiWrapper {
    private long companyId;
    private String bearerAuthentication;
    private String userToken;
    private String contentTypeHeader;
    private String acceptHeader;

    public String getListOfRecords() throws IOException {
        final Content request = Request.Get("https://api.yclients.com/api/v1/records/" + companyId)
                .addHeader("Content-Type", contentTypeHeader)
                .addHeader("Accept", acceptHeader)
                .addHeader("Authorization", "Bearer " + bearerAuthentication + ", User " + userToken)
                .execute()
                .returnContent();

        return request.asString(StandardCharsets.UTF_8);
    }

    public String getListOfActivities(LocalDateTime from, LocalDateTime till) throws IOException {
        String fromDate = from.getYear() + "-" + from.getMonth().getValue() + "-" + from.getDayOfMonth(),
                tillDate = till.getYear() + "-" + till.getMonth().getValue() + "-" + till.getDayOfMonth();

        final Content request = Request.Get("https://api.yclients.com/api/v1/activity/" + companyId + "/search/?from=" + fromDate + "&till=" + tillDate)
                .addHeader("Content-Type", contentTypeHeader)
                .addHeader("Accept", acceptHeader)
                .addHeader("Authorization", "Bearer " + bearerAuthentication + ", User " + userToken)
                .execute()
                .returnContent();

        return request.asString(StandardCharsets.UTF_8);
    }

    public String getListOfActivities(LocalDateTime day) throws IOException {
        String date = day.getYear() + "-" + day.getMonth().getValue() + "-" + day.getDayOfMonth();

        final Content request = Request.Get("https://api.yclients.com/api/v1/activity/" + companyId + "/search/?from=" + date + "&till=" + date)
                .addHeader("Content-Type", contentTypeHeader)
                .addHeader("Accept", acceptHeader)
                .addHeader("Authorization", "Bearer " + bearerAuthentication + ", User " + userToken)
                .execute()
                .returnContent();

        return request.asString(StandardCharsets.UTF_8);
    }
}