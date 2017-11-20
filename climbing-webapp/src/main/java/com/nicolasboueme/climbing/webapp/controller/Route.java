package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.RouteManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/climbing/{spotId}/sector/{routeId}")
public class Route {
    private RouteManager webappToConsumer = new RouteManager();

    @RequestMapping(method = RequestMethod.GET)
    public String listRoutesFromParent(final ModelMap modelMap, @PathVariable("spotId") final String spotId, @PathVariable("routeId") final String routeId) {
        webappToConsumer.initDao();
        modelMap.addAttribute("spotId", spotId);
        modelMap.addAttribute("routeList", webappToConsumer.getRouteDao().listRoutesFromParent(Integer.parseInt(routeId)));
        return "route";
    }
}
