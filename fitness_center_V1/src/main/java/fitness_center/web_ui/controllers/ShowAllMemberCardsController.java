package fitness_center.web_ui.controllers;


import fitness_center.core.requests.GetAllMemberCardsRequest;
import fitness_center.core.responses.GetAllMemberCardsResponse;
import fitness_center.core.services.GetAllMemberCardsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowAllMemberCardsController {
    @Autowired
    private GetAllMemberCardsService getAllMemberCardsService;

    @GetMapping(value = "/showAllMemberCards")
    public String showAllMemberCards(ModelMap modelMap) {
        GetAllMemberCardsResponse response = getAllMemberCardsService.execute(
                new GetAllMemberCardsRequest()
        );
        modelMap.addAttribute("memberCard", response.getMemberCards());
        return "/showAllMemberCards";
    }
}
