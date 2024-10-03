package org.fitness_center.web_ui.controllers;

import org.fitness_center.core.requests.GetFitnessCenterRequest;
import org.fitness_center.core.responses.GetFitnessCenterResponse;
import org.fitness_center.core.services.GetFitnessCenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GetFitnessCenterController {

    @Autowired
    private GetFitnessCenterService getFitnessCenterService;

    @GetMapping(path = "/getFitnessCenter", produces = "application/json")
    public GetFitnessCenterResponse getFitnessCenter(@PathVariable Long id) {
        GetFitnessCenterRequest request = new GetFitnessCenterRequest(id);
        return getFitnessCenterService.execute(request);
    }
}
