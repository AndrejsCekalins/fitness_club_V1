package fitness_center.core.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import fitness_center.core.domain.Client;

import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GetClientResponse extends CoreResponse {
    private Client client;

    public GetClientResponse(List<CoreError> errors) {
        super(errors);
    }
}
