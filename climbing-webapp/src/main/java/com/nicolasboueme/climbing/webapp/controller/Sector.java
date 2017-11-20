package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.manager.SectorManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/climbing/sector")
public class Sector {
    private SectorManager webappToConsumer = new SectorManager();

    @RequestMapping(method = RequestMethod.GET)
    public String listSectorsFromParent(final ModelMap modelMap, @RequestParam("id") String id) {
        webappToConsumer.initDao();
        modelMap.addAttribute("sectorList", webappToConsumer.getSectorDao().listSectorsFromParent(Integer.parseInt(id)));
        return "sector";
    }
}
