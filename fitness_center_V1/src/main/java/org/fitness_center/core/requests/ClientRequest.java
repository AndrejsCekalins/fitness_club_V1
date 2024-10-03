package org.fitness_center.core.requests;

public class ClientRequest {

    private String firstName;
    private String lastName;
    private String personalCode;

    public ClientRequest(String firstName, String lastName, String personalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPersonalCode() {
        return personalCode;
    }
}
