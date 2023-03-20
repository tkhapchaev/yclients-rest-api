CREATE SCHEMA IF NOT EXISTS activities;

CREATE TABLE IF NOT EXISTS activities.client
(
    id                   bigserial NOT NULL PRIMARY KEY,
    yclients_id          bigint    NOT NULL,
    name                 varchar   NOT NULL,
    phone                varchar   NOT NULL,
    yclients_activity_id bigint    NOT NULL
);

CREATE TABLE IF NOT EXISTS activities.activity
(
    id          bigserial NOT NULL PRIMARY KEY,
    yclients_id bigint    NOT NULL,
    date_time   timestamp NOT NULL
);

CREATE TABLE IF NOT EXISTS activities.result
(
    id                   bigserial        NOT NULL PRIMARY KEY,
    yclients_client_id   bigint           NOT NULL,
    client_name          varchar          NOT NULL,
    yclients_activity_id bigint           NOT NULL,
    score                double precision NOT NULL
);