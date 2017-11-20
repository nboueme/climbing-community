package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.TopoManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/topo")
public class Topo {
    private TopoManager webappToConsumer = new TopoManager();

    @RequestMapping(method = RequestMethod.GET)
    public String listTopo(final ModelMap modelMap) {
        webappToConsumer.initDao();
        modelMap.addAttribute("topoList", webappToConsumer.getTopoDao().listTopo());
        return "topo";
    }
}
