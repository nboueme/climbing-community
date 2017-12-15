package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.RouteManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Route;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RouteController extends AbstractResource {
    private RouteManager webappToConsumer =  getManagerFactory().getRouteManager();
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @GetMapping("/climbing/sector/{sectorId}")
    public ModelAndView listRoutesFromParent(ModelMap modelMap, @PathVariable String sectorId, HttpServletRequest request) {
        Route route = new Route();
        route.setSectorId(Integer.parseInt(sectorId));

        Comment comment = new Comment();
        comment.setPublicationId(Integer.parseInt(sectorId));

        modelMap.addAttribute("currentURI", request.getRequestURI());
        modelMap.addAttribute("publicationId", sectorId);
        modelMap.addAttribute("routeList", webappToConsumer.listRoutesFromParent(route));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return new ModelAndView("route", "route", new Route());
    }

    @PostMapping("/climbing/sector/{sectorId}")
    public String addRoute(@ModelAttribute Route route, @SessionAttribute UserAccount user, @PathVariable String sectorId) {
        route.setUserAccountId(user.getId());
        route.setSectorId(Integer.parseInt(sectorId));
        route.setTypeRoute("route");

        webappToConsumer.addRoute(route);
        return "redirect:/climbing/sector/" + sectorId;
    }

    @PostMapping("/climbing/{sectorId}/route/{routeId}/update")
    public String updateRoute(@ModelAttribute Route route, @PathVariable String sectorId, @PathVariable String routeId) {
        route.setPublicationId(Integer.parseInt(routeId));

        webappToConsumer.updateRoute(route);
        return "redirect:/climbing/sector/" + sectorId;
    }

    @PostMapping("/climbing/{sectorId}/route/{routeId}/delete")
    public String deleteRoute(@ModelAttribute Route route, @PathVariable String sectorId, @PathVariable String routeId) {
        route.setPublicationId(Integer.parseInt(routeId));

        webappToConsumer.deleteRoute(route);
        return "redirect:/climbing/sector/" + sectorId;
    }

    @GetMapping("/climbing/route/{routeId}")
    public ModelAndView getRoute(ModelMap modelMap, @PathVariable String routeId, HttpServletRequest request) {
        Route route = new Route();
        route.setPublicationId(Integer.parseInt(routeId));
        route.setParentPublicationId(Integer.parseInt(routeId));

        Comment comment = new Comment();
        comment.setPublicationId(Integer.parseInt(routeId));

        modelMap.addAttribute("currentURI", request.getRequestURI());
        modelMap.addAttribute("publicationId", routeId);
        modelMap.addAttribute("route", webappToConsumer.getRoute(route));
        modelMap.addAttribute("listLength", webappToConsumer.listLengthsFromRoute(route));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return new ModelAndView("route_item", "length", new Route());
    }

    @PostMapping("/climbing/route/{parentId}/{sectorId}")
    public String addLength(@ModelAttribute Route length, @SessionAttribute UserAccount user, @PathVariable String parentId, @PathVariable String sectorId) {
        length.setUserAccountId(user.getId());
        length.setSectorId(Integer.parseInt(sectorId));
        length.setParentPublicationId(Integer.parseInt(parentId));
        length.setTypeRoute("length");

        webappToConsumer.addRoute(length);
        return "redirect:/climbing/route/" + parentId;
    }

    @PostMapping("/climbing/route/{parentId}/{lengthId}/update")
    public String updateLength(@ModelAttribute Route length, @PathVariable String parentId, @PathVariable String lengthId) {
        length.setPublicationId(Integer.parseInt(lengthId));

        webappToConsumer.updateRoute(length);
        return "redirect:/climbing/route/" + parentId;
    }

    @PostMapping("/climbing/route/{parentId}/{lengthId}/delete")
    public String deleteLength(@ModelAttribute Route length, @PathVariable String parentId, @PathVariable String lengthId) {
        length.setPublicationId(Integer.parseInt(lengthId));

        webappToConsumer.deleteRoute(length);
        return "redirect:/climbing/route/" + parentId;
    }
}
