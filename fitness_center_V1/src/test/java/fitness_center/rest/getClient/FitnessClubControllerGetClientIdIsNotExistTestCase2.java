package fitness_center.rest.getClient;


import org.junit.jupiter.api.Test;

public class FitnessClubControllerGetClientIdIsNotExistTestCase2 extends
        FitnessClubControllerGetClientIdIsNotExistTestCase {
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "get_client_test_case_2";
    }
}
