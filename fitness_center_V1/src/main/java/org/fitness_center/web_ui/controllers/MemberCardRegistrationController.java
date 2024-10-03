package org.fitness_center.web_ui.controllers;

import org.fitness_center.core.database.jpa.JpaAgeGroupRepository;
import org.fitness_center.core.database.jpa.JpaFitnessCentersRepository;
import org.fitness_center.core.database.jpa.JpaWorkoutsRepository;
import org.fitness_center.core.requests.MemberCardRegistrationRequest;
import org.fitness_center.core.requests.SearchMemberCardRequest;
import org.fitness_center.core.requests.SearchWorkoutRequest;
import org.fitness_center.core.responses.MemberCardRegistrationResponse;
import org.fitness_center.core.responses.SearchClientsResponse;
import org.fitness_center.core.responses.SearchWorkoutResponse;
import org.fitness_center.core.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
public class MemberCardRegistrationController {

    @Autowired
    private MemberCardRegistrationService memberCardRegistrationService;
    @Autowired
    private JpaWorkoutsRepository workoutsRepository;
    @Autowired
    private JpaAgeGroupRepository ageGroupRepository;
    @Autowired
    private JpaFitnessCentersRepository fitnessCentersRepository;
    @Autowired
    private SearchClientsService searchClientService;
    @Autowired
    private SearchAgeGroupService searchAgeGroupService;
    @Autowired
    private SearchFitnessCenterService searchFitnessCenterService;
    @Autowired
    private SearchWorkoutService searchWorkoutService;

    @GetMapping(value = "/memberCardRegistration")
    public String showMemberCardFilling(ModelMap modelMap) {

        modelMap.addAttribute("ageGroups", ageGroupRepository.findAll());

        modelMap.addAttribute("fitnessCenters", fitnessCentersRepository.findAll());

        modelMap.addAttribute("workouts", workoutsRepository.findAll());


        modelMap.addAttribute("request", new MemberCardRegistrationRequest());
        modelMap.addAttribute("searchRequest", new SearchMemberCardRequest());
        return "memberCardRegistration";
    }

    @PostMapping("/memberCardFormFilling")
    public String processMemberCardFillingRequest(@ModelAttribute(value = "request") MemberCardRegistrationRequest request,
                                                  ModelMap modelMap) throws ParseException {
        MemberCardRegistrationResponse response = memberCardRegistrationService.execute(request);
        if (response.hasErrors()) {
            modelMap.addAttribute("errors", response.getErrors());
            return "memberCardRegistration";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/registeredWorkoutByClient/search")
    public String processSearchClientsRequest(@ModelAttribute(value = "searchRequest")
                                              SearchMemberCardRequest searchRequest,
                                              ModelMap modelMap) {
        SearchClientsRequest searchClientRequest = new SearchClientsRequest(
                searchRequest.getClientFirstName(), searchRequest.getClientLastName(), searchRequest.getClientPersonalCode());

        SearchClientsResponse searchClientResponse = searchClientService.execute(searchClientRequest);
        modelMap.addAttribute("clients", searchClientResponse.getFoundClients());


        SearchWorkoutRequest searchWorkoutRequest = new SearchWorkoutRequest(
                searchRequest.getWorkoutTitle());

        SearchWorkoutResponse searchWorkoutResponse = searchWorkoutService.execute(searchWorkoutRequest);
        modelMap.addAttribute("workouts", searchWorkoutResponse.foundWorkouts());
        modelMap.addAttribute("request", new MemberCardRegistrationRequest());


        return "registeredWorkoutByClient";
    }



    /*

    @GetMapping(path = "/{id}", produces = "application/json")
    public GetMemberCardResponse getMemberCard(@PathVariable Long id) {
        GetMemberCardRequest request = new GetMemberCardRequest(id);
        return getMemberCardService.execute(request);
    }

    @PostMapping(path = "/",
            consumes = "application/json",
            produces = "application/json")
    public MemberCardRegistrationResponse addMemberCard(@RequestBody MemberCardRegistrationRequest request) {
        return addMemberCardService.execute(request);
    }

    @PostMapping(path = "/{id}",
            consumes = "application/json",
            produces = "application/json")
    public UpdateMemberCardResponse updateMemberCard(@RequestBody UpdateMemberCardRequest request) {
        return updateMemberCardService.execute(request);
    }


    // @DeleteMapping(path = "/{id}", produces = "application/json")
    //public DeleteMemberCardResponse deleteMemberCard(@PathVariable Long id) {
    //  DeleteMemberCardRequest request = new DeleteMemberCardRequest(id);
    //return deleteMemberCardService.executeByMemberCardId(request);
    // }

     */
}
