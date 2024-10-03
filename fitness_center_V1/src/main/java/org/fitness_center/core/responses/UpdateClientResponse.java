package org.fitness_center.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.fitness_center.core.domain.Client;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateClientResponse extends CoreResponse {
    private Client updateClient;

    public UpdateClientResponse(List<CoreError> errors) {
        super(errors);
    }

}
