package ru.tkhapchaev.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "result", schema = "activities")
public class Result {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "yclients_client_id")
    private Long yclientsClientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "yclients_activity_id")
    private Long yclientsActivityId;

    private Double score;
}