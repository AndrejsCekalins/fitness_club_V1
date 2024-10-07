package fitness_center.core.services;

import fitness_center.core.database.jpa.JpaFitnessCentersRepository;
import fitness_center.core.domain.FitnessCenter;
import fitness_center.core.requests.GetAllFitnessCentersRequest;
import fitness_center.core.responses.GetAllFitnessCentersResponse;
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
public class GetAllClientFitnessCentersServiceTest {
    private FitnessCenter fitnessCenter1;
    private FitnessCenter fitnessCenter2;
    private FitnessCenter fitnessCenter3;
    private FitnessCenter fitnessCenter4;
    private FitnessCenter fitnessCenter5;

    @Mock
    private JpaFitnessCentersRepository fitnessCentersRepository;

    @InjectMocks
    private GetAllFitnessCentresService service;


    @Test
    public void shouldGetFitnessCentersFromDb() {

        fitnessCenter1 = new FitnessCenter(1L, "IMANTA");
        fitnessCenter2 = new FitnessCenter(2L, "AKROPOLE");
        fitnessCenter3 = new FitnessCenter(3L, "SAGA");
        fitnessCenter4 = new FitnessCenter(4L, "RIGA_PLAZA");
        fitnessCenter5 = new FitnessCenter(5L, "ZOLITUDE");

        List<FitnessCenter> fitnessCenters = List.of(fitnessCenter1,
                fitnessCenter2, fitnessCenter3,fitnessCenter4,fitnessCenter5);
        Mockito.when(fitnessCentersRepository.findAll()).thenReturn(fitnessCenters);
        GetAllFitnessCentersRequest request = new GetAllFitnessCentersRequest();
        GetAllFitnessCentersResponse response = service.execute(request);
        assertEquals(response.getFitnessCenters().size(), 5);
        assertEquals(response.getFitnessCenters().get(0).getFitnessCenter(), "IMANTA");
        assertEquals(response.getFitnessCenters().get(1).getFitnessCenter(), "AKROPOLE");
        assertEquals(response.getFitnessCenters().get(2).getFitnessCenter(), "SAGA");
        assertEquals(response.getFitnessCenters().get(3).getFitnessCenter(), "RIGA_PLAZA");
        assertEquals(response.getFitnessCenters().get(4).getFitnessCenter(), "ZOLITUDE");
    }
    @Test
    public void shouldNotGetFitnessCentersFromDb() {

        fitnessCenter1 = new FitnessCenter(1L, "IMANTA");
        List<FitnessCenter> fitnessCenters = List.of(fitnessCenter1);

        Mockito.when(fitnessCentersRepository.findAll()).thenReturn(fitnessCenters);
        GetAllFitnessCentersRequest request = new GetAllFitnessCentersRequest();
        GetAllFitnessCentersResponse response = service.execute(request);
        assertEquals(response.getFitnessCenters().size(), 1);
        assertNotEquals(response.getFitnessCenters().get(0).getFitnessCenter(), "ZOLITUDE");

    }

}