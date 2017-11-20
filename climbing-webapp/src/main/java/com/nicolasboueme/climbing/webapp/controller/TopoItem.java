package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.TopoManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/topo/item")
public class TopoItem {
    private TopoManager webappToConsumer = new TopoManager();

    @RequestMapping(method = RequestMethod.GET)
    public String getTopo(final ModelMap modelMap, @RequestParam("id") String id) {
        webappToConsumer.initDao();
        modelMap.addAttribute("topo", webappToConsumer.getTopoDao().getTopo(Integer.parseInt(id)));
        return "topo_item";
    }
}
