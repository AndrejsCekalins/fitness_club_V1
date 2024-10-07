package fitness_center.rest.addClient;

import org.junit.jupiter.api.Test;

public class FitnessClubControllerAddClientTestCase5 extends
        FitnessClubControllerAddClientTestCase {
    @Test
    public void execute() throws Exception {
        executeAndCompare();
    }

    @Override
    protected String getTestCaseFolderName() {
        return "add_client_test_case_5";
    }
}
