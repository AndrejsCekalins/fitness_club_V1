package fitness_center.rest.getClient;


import org.junit.jupiter.api.Test;

public class FitnessClubControllerGetClientWithCorrectIdTestCase1 extends
        FitnessClubControllerGetClientWithCorrectIdTestCase {
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "get_client_test_case_1";
    }
}
