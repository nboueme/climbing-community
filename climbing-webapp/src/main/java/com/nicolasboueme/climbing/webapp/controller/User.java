package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.UserManager;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class User extends AbstractResource {
    private UserManager webappToConsumer = getManagerFactory().getUserManager();

    @GetMapping("/login")
    public ModelAndView userLogin() {
        return new ModelAndView("login", "getUser", new UserAccount());
    }

    @PostMapping("/login")
    public String userLogin(@ModelAttribute("getUser") UserAccount user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserAccount userAccount = webappToConsumer.userLogin(user.getPseudo(), user.getPassword());

        HttpSession session = request.getSession();

        if (request.getParameter("remember") != null) {
            session.setAttribute("user", userAccount);
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            session.invalidate();
        }

        return "login";
    }

    @GetMapping("/logout")
    public void userLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect(request.getContextPath() + "/");
    }

    @GetMapping("/inscription")
    public  ModelAndView addUser() {
        return new ModelAndView("inscription", "addUser", new UserAccount());
    }

    @PostMapping("/inscription")
    public String addUser(@ModelAttribute("addUser") UserAccount user, HttpServletRequest request, HttpServletResponse response) throws IOException {
        webappToConsumer.registerNewUserAccount(user);

        UserAccount userAccount = webappToConsumer.userLogin(user.getPseudo(), user.getPassword());

        HttpSession session = request.getSession();
        session.setAttribute("user", userAccount);
        response.sendRedirect(request.getContextPath() + "/");

        return "inscription";
    }
}
