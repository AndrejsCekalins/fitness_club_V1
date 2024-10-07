package fitness_center.core.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateClientRequest {

    private Long id;

    private String newFirstName;

    private String newLastName;

    private String newPersonalCode;
}
