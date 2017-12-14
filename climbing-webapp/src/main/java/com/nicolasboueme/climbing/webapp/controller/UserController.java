package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.UserManager;
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
import javax.servlet.http.HttpSession;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class UserController extends AbstractResource {
    private UserManager webappToConsumer = getManagerFactory().getUserManager();

    @GetMapping("/inscription")
    public String inscription(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/");
        }
        return "user/inscription";
    }

    @PostMapping("/inscription")
    public String addUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserAccount user = new UserAccount();
        user.setPseudo(request.getParameter("pseudo"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        webappToConsumer.registerNewUserAccount(user);

        UserAccount userQuery = webappToConsumer.userLogin(user);

        HttpSession session = request.getSession();
        session.setAttribute("user", userQuery);
        response.sendRedirect(request.getContextPath() + "/");

        return "user/inscription";
    }

    @GetMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/");
        }
        return "user/login";
    }

    @PostMapping("/login")
    public String userLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserAccount user = new UserAccount();
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));

        UserAccount userQuery = webappToConsumer.userLogin(user);

        HttpSession session = request.getSession();

        if (request.getParameter("remember") != null && userQuery != null) {
            session.setAttribute("user", userQuery);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            session.invalidate();
        }

        return "user/login";
    }

    @GetMapping("/account/{userId}")
    public String userAccount(@PathVariable("userId") String userId, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
        }

        modelMap.addAttribute("currentURI", request.getRequestURI());

        return "user/account";
    }

    @PostMapping("/account/{userId}/picture-delete")
    public void deleteUserPicture(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserAccount userSession = (UserAccount) session.getAttribute("user");

        String defaultPictureURI = "/image/user/user-0.png";

        if (!userSession.getImageUrl().equals(defaultPictureURI)) {
            File file = new File("/Users/nicolasboueme/p3-climbing" + userSession.getImageUrl());
            file.delete();
            userSession.setImageUrl(defaultPictureURI);
        }

        webappToConsumer.deleteUserPicture(userSession);

        response.sendRedirect(request.getParameter("current_uri"));
    }

    @PostMapping("/account/{userId}")
    public String updateUser(@PathVariable("userId") String userId, @RequestParam("picture") MultipartFile file, ModelMap modelMap, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        UserAccount userSession = (UserAccount) session.getAttribute("user");
        userSession.setPseudo(request.getParameter("pseudo"));
        userSession.setEmail(request.getParameter("email"));
        userSession.setPassword(request.getParameter("new_password"));

        if (!file.isEmpty()) {
            byte[] bytes = file.getBytes();

            // Creating the directory to store file
            String rootPath = "/Users/nicolasboueme/p3-climbing/image";
            File dir = new File(rootPath + File.separator + "user");
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator + "user-" + userSession.getId() + ".jpg");
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            userSession.setImageUrl("/image/user/" + serverFile.getName());
        } else {
            userSession.setImageUrl(userSession.getImageUrl());
        }

        modelMap.addAttribute("currentURI", request.getRequestURI());

        if (request.getParameter("last_password").equals(userSession.getPassword())) {
            webappToConsumer.updateUser(userSession);
        } else {
            System.out.println("Mauvais mot de passe !");
        }

        return "user/account";
    }

    @PostMapping("/account/{userId}/delete")
    public void deleteUser(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        UserAccount userSession = (UserAccount) session.getAttribute("user");

        String defaultPictureURI = "/image/user/user-0.png";

        if (!userSession.getImageUrl().equals(defaultPictureURI)) {
            File file = new File("/Users/nicolasboueme/p3-climbing" + userSession.getImageUrl());
            file.delete();
            userSession.setImageUrl(defaultPictureURI);
        }

        webappToConsumer.deleteUser(userSession);

        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }

    @GetMapping("/logout")
    public void userLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }
}
