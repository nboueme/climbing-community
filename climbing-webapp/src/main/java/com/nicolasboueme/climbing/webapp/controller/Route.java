package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.RouteManager;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Route extends AbstractResource {
    private RouteManager webappToConsumer =  getManagerFactory().getRouteManager();
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @GetMapping("/climbing/{spotId}/sector/{routeId}")
    public String listRoutesFromParent(final ModelMap modelMap, @PathVariable String spotId, @PathVariable String routeId) {
        modelMap.addAttribute("spotId", spotId);
        modelMap.addAttribute("routeList", webappToConsumer.listRoutesFromParent(Integer.parseInt(routeId)));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(Integer.parseInt(routeId)));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(Integer.parseInt(routeId)));
        return "route";
    }

    @GetMapping("/climbing/{spotId}/route/{routeId}")
    public String getRoute(final ModelMap modelMap, @PathVariable String spotId, @PathVariable String routeId) {
        modelMap.addAttribute("route", webappToConsumer.getRoute(Integer.parseInt(routeId)));
        modelMap.addAttribute("listLength", webappToConsumer.listLengthsFromRoute(Integer.parseInt(routeId)));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(Integer.parseInt(routeId)));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(Integer.parseInt(routeId)));
        return "route_item";
    }
}
