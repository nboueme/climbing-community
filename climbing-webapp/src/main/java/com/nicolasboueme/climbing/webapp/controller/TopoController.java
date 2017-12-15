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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView listTopo(ModelMap modelMap) {
        modelMap.addAttribute("topoList", webappToConsumer.listTopo());
        return new ModelAndView("topo", "topo", new Topo());
    }

    @PostMapping("/topo")
    public String addTopo(@ModelAttribute Topo topo, @SessionAttribute UserAccount user) {
        topo.setUserAccountId(user.getId());

        webappToConsumer.addTopo(topo);
        return "redirect:/topo";
    }

    @PostMapping("/topo/{topoId}/update")
    public String updateTopo(@ModelAttribute Topo topo, @PathVariable String topoId, @RequestParam String description, @RequestParam MultipartFile file, @RequestParam String currentPicture) throws IOException {
        topo.setPublicationId(Integer.parseInt(topoId));
        topo.setDescription(description);

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file
            String rootPath = "/Users/nicolasboueme/p3-climbing/image";
            File dir = new File(rootPath + File.separator + "topo");
            if (!dir.exists())
                //noinspection ResultOfMethodCallIgnored
                dir.mkdirs();

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + "topo-" + topo.getPublicationId() + ".jpg");
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            topo.setImageUrl("/image/topo/" + serverFile.getName());
        } else {
            topo.setImageUrl(currentPicture);
        }

        webappToConsumer.updateTopo(topo);
        return "redirect:/topo";
    }

    @PostMapping("/topo/{topoId}/picture-delete")
    public String deleteTopoPicture(@ModelAttribute Topo topo, @PathVariable String topoId, @RequestParam String picture) {
        topo.setPublicationId(Integer.parseInt(topoId));
        topo.setImageUrl(picture);

        File file = new File("/Users/nicolasboueme/p3-climbing" + topo.getImageUrl());
        //noinspection ResultOfMethodCallIgnored
        file.delete();

        webappToConsumer.deleteTopoPicture(topo);

        return "redirect:/topo/" + topoId;
    }

    @PostMapping("/topo/{topoId}/delete")
    public String deleteTopo(@ModelAttribute Topo topo, @PathVariable String topoId, @RequestParam String picture) {
        topo.setPublicationId(Integer.parseInt(topoId));
        topo.setImageUrl(picture);

        File file = new File("/Users/nicolasboueme/p3-climbing" + topo.getImageUrl());
        //noinspection ResultOfMethodCallIgnored
        file.delete();

        webappToConsumer.deleteTopo(topo);
        return "redirect:/topo";
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

    @PostMapping("/topo-spot/{topoId}")
    public String addTopoHasSpot(@PathVariable String topoId, @RequestParam int spotId) {
        Topo topo = new Topo();
        topo.setPublicationId(Integer.parseInt(topoId));

        Spot spot = new Spot();
        spot.setPublicationId(spotId);

        webappToConsumer.addTopoHasSpot(topo, spot);
        return "redirect:/topo/" + topoId;
    }

    @PostMapping("/topo-spot/{spotId}/delete")
    public String deleteTopoHasSpot(@PathVariable String spotId, @RequestParam int topoId) {
        Topo topo = new Topo();
        topo.setPublicationId(topoId);

        Spot spot = new Spot();
        spot.setPublicationId(Integer.parseInt(spotId));

        webappToConsumer.deleteTopoHastSpot(topo, spot);
        return "redirect:/topo/" + topoId;
    }

    @PostMapping("/user-topo/{topoId}")
    public String addUserHasTopo(@PathVariable String topoId, @SessionAttribute UserAccount user) {
        user.setTopo(new Topo());
        user.getTopo().setPublicationId(Integer.parseInt(topoId));

        webappToConsumer.addUserHasTopo(user);
        return "redirect:/topo/" + topoId;
    }

    @PostMapping("/user-topo/{topoId}/update")
    public String updateUserHasTopo(@PathVariable String topoId, @SessionAttribute UserAccount user, HttpServletRequest request) throws ParseException {
        user.setId(user.getId());
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
        return "redirect:/topo/" + topoId;
    }

    @PostMapping("/user-topo/{topoId}/delete")
    public String deleteUserHasTopo(@PathVariable String topoId, @SessionAttribute UserAccount user) {
        user.setId(user.getId());
        user.setTopo(new Topo());
        user.getTopo().setPublicationId(Integer.parseInt(topoId));

        webappToConsumer.deleteUserHasTopo(user);
        return "redirect:/topo/" + topoId;
    }
}
