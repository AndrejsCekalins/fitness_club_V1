package fitness_center.rest.deleteClient;


import org.junit.jupiter.api.Test;

public class FitnessClubControllerPersonalCodeEmptyDeleteClientTestCase2 extends
        FitnessClubControllerPersonalCodeEmptyDeleteClientTestCase {
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "delete_client_test_case_2";
    }
}
