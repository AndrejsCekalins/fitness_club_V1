package org.fitness_center.web_ui.controllers;

import org.fitness_center.core.requests.AddClientRequest;
import org.fitness_center.core.responses.AddClientResponse;
import org.fitness_center.core.services.AddClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddClientController {
    @Autowired
    private AddClientService addClientService;

    @GetMapping(value = "/addClientToList")
    public String showAddClientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new AddClientRequest());
        return "addClient";
    }

    @PostMapping("/addClientToList")
    public String processAddClientRequest(@ModelAttribute(value = "request") AddClientRequest request, ModelMap modelMap) {
        AddClientResponse response = addClientService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "addClient";
        } else {
            return "redirect:/";
        }
    }
}
