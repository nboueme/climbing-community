package com.nicolasboueme.climbing.webapp.servlets;

import com.nicolasboueme.climbing.business.RouteBusiness;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RouteItem extends HttpServlet {
    private RouteBusiness webappToConsumer = new RouteBusiness();

    @Override
    public void init() throws ServletException {
        webappToConsumer.initDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("route", webappToConsumer.getRouteDao().getRoute(Integer.parseInt(request.getParameter("id"))));
        this.getServletContext().getRequestDispatcher("/WEB-INF/route_item.jsp").forward(request, response);
    }
}
