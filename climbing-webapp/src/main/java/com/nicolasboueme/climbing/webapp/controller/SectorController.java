package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.SectorManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Sector;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        modelMap.addAttribute("spotId", spotId);
        modelMap.addAttribute("sectorList", webappToConsumer.listSectorsFromParent(sector));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return "sector";
    }

    @PostMapping("/climbing/{spotId}")
    public void addSector(@PathVariable String spotId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Sector sector = new Sector();
        sector.setUserAccountId(((UserAccount) request.getSession().getAttribute("user")).getId());
        sector.setName(request.getParameter("name"));
        sector.setSpotId(Integer.parseInt(spotId));
        sector.setHeight(Integer.parseInt(request.getParameter("height")));

        webappToConsumer.addSector(sector);
        response.sendRedirect(request.getContextPath() + "/climbing/" + spotId);
    }

    @PostMapping("/climbing/{spotId}/sector/{sectorId}/update")
    public void updateSector(@PathVariable String spotId, @PathVariable String sectorId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Sector sector = new Sector();
        sector.setPublicationId(Integer.parseInt(sectorId));
        sector.setName(request.getParameter("name"));
        sector.setHeight(Integer.parseInt(request.getParameter("height")));

        webappToConsumer.updateSector(sector);
        response.sendRedirect(request.getContextPath() + "/climbing/" + spotId);
    }

    @PostMapping("/climbing/{spotId}/sector/{sectorId}/delete")
    public void deleteSector(@PathVariable String spotId, @PathVariable String sectorId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Sector sector = new Sector();
        sector.setPublicationId(Integer.parseInt(sectorId));

        webappToConsumer.deleteSector(sector);
        response.sendRedirect(request.getContextPath() + "/climbing/" + spotId);
    }
}
