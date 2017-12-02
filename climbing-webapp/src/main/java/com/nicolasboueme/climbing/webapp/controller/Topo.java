package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.TopoManager;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Topo extends AbstractResource {
    private TopoManager webappToConsumer = getManagerFactory().getTopoManager();
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @GetMapping("/topo")
    public String listTopo(final ModelMap modelMap) {
        modelMap.addAttribute("topoList", webappToConsumer.listTopo());
        return "topo";
    }

    @GetMapping("/topo/{topoId}")
    public String getTopo(final ModelMap modelMap, @PathVariable String topoId) {
        modelMap.addAttribute("topo", webappToConsumer.getTopo(Integer.parseInt(topoId)));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(Integer.parseInt(topoId)));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(Integer.parseInt(topoId)));
        return "topo_item";
    }
}
