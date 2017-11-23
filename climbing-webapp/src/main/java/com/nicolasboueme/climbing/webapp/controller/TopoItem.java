package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.TopoManager;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TopoItem extends AbstractResource {
    private TopoManager webappToConsumer = getManagerFactory().getTopoManager();

    @GetMapping("/topo/{topoId}")
    public String getTopo(final ModelMap modelMap, @PathVariable("topoId") final String topoId) {
        modelMap.addAttribute("topo", webappToConsumer.getTopo(Integer.parseInt(topoId)));
        return "topo_item";
    }
}
