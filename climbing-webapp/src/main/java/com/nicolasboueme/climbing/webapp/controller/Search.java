package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.SearchManager;
import com.nicolasboueme.climbing.model.entity.Publication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Search {
    private SearchManager webappToConsumer = new SearchManager();

    @GetMapping("/search")
    public String searchForm(Model model) {
        //model.addAttribute("searchPublication", new Publication());
        return "search";
    }

    @PostMapping("/search")
    public String listPublication(@ModelAttribute("searchPublication") Publication publication, ModelMap modelMap) {
        webappToConsumer.initDao();
        modelMap.addAttribute("publicationName", publication.getName());
        modelMap.addAttribute("publicationList", webappToConsumer.getPublicationDao().listPublication());
        return "search";
    }
}
