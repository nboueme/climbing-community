package com.nicolasboueme.climbing.webapp.servlets;

import com.nicolasboueme.climbing.business.SpotBusiness;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Spot extends HttpServlet {
    private SpotBusiness webappToConsumer = new SpotBusiness();

    @Override
    public void init() throws ServletException {
        webappToConsumer.initDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("spotList", webappToConsumer.getSpotDao().listSpot());
        this.getServletContext().getRequestDispatcher("/WEB-INF/climbing.jsp").forward(request, response);
    }
}
