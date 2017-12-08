package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.RouteManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Route;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class RouteController extends AbstractResource {
    private RouteManager webappToConsumer =  getManagerFactory().getRouteManager();
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @GetMapping("/climbing/{spotId}/sector/{sectorId}")
    public String listRoutesFromParent(final ModelMap modelMap, @PathVariable String spotId, @PathVariable String sectorId, HttpServletRequest request) {
        Route route = new Route();
        route.setSectorId(Integer.parseInt(sectorId));

        Comment comment = new Comment();
        comment.setPublicationId(Integer.parseInt(sectorId));

        modelMap.addAttribute("currentURI", request.getRequestURI());
        modelMap.addAttribute("spotId", spotId);
        modelMap.addAttribute("publicationId", sectorId);
        modelMap.addAttribute("routeList", webappToConsumer.listRoutesFromParent(route));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return "route";
    }

    @PostMapping("/climbing/{spotId}/sector/{sectorId}")
    public void addRoute(@PathVariable String spotId, @PathVariable String sectorId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Route route = new Route();
        route.setUserAccountId(((UserAccount) request.getSession().getAttribute("user")).getId());
        route.setName(request.getParameter("name"));
        route.setSectorId(Integer.parseInt(sectorId));
        route.setHeight(Integer.parseInt(request.getParameter("height")));
        route.setQuotation(request.getParameter("quotation"));
        route.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        route.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        route.setPointsNumber(Integer.parseInt(request.getParameter("points_number")));
        route.setTypeRoute("route");

        webappToConsumer.addRoute(route);
        response.sendRedirect(request.getContextPath() + "/climbing/" + spotId + "/sector/" + sectorId);
    }

    @PostMapping("/climbing/{spotId}/sector/{sectorId}/route/{routeId}/update")
    public void updateRoute(@PathVariable String spotId, @PathVariable String sectorId, @PathVariable String routeId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Route route = new Route();
        route.setPublicationId(Integer.parseInt(routeId));
        route.setName(request.getParameter("name"));
        route.setHeight(Integer.parseInt(request.getParameter("height")));
        route.setQuotation(request.getParameter("quotation"));
        route.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        route.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        route.setPointsNumber(Integer.parseInt(request.getParameter("points_number")));

        webappToConsumer.updateRoute(route);
        response.sendRedirect(request.getContextPath() + "/climbing/" + spotId + "/sector/" + sectorId);
    }

    @PostMapping("/climbing/{spotId}/sector/{sectorId}/route/{routeId}/delete")
    public void deleteRoute(@PathVariable String spotId, @PathVariable String sectorId, @PathVariable String routeId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Route route = new Route();
        route.setPublicationId(Integer.parseInt(routeId));

        webappToConsumer.deleteRoute(route);
        response.sendRedirect(request.getContextPath() + "/climbing/" + spotId + "/sector/" + sectorId);
    }

    @GetMapping("/climbing/{spotId}/route/{routeId}")
    public String getRoute(final ModelMap modelMap, @PathVariable String spotId, @PathVariable String routeId, HttpServletRequest request) {
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
        return "route_item";
    }

    @PostMapping("/climbing/{spotId}/route/{routeId}")
    public void addLength(@PathVariable String spotId, @PathVariable String routeId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Route route = new Route();
        route.setUserAccountId(((UserAccount) request.getSession().getAttribute("user")).getId());
        route.setName(request.getParameter("name"));
        route.setSectorId(Integer.parseInt(request.getParameter("sector_id")));
        route.setParentPublicationId(Integer.parseInt(request.getParameter("parent_id")));
        route.setHeight(Integer.parseInt(request.getParameter("height")));
        route.setQuotation(request.getParameter("quotation"));
        route.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        route.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        route.setPointsNumber(Integer.parseInt(request.getParameter("points_number")));
        route.setTypeRoute("length");

        webappToConsumer.addRoute(route);
        response.sendRedirect(request.getContextPath() + "/climbing/" + spotId + "/route/" + routeId);
    }

    @PostMapping("/climbing/{spotId}/route/{routeId}/update")
    public void updateLength(@PathVariable String spotId, @PathVariable String routeId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Route route = new Route();
        route.setPublicationId(Integer.parseInt(routeId));
        route.setName(request.getParameter("name"));
        route.setHeight(Integer.parseInt(request.getParameter("height")));
        route.setQuotation(request.getParameter("quotation"));
        route.setLatitude(Double.parseDouble(request.getParameter("latitude")));
        route.setLongitude(Double.parseDouble(request.getParameter("longitude")));
        route.setPointsNumber(Integer.parseInt(request.getParameter("points_number")));

        webappToConsumer.updateRoute(route);
        response.sendRedirect(request.getContextPath() + "/climbing/" + spotId + "/route/" + request.getParameter("route_id"));
    }

    @PostMapping("/climbing/{spotId}/route/{childId}/delete")
    public void deleteLength(@PathVariable String spotId, @PathVariable String childId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Route route = new Route();
        route.setPublicationId(Integer.parseInt(childId));

        webappToConsumer.deleteRoute(route);
        response.sendRedirect(request.getContextPath() + "/climbing/" + spotId + "/route/" + request.getParameter("route_id"));
    }
}
