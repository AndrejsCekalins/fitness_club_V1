package fitness_center.rest.updateClient;


import org.junit.jupiter.api.Test;

public class FitnessClubControllerUpdateClientNewFirstNameEmptyTestCase3 extends
        FitnessClubControllerUpdateClientIWithExistingIdInDbTestCase {
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "update_client_test_case_3";
    }
}
