package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.SectorManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Sector;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class SectorController extends AbstractResource {
    private SectorManager webappToConsumer = getManagerFactory().getSectorManager();
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @GetMapping("/climbing/{spotId}")
    public String listSectorsFromParent(final ModelMap modelMap, @PathVariable String spotId) {
        Sector sector = new Sector();
        sector.setSpotId(Integer.parseInt(spotId));

        Comment comment = new Comment();
        comment.setPublicationId(Integer.parseInt(spotId));

        modelMap.addAttribute("sectorList", webappToConsumer.listSectorsFromParent(sector));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return "sector";
    }
}
