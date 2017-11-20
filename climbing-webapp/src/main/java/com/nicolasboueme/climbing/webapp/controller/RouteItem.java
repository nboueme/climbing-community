package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.RouteManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/climbing/{spotId}/route/{routeId}")
public class RouteItem {
    private RouteManager webappToConsumer = new RouteManager();

    @RequestMapping(method = RequestMethod.GET)
    public String getRoute(final ModelMap modelMap, @PathVariable("spotId") final String spotId, @PathVariable("routeId") final String routeId) {
        webappToConsumer.initDao();
        modelMap.addAttribute("spotId", spotId);
        modelMap.addAttribute("route", webappToConsumer.getRouteDao().getRoute(Integer.parseInt(routeId)));
        return "route_item";
    }
}
