package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.SpotManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/climbing")
public class Spot {
    private SpotManager webappToConsumer = new SpotManager();

    @RequestMapping(method = RequestMethod.GET)
    public String listSpots(final ModelMap modelMap) {
        webappToConsumer.initDao();
        modelMap.addAttribute("spotList", webappToConsumer.getSpotDao().listSpots());
        return "climbing";
    }
}
