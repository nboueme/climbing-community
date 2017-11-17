package com.nicolasboueme.climbing.webapp.servlet;

import com.nicolasboueme.climbing.business.TopoBusiness;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Topo extends HttpServlet {
    private TopoBusiness webappToConsumer = new TopoBusiness();

    @Override
    public void init() throws ServletException {
        webappToConsumer.initDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("topoList", webappToConsumer.getTopoDao().listTopo());
        this.getServletContext().getRequestDispatcher("/WEB-INF/topo.jsp").forward(request, response);
    }
}
