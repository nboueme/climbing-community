package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.TopoManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/topo/{topoId}")
public class TopoItem {
    private TopoManager webappToConsumer = new TopoManager();

    @RequestMapping(method = RequestMethod.GET)
    public String getTopo(final ModelMap modelMap, @PathVariable("topoId") final String topoId) {
        webappToConsumer.initDao();
        modelMap.addAttribute("topo", webappToConsumer.getTopoDao().getTopo(Integer.parseInt(topoId)));
        return "topo_item";
    }
}
