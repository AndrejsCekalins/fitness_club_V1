package org.fitness_center.core.domain;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "member_card")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MemberCard {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "age_group_id", nullable = false)
    private AgeGroup ageGroup;

    @ManyToOne
    @JoinColumn(name = "fitness_center_id", nullable = false)
    private FitnessCenter fitnessCenter;

    @ManyToOne
    @JoinColumn(name = "workout_id", nullable = false)
    private Workout workout;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "term_of_contract", nullable = false)
    private Date termOfContract;

    public MemberCard(Long client, Long ageGroup, Long fitnessCenter, Long workout, Date termOfContract) {
    }
}
