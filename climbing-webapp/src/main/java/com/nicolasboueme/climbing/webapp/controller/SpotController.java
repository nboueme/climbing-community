package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.SpotManager;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpotController extends AbstractResource {
    private SpotManager webappToConsumer = getManagerFactory().getSpotManager();

    @GetMapping("/climbing")
    public String listSpots(final ModelMap modelMap) {
        modelMap.addAttribute("spotList", webappToConsumer.listSpots());
        return "climbing";
    }
}
