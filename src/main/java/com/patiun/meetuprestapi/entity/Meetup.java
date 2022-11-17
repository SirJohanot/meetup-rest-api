package com.patiun.meetuprestapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Objects;

public class Meetup {

    private Integer id;
    private String agenda;
    private String description;
    private String organizer;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime dateTime;
    private String location;

    public Meetup() {
    }

    public Meetup(Integer id, String agenda, String description, String organizer, LocalDateTime dateTime, String location) {
        this.id = id;
        this.agenda = agenda;
        this.description = description;
        this.organizer = organizer;
        this.dateTime = dateTime;
        this.location = location;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgenda() {
        return agenda;
    }

    public void setAgenda(String agenda) {
        this.agenda = agenda;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Meetup meetup = (Meetup) o;

        if (!Objects.equals(id, meetup.id)) {
            return false;
        }
        if (!Objects.equals(agenda, meetup.agenda)) {
            return false;
        }
        if (!Objects.equals(description, meetup.description)) {
            return false;
        }
        if (!Objects.equals(organizer, meetup.organizer)) {
            return false;
        }
        if (!Objects.equals(dateTime, meetup.dateTime)) {
            return false;
        }
        return Objects.equals(location, meetup.location);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (agenda != null ? agenda.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (organizer != null ? organizer.hashCode() : 0);
        result = 31 * result + (dateTime != null ? dateTime.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Meetup{" +
                "id=" + id +
                ", agenda='" + agenda + '\'' +
                ", description='" + description + '\'' +
                ", organizer='" + organizer + '\'' +
                ", dateTime=" + dateTime +
                ", location='" + location + '\'' +
                '}';
    }
}
