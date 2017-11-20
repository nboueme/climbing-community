package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.SectorManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/climbing/{spotId}")
public class Sector {
    private SectorManager webappToConsumer = new SectorManager();

    @RequestMapping(method = RequestMethod.GET)
    public String listSectorsFromParent(final ModelMap modelMap, @PathVariable("spotId") final String spotId) {
        webappToConsumer.initDao();
        modelMap.addAttribute("sectorList", webappToConsumer.getSectorDao().listSectorsFromParent(Integer.parseInt(spotId)));
        return "sector";
    }
}
