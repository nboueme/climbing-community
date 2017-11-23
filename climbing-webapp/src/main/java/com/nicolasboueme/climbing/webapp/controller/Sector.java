package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.SectorManager;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Sector extends AbstractResource {
    private SectorManager webappToConsumer = getManagerFactory().getSectorManager();

    @GetMapping("/climbing/{spotId}")
    public String listSectorsFromParent(final ModelMap modelMap, @PathVariable("spotId") final String spotId) {
        modelMap.addAttribute("sectorList", webappToConsumer.listSectorsFromParent(Integer.parseInt(spotId)));
        return "sector";
    }
}
