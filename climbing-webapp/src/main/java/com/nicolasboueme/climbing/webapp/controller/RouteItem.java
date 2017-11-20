package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.RouteManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/climbing/route/item")
public class RouteItem {
    private RouteManager webappToConsumer = new RouteManager();

    @RequestMapping(method = RequestMethod.GET)
    public String getRoute(final ModelMap modelMap, @RequestParam("id") String id) {
        webappToConsumer.initDao();
        modelMap.addAttribute("route", webappToConsumer.getRouteDao().getRoute(Integer.parseInt(id)));
        return "route_item";
    }
}
