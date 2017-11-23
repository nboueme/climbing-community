package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.RouteManager;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Route extends AbstractResource {
    private RouteManager webappToConsumer =  getManagerFactory().getRouteManager();


    @GetMapping("/climbing/{spotId}/sector/{routeId}")
    public String listRoutesFromParent(final ModelMap modelMap, @PathVariable("spotId") final String spotId, @PathVariable("routeId") final String routeId) {
        modelMap.addAttribute("spotId", spotId);
        modelMap.addAttribute("routeList", webappToConsumer.listRoutesFromParent(Integer.parseInt(routeId)));
        return "route";
    }
}
