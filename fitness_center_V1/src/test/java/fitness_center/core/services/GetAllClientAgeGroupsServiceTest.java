package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaAgeGroupRepository;
import fitness_center.core.domain.AgeGroup;
import fitness_center.core.requests.GetAllAgeGroupsRequest;
import fitness_center.core.responses.GetAllAgeGroupsResponse;
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
public class GetAllClientAgeGroupsServiceTest {
    private AgeGroup ageGroup1;
    private AgeGroup ageGroup2;
    private AgeGroup ageGroup3;

    @Mock
    private JpaAgeGroupRepository ageGroupsRepository;

    @InjectMocks
    private GetAllAgeGroupsService service;


    @Test
    public void shouldGetAgeGroupsFromDb() {

        ageGroup1 = new AgeGroup(1L, "CHILD");
        ageGroup2 = new AgeGroup(2L, "ADULT");
        ageGroup3 = new AgeGroup(3L, "SENIOR");

        List<AgeGroup> ageGroups = List.of(ageGroup1,
                ageGroup2, ageGroup3);
        Mockito.when(ageGroupsRepository.findAll()).thenReturn(ageGroups);
        GetAllAgeGroupsRequest request = new GetAllAgeGroupsRequest();
        GetAllAgeGroupsResponse response = service.execute(request);
        assertEquals(response.getAgeGroups().size(), 3);
        assertEquals(response.getAgeGroups().get(0).getAgeGroup(), "CHILD");
        assertEquals(response.getAgeGroups().get(1).getAgeGroup(), "ADULT");
        assertEquals(response.getAgeGroups().get(2).getAgeGroup(), "SENIOR");
    }
    @Test
    public void shouldNotGetAgeGroupsFromDb() {

        ageGroup1 = new AgeGroup(1L, "CHILD");
        List<AgeGroup> ageGroups = List.of(ageGroup1);

        Mockito.when(ageGroupsRepository.findAll()).thenReturn(ageGroups);
        GetAllAgeGroupsRequest request = new GetAllAgeGroupsRequest();
        GetAllAgeGroupsResponse response = service.execute(request);
        assertEquals(response.getAgeGroups().size(), 1);
        assertNotEquals(response.getAgeGroups().get(0).getAgeGroup(), "SENIOR");
    }

}