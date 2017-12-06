package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.RouteManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Route;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RouteController extends AbstractResource {
    private RouteManager webappToConsumer =  getManagerFactory().getRouteManager();
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @GetMapping("/climbing/{spotId}/sector/{sectorId}")
    public String listRoutesFromParent(final ModelMap modelMap, @PathVariable String spotId, @PathVariable String sectorId) {
        Route route = new Route();
        route.setSectorId(Integer.parseInt(sectorId));

        Comment comment = new Comment();
        comment.setPublicationId(Integer.parseInt(sectorId));

        modelMap.addAttribute("spotId", spotId);
        modelMap.addAttribute("routeList", webappToConsumer.listRoutesFromParent(route));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return "route";
    }

    @GetMapping("/climbing/{spotId}/route/{routeId}")
    public String getRoute(final ModelMap modelMap, @PathVariable String spotId, @PathVariable String routeId) {
        Route route = new Route();
        route.setPublicationId(Integer.parseInt(routeId));
        route.setParentPublicationId(Integer.parseInt(routeId));

        Comment comment = new Comment();
        comment.setPublicationId(Integer.parseInt(routeId));

        modelMap.addAttribute("route", webappToConsumer.getRoute(route));
        modelMap.addAttribute("listLength", webappToConsumer.listLengthsFromRoute(route));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return "route_item";
    }
}
