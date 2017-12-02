package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.SectorManager;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class Sector extends AbstractResource {
    private SectorManager webappToConsumer = getManagerFactory().getSectorManager();
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @GetMapping("/climbing/{spotId}")
    public String listSectorsFromParent(final ModelMap modelMap, @PathVariable String spotId) {
        modelMap.addAttribute("sectorList", webappToConsumer.listSectorsFromParent(Integer.parseInt(spotId)));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(Integer.parseInt(spotId)));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(Integer.parseInt(spotId)));
        return "sector";
    }
}
