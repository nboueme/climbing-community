package com.nicolasboueme.climbing.webapp.servlets;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.RouteDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Route extends HttpServlet {
    private RouteDao routeDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        routeDao = daoFactory.getRouteDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("routeList", routeDao.listRoute());
        this.getServletContext().getRequestDispatcher("/WEB-INF/route.jsp").forward(request, response);
    }
}
