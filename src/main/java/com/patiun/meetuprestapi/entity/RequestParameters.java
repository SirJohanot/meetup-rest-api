package com.patiun.meetuprestapi.entity;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RequestParameters {

    private Map<String, String> filterParameters;

    private List<String> sortingParameters;

    public RequestParameters() {
    }

    public RequestParameters(Map<String, String> filterParameters, List<String> sortingParameters) {
        this.filterParameters = filterParameters;
        this.sortingParameters = sortingParameters;
    }

    public Map<String, String> getFilterParameters() {
        return filterParameters;
    }

    public void setFilterParameters(Map<String, String> filterParameters) {
        this.filterParameters = filterParameters;
    }

    public List<String> getSortingParameters() {
        return sortingParameters;
    }

    public void setSortingParameters(List<String> sortingParameters) {
        this.sortingParameters = sortingParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RequestParameters that = (RequestParameters) o;

        if (!Objects.equals(filterParameters, that.filterParameters)) {
            return false;
        }
        return Objects.equals(sortingParameters, that.sortingParameters);
    }

    @Override
    public int hashCode() {
        int result = filterParameters != null ? filterParameters.hashCode() : 0;
        result = 31 * result + (sortingParameters != null ? sortingParameters.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RequestParameters{" +
                "filterParameters=" + filterParameters +
                ", sortingParameters=" + sortingParameters +
                '}';
    }
}
