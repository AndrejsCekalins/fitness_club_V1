package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaWorkoutsRepository;
import fitness_center.core.domain.Workout;
import fitness_center.core.requests.GetAllWorkoutsRequest;
import fitness_center.core.responses.GetAllWorkoutsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(MockitoJUnitRunner.class)
public class GetAllWorkoutsServiceTest {
   private Workout workout1;
   private Workout workout2;
   private Workout workout3;

    @Mock
    private JpaWorkoutsRepository workoutsRepository;

    @InjectMocks
    private GetAllWorkoutsService service;


    @Test
    public void shouldGetWorkoutsFromDb() {

       workout1 = new Workout(1L, "GYM");
        workout2 = new Workout(2L, "SWIMMING_POOL");
        workout3 = new Workout(3L, "GROUP_CLASSES");

        List<Workout> workouts = List.of(workout1,
                workout2, workout3);
        Mockito.when(workoutsRepository.findAll()).thenReturn(workouts);
        GetAllWorkoutsRequest request = new GetAllWorkoutsRequest();
        GetAllWorkoutsResponse response = service.execute(request);
        assertEquals(response.getWorkouts().size(), 3);
        assertEquals(response.getWorkouts().get(0).getWorkout(), "GYM");
        assertEquals(response.getWorkouts().get(1).getWorkout(), "SWIMMING_POOL");
        assertEquals(response.getWorkouts().get(2).getWorkout(), "GROUP_CLASSES");
    }
    @Test
    public void shouldNotGetWorkoutsFromDb() {
        workout1 = new Workout(1L, "GYM");
        List<Workout> workouts = List.of(workout1);

        Mockito.when(workoutsRepository.findAll()).thenReturn(workouts);
        GetAllWorkoutsRequest request = new GetAllWorkoutsRequest();
        GetAllWorkoutsResponse response = service.execute(request);
        assertEquals(response.getWorkouts().size(), 1);
        assertNotEquals(response.getWorkouts().get(0).getWorkout(), "GROUP_CLASSES");
    }
}