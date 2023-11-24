-- liquibase formatted sql

-- changeset liquibase:1
ALTER TABLE person ADD deleted int default 0;