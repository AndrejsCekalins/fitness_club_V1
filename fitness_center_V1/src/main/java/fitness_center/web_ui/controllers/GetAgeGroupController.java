package fitness_center.web_ui.controllers;

import fitness_center.core.requests.GetAgeGroupRequest;
import fitness_center.core.responses.GetAgeGroupResponse;
import fitness_center.core.services.GetAgeGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class GetAgeGroupController {

    @Autowired
    private GetAgeGroupService getAgeGroupService;


    @GetMapping(path = "/getAgeGroup", produces = "application/json")
    public GetAgeGroupResponse getAgeGroup(@PathVariable Long id) {
        GetAgeGroupRequest request = new GetAgeGroupRequest(id);
        return getAgeGroupService.execute(request);
    }
}
