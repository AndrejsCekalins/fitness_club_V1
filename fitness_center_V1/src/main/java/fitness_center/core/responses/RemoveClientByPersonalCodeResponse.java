package fitness_center.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RemoveClientByPersonalCodeResponse extends CoreResponse {

    private boolean removeClient;


    public RemoveClientByPersonalCodeResponse(List<CoreError> errors) {
        super(errors);
    }

    public boolean isClientRemoved() {
        return removeClient;
    }
}
