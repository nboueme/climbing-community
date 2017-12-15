package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.SpotManager;
import com.nicolasboueme.climbing.model.entity.Spot;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpotController extends AbstractResource {
    private SpotManager webappToConsumer = getManagerFactory().getSpotManager();

    @GetMapping("/climbing")
    public ModelAndView listSpots(ModelMap modelMap) {
        modelMap.addAttribute("spotList", webappToConsumer.listSpots());
        return new ModelAndView("climbing", "spot", new Spot());
    }

    @PostMapping("/climbing")
    public String addSpot(@ModelAttribute Spot spot, @SessionAttribute UserAccount user) {
        spot.setUserAccountId(user.getId());

        webappToConsumer.addSpot(spot);
        return "redirect:/climbing";
    }

    @PostMapping("/climbing/{spotId}/update")
    public String updateSpot(@ModelAttribute Spot spot, @PathVariable String spotId, @RequestParam String description) {
        spot.setPublicationId(Integer.parseInt(spotId));
        spot.setDescription(description);

        webappToConsumer.updateSpot(spot);
        return "redirect:/climbing";
    }

    @PostMapping("/climbing/{spotId}/delete")
    public String deleteSpot(@ModelAttribute Spot spot, @PathVariable String spotId) {
        spot.setPublicationId(Integer.parseInt(spotId));

        webappToConsumer.deleteSpot(spot);
        return "redirect:/climbing";
    }
}
