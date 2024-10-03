package org.fitness_center.web_ui.controllers;

import org.fitness_center.core.requests.RemoveClientByPersonalCodeRequest;
import org.fitness_center.core.responses.RemoveClientByPersonalCodeResponse;
import org.fitness_center.core.services.RemoveClientByPersonalCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveClientController {

    @Autowired
    private RemoveClientByPersonalCodeService deleteClientByPersonalCodeService;

    @GetMapping(value = "/removeClientFromList")
    public String showRemoveClientPage(ModelMap modelMap) {
        modelMap.addAttribute("request", new RemoveClientByPersonalCodeRequest());
        return "removeClient";
    }

    @PostMapping("/removeClientFromList")
    public String processRemoveClientRequest(@ModelAttribute(value = "request") RemoveClientByPersonalCodeRequest request, ModelMap modelMap) {
        RemoveClientByPersonalCodeResponse response = deleteClientByPersonalCodeService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "removeClient";
        } else {
            return "index";
        }
    }

}
