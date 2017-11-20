package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.RouteManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/climbing/route")
public class Route {
    private RouteManager webappToConsumer = new RouteManager();

    @RequestMapping(method = RequestMethod.GET)
    public String listRoutesFromParent(final ModelMap modelMap, @RequestParam("id") String id) {
        webappToConsumer.initDao();
        modelMap.addAttribute("routeList", webappToConsumer.getRouteDao().listRoutesFromParent(Integer.parseInt(id)));
        return "route";
    }
}
