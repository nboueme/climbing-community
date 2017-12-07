package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.SpotManager;
import com.nicolasboueme.climbing.model.entity.Spot;
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
public class SpotController extends AbstractResource {
    private SpotManager webappToConsumer = getManagerFactory().getSpotManager();

    @GetMapping("/climbing")
    public String listSpots(final ModelMap modelMap) {
        modelMap.addAttribute("spotList", webappToConsumer.listSpots());
        return "climbing";
    }

    @PostMapping("/climbing")
    public void addSpot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Spot spot = new Spot();
        spot.setUserAccountId(((UserAccount) request.getSession().getAttribute("user")).getId());
        spot.setName(request.getParameter("name"));
        spot.setDescription(request.getParameter("description"));
        spot.setHeight(Integer.parseInt(request.getParameter("height")));

        webappToConsumer.addSpot(spot);
        response.sendRedirect(request.getContextPath() + "/climbing");
    }

    @PostMapping("/climbing/{spotId}/update")
    public void updateSpot(@PathVariable String spotId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Spot spot = new Spot();
        spot.setPublicationId(Integer.parseInt(spotId));
        spot.setName(request.getParameter("name"));
        spot.setDescription(request.getParameter("description"));
        spot.setHeight(Integer.parseInt(request.getParameter("height")));

        webappToConsumer.updateSpot(spot);
        response.sendRedirect(request.getContextPath() + "/climbing");
    }

    @PostMapping("/climbing/{spotId}/delete")
    public void deleteSpot(@PathVariable String spotId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Spot spot = new Spot();
        spot.setPublicationId(Integer.parseInt(spotId));

        webappToConsumer.deleteSpot(spot);
        response.sendRedirect(request.getContextPath() + "/climbing");
    }
}
