package org.fitness_center.core.requests;


import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchClientsRequest {

    private String firstName;
    private String lastName;
    private String personalCode;
    private Ordering ordering;
    private Paging paging;

    public SearchClientsRequest(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public SearchClientsRequest(String personaCode) {
        this.personalCode = personaCode;
    }

    public SearchClientsRequest(String firstName, String lastName, Ordering ordering) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ordering = ordering;
    }

    public SearchClientsRequest(String firstName, String lastName, Paging paging) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.paging = paging;
    }

    public SearchClientsRequest(String firstName, String lastName, Ordering ordering, Paging paging) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ordering = ordering;
        this.paging = paging;
    }

    public SearchClientsRequest(String firstName, String lastName, String personalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
    }

    public boolean isFirstNameProvided() {
        return this.firstName != null && !this.firstName.isEmpty();
    }

    public boolean isLastNameProvided() {
        return this.lastName != null && !this.lastName.isEmpty();
    }

    public boolean isPersonalCodeProvided() {
        return this.personalCode != null && !this.personalCode.isEmpty();
    }


    @Override
    public String toString() {
        return "SearchClientsRequest{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalCode='" + personalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchClientsRequest that = (SearchClientsRequest) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(personalCode, that.personalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, personalCode);
    }
}

