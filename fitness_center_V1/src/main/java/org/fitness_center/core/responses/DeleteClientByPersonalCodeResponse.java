package org.fitness_center.core.responses;

import java.util.List;

public class DeleteClientByPersonalCodeResponse extends CoreResponse {

    private boolean clientRemoved;


    public DeleteClientByPersonalCodeResponse(List<CoreError> errors) {
        super(errors);
    }

    public DeleteClientByPersonalCodeResponse(boolean clientRemoved) {
        this.clientRemoved = clientRemoved;
    }

    public boolean isClientRemoved() {
        return clientRemoved;
    }

}
