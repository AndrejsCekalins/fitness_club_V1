package org.fitness_center.web_ui.controllers;

import org.fitness_center.core.requests.SearchClientsRequest;
import org.fitness_center.core.responses.SearchClientsResponse;
import org.fitness_center.core.services.SearchClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchClientsController {

    @Autowired private SearchClientsService searchClientsService;

    @GetMapping(value = "/searchClients")
    public String showSearchClientsPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new SearchClientsRequest());
        return "searchClients";
    }
    @PostMapping("/searchClients")
    public String processSearchClientsRequest(@ModelAttribute(value = "request") SearchClientsRequest request, ModelMap modelMap) {
        SearchClientsResponse response = searchClientsService.execute(request);
        modelMap.addAttribute("clients", response.getFoundClients());
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
        }
        return "searchClients";
    }

}
