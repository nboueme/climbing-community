package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.model.entity.Publication;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchController extends AbstractResource {
    private PublicationManager webappToConsumer = getManagerFactory().getPublicationManager();

    @GetMapping("/search")
    public String searchForm(Model model) {
        //model.addAttribute("searchPublication", new Publication());
        return "search";
    }

    @PostMapping("/search")
    public String listPublication(@ModelAttribute("searchPublication") Publication publication, ModelMap modelMap) {
        modelMap.addAttribute("publicationName", publication.getName());
        modelMap.addAttribute("publicationList", webappToConsumer.listPublication());
        return "search";
    }
}
