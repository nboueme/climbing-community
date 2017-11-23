package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.TopoManager;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Topo extends AbstractResource {
    private TopoManager webappToConsumer = getManagerFactory().getTopoManager();

    @GetMapping("/topo")
    public String listTopo(final ModelMap modelMap) {
        modelMap.addAttribute("topoList", webappToConsumer.listTopo());
        return "topo";
    }
}
