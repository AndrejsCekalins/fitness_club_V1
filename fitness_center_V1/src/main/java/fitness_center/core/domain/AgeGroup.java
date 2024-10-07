package fitness_center.core.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "age_groups")
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AgeGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "age_group", nullable = false)
    private String ageGroup;


}
