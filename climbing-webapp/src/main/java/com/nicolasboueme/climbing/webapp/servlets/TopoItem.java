package com.nicolasboueme.climbing.webapp.servlets;

import com.nicolasboueme.climbing.business.TopoBusiness;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TopoItem extends HttpServlet {
    private TopoBusiness webappToConsumer = new TopoBusiness();

    @Override
    public void init() throws ServletException {
        webappToConsumer.initDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("topo", webappToConsumer.getTopoDao().getTopo());
        this.getServletContext().getRequestDispatcher("/WEB-INF/topo_item.jsp").forward(request, response);
    }
}
