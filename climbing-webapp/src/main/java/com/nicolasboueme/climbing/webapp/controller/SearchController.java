package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.model.entity.*;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SearchController extends AbstractResource {
    private PublicationManager webappToConsumer = getManagerFactory().getPublicationManager();

    @GetMapping("/search")
    public String searchForm() {
        return "search";
    }

    @PostMapping("/search")
    public String listPublication(ModelMap modelMap, HttpServletRequest request) {
        String typePublication = request.getParameter("type_publication");
        String textPublication = request.getParameter("text_name_description");
        String quotation = request.getParameter("quotation");

        int height;
        if (request.getParameter("height").equals("")) height = 50;
        else height = Integer.parseInt(request.getParameter("height"));

        int points;
        if (request.getParameter("points_number").equals("")) points = 0;
        else points = Integer.parseInt(request.getParameter("points_number"));

        if (typePublication.equals("spot")) {
            Spot spot = new Spot();
            spot.setName(textPublication);
            spot.setHeight(height);
            modelMap.addAttribute("publicationList", webappToConsumer.listPublication(spot));
        } else if (typePublication.equals("sector")) {
            Sector sector = new Sector();
            sector.setName(textPublication);
            sector.setHeight(height);
            modelMap.addAttribute("publicationList", webappToConsumer.listPublication(sector));
        } else if (typePublication.equals("route")) {
            Route route = new Route();
            route.setName(textPublication);
            route.setHeight(height);
            route.setQuotation(quotation);
            route.setPointsNumber(points);
            modelMap.addAttribute("publicationList", webappToConsumer.listPublication(route));
        } else {
            Topo topo = new Topo();
            topo.setName(textPublication);
            modelMap.addAttribute("publicationList", webappToConsumer.listPublication(topo));
        }

        modelMap.addAttribute("typePublication", typePublication);

        return "search";
    }
}
