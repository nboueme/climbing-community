package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.TopoManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Topo;
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
public class TopoController extends AbstractResource {
    private TopoManager webappToConsumer = getManagerFactory().getTopoManager();
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @GetMapping("/topo")
    public String listTopo(ModelMap modelMap) {
        modelMap.addAttribute("topoList", webappToConsumer.listTopo());
        return "topo";
    }

    @PostMapping("/topo")
    public void addTopo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Topo topo = new Topo();
        topo.setUserAccountId(((UserAccount) request.getSession().getAttribute("user")).getId());
        topo.setName(request.getParameter("name"));
        topo.setDescription(request.getParameter("description"));

        webappToConsumer.addTopo(topo);
        response.sendRedirect(request.getContextPath() + "/topo");
    }

    @GetMapping("/topo/{topoId}")
    public String getTopo(ModelMap modelMap, @PathVariable String topoId, HttpServletRequest request) {
        Topo topo = new Topo();
        topo.setPublicationId(Integer.parseInt(topoId));

        Comment comment = new Comment();
        comment.setPublicationId(Integer.parseInt(topoId));

        modelMap.addAttribute("currentURI", request.getRequestURI());
        modelMap.addAttribute("publicationId", topoId);
        modelMap.addAttribute("topo", webappToConsumer.getTopo(topo));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return "topo_item";
    }

    @PostMapping("/topo/{topoId}/update")
    public void updateTopo(@PathVariable String topoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Topo topo = new Topo();
        topo.setPublicationId(Integer.parseInt(topoId));
        topo.setName(request.getParameter("name"));
        topo.setDescription(request.getParameter("description"));

        webappToConsumer.updateTopo(topo);
        response.sendRedirect(request.getContextPath() + "/topo");
    }

    @PostMapping("/topo/{topoId}/delete")
    public void deleteTopo(@PathVariable String topoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Topo topo = new Topo();
        topo.setPublicationId(Integer.parseInt(topoId));

        webappToConsumer.deleteTopo(topo);
        response.sendRedirect(request.getContextPath() + "/topo");
    }
}
