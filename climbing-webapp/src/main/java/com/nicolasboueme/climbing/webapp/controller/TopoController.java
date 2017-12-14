package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.business.contract.manager.TopoManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.Spot;
import com.nicolasboueme.climbing.model.entity.Topo;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

        if (request.getSession().getAttribute("user") != null)
            topo.setUserAccountId(((UserAccount) request.getSession().getAttribute("user")).getId());

        topo.setPublicationId(Integer.parseInt(topoId));

        Comment comment = new Comment();
        comment.setPublicationId(Integer.parseInt(topoId));

        modelMap.addAttribute("currentURI", request.getRequestURI());
        modelMap.addAttribute("publicationId", topoId);
        modelMap.addAttribute("topo", webappToConsumer.getTopo(topo));
        modelMap.addAttribute("notRelatedSpots", webappToConsumer.getNotRelatedSpots(topo));
        modelMap.addAttribute("topoHasSpots", webappToConsumer.getTopoHasSpot(topo));
        modelMap.addAttribute("notRelatedUser", webappToConsumer.getNotRelatedUser(topo));
        modelMap.addAttribute("userHasTopos", webappToConsumer.getUserHasTopo(topo));
        modelMap.addAttribute("parentsComments", comments.getParentsComments(comment));
        modelMap.addAttribute("childrenComments", comments.getChildrenComments(comment));
        return "topo_item";
    }

    @PostMapping("/topo/{topoId}/picture-delete")
    public void deleteTopoPicture(@PathVariable String topoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Topo topo = new Topo();
        topo.setPublicationId(Integer.parseInt(topoId));
        topo.setImageUrl(request.getParameter("picture"));

        File file = new File("/Users/nicolasboueme/p3-climbing" + topo.getImageUrl());
        file.delete();

        webappToConsumer.deleteTopoPicture(topo);

        response.sendRedirect(request.getParameter("current_uri"));
    }

    @PostMapping("/topo/{topoId}/update")
    public void updateTopo(@PathVariable String topoId, @RequestParam("picture") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Topo topo = new Topo();
        topo.setPublicationId(Integer.parseInt(topoId));
        topo.setName(request.getParameter("name"));
        topo.setDescription(request.getParameter("description"));

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file
            String rootPath = "/Users/nicolasboueme/p3-climbing/image";
            File dir = new File(rootPath + File.separator + "topo");
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + "topo-" + topo.getPublicationId() + ".jpg");
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            topo.setImageUrl("/image/topo/" + serverFile.getName());
        } else {
            topo.setImageUrl(request.getParameter("current_picture"));
        }

        webappToConsumer.updateTopo(topo);
        response.sendRedirect(request.getContextPath() + "/topo");
    }

    @PostMapping("/topo/{topoId}/delete")
    public void deleteTopo(@PathVariable String topoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Topo topo = new Topo();
        topo.setPublicationId(Integer.parseInt(topoId));
        topo.setImageUrl(request.getParameter("picture"));

        File file = new File("/Users/nicolasboueme/p3-climbing" + topo.getImageUrl());
        file.delete();

        webappToConsumer.deleteTopo(topo);
        response.sendRedirect(request.getContextPath() + "/topo");
    }

    @PostMapping("/topo-spot")
    public void addTopoHasSpot(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Topo topo = new Topo();
        topo.setPublicationId(Integer.parseInt(request.getParameter("topo_id")));

        Spot spot = new Spot();
        spot.setPublicationId(Integer.parseInt(request.getParameter("spot")));

        webappToConsumer.addTopoHasSpot(topo, spot);
        response.sendRedirect(request.getParameter("current_uri"));
    }

    @PostMapping("/topo-spot/{spotId}/delete")
    public void deleteTopoHasSpot(@PathVariable String spotId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Topo topo = new Topo();
        topo.setPublicationId(Integer.parseInt(request.getParameter("topo_id")));

        Spot spot = new Spot();
        spot.setPublicationId(Integer.parseInt(spotId));

        webappToConsumer.deleteTopoHastSpot(topo, spot);
        response.sendRedirect(request.getParameter("current_uri"));
    }

    @PostMapping("/user-topo/{topoId}")
    public void addUserHasTopo(@PathVariable String topoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserAccount user = new UserAccount();
        user.setId(((UserAccount) request.getSession().getAttribute("user")).getId());
        user.setTopo(new Topo());
        user.getTopo().setPublicationId(Integer.parseInt(topoId));

        webappToConsumer.addUserHasTopo(user);

        response.sendRedirect(request.getParameter("current_uri"));
    }

    @PostMapping("/user-topo/{topoId}/update")
    public void updateUserHasTopo(@PathVariable String topoId, HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        UserAccount user = new UserAccount();
        user.setId(((UserAccount) request.getSession().getAttribute("user")).getId());
        user.setTopo(new Topo());
        user.getTopo().setPublicationId(Integer.parseInt(topoId));

        if (request.getParameter("loaned") != null) user.getTopo().setLoaned(true);
        else user.getTopo().setLoaned(false);

        String expectedPattern = "yyyy-MM-dd";
        SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);
        String borrowingDate = request.getParameter("borrowing_date");
        String returnDate = request.getParameter("return_date");

        if (!borrowingDate.equals("")) user.getTopo().setBorrowingDate(formatter.parse(borrowingDate));
        else user.getTopo().setBorrowingDate(null);
        if (!returnDate.equals("")) user.getTopo().setReturnDate(formatter.parse(returnDate));
        else user.getTopo().setReturnDate(null);

        webappToConsumer.updateUserHasTopo(user);
        response.sendRedirect(request.getParameter("current_uri"));
    }

    @PostMapping("/user-topo/{topoId}/delete")
    public void deleteUserHasTopo(@PathVariable String topoId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserAccount user = new UserAccount();
        user.setId(((UserAccount) request.getSession().getAttribute("user")).getId());
        user.setTopo(new Topo());
        user.getTopo().setPublicationId(Integer.parseInt(topoId));

        webappToConsumer.deleteUserHasTopo(user);
        response.sendRedirect(request.getParameter("current_uri"));
    }
}
