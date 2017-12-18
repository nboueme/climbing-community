package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController extends AbstractResource {

    @GetMapping("/")
    public String home(ModelMap modelMap) {
        modelMap.addAttribute("spotList", getManagerFactory().getSpotManager().listSpots());
        modelMap.addAttribute("topoList", getManagerFactory().getTopoManager().listTopo());
        return "index";
    }
}
