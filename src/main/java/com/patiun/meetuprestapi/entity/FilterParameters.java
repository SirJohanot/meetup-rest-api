package com.patiun.meetuprestapi.entity;

import java.util.Objects;

public class FilterParameters {

    private String agendaFilter;
    private String organizerFilter;
    private String dateTimeFilter;

    public FilterParameters() {
    }

    public FilterParameters(String agendaFilter, String organizerFilter, String dateTimeFilter) {
        this.agendaFilter = agendaFilter;
        this.organizerFilter = organizerFilter;
        this.dateTimeFilter = dateTimeFilter;
    }

    public String getAgendaFilter() {
        return agendaFilter;
    }

    public void setAgendaFilter(String agendaFilter) {
        this.agendaFilter = agendaFilter;
    }

    public String getOrganizerFilter() {
        return organizerFilter;
    }

    public void setOrganizerFilter(String organizerFilter) {
        this.organizerFilter = organizerFilter;
    }

    public String getDateTimeFilter() {
        return dateTimeFilter;
    }

    public void setDateTimeFilter(String dateTimeFilter) {
        this.dateTimeFilter = dateTimeFilter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FilterParameters that = (FilterParameters) o;

        if (!Objects.equals(agendaFilter, that.agendaFilter)) {
            return false;
        }
        if (!Objects.equals(organizerFilter, that.organizerFilter)) {
            return false;
        }
        return Objects.equals(dateTimeFilter, that.dateTimeFilter);
    }

    @Override
    public int hashCode() {
        int result = agendaFilter != null ? agendaFilter.hashCode() : 0;
        result = 31 * result + (organizerFilter != null ? organizerFilter.hashCode() : 0);
        result = 31 * result + (dateTimeFilter != null ? dateTimeFilter.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FilterParameters{" +
                "agendaFilter='" + agendaFilter + '\'' +
                ", organizerFilter='" + organizerFilter + '\'' +
                ", dateTimeFilter='" + dateTimeFilter + '\'' +
                '}';
    }
}
