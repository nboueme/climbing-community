package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.SectorManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Sector;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SectorController extends AbstractResource {
    private SectorManager webappToConsumer = getManagerFactory().getSectorManager();
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @GetMapping("/climbing/{spotId}")
    public ModelAndView listSectorsFromParent(ModelMap modelMap, @PathVariable String spotId, HttpServletRequest request) {
        Sector sector = new Sector();
        sector.setSpotId(Integer.parseInt(spotId));

        Comment comment = new Comment();
        comment.setPublicationId(Integer.parseInt(spotId));

        modelMap.addAttribute("currentURI", request.getRequestURI());
        modelMap.addAttribute("publicationId", spotId);
        modelMap.addAttribute("sectorList", webappToConsumer.listSectorsFromParent(sector));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return new ModelAndView("sector", "sector", new Sector());
    }

    @PostMapping("/climbing/{spotId}")
    public String addSector(@ModelAttribute Sector sector, @SessionAttribute UserAccount user, @PathVariable String spotId) {
        sector.setUserAccountId(user.getId());
        sector.setSpotId(Integer.parseInt(spotId));

        webappToConsumer.addSector(sector);
        return "redirect:/climbing/" + spotId;
    }

    @PostMapping("/climbing/{spotId}/sector/{sectorId}/update")
    public String updateSector(@ModelAttribute Sector sector, @PathVariable String spotId, @PathVariable String sectorId) {
        sector.setPublicationId(Integer.parseInt(sectorId));

        webappToConsumer.updateSector(sector);
        return "redirect:/climbing/" + spotId;
    }

    @PostMapping("/climbing/{spotId}/sector/{sectorId}/delete")
    public String deleteSector(@ModelAttribute Sector sector, @PathVariable String spotId, @PathVariable String sectorId) {
        sector.setPublicationId(Integer.parseInt(sectorId));

        webappToConsumer.deleteSector(sector);
        return "redirect:/climbing/" + spotId;
    }
}
