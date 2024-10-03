package org.fitness_center.core.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Client {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "personal_code", nullable = false, unique = true)
    private String personalCode;

    public Client(String firstName, String lastName, String personalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalCode = personalCode;
    }

    @Override
    public String toString() {
        return "Client{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalCode='" + personalCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(firstName, client.firstName) && Objects.equals(lastName, client.lastName) && Objects.equals(personalCode, client.personalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, personalCode);
    }
}
