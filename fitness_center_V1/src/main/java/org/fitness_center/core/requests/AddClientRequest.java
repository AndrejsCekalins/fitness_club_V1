package org.fitness_center.core.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddClientRequest {

    private String firstName;
    private String lastName;
    private String personalCode;

}
