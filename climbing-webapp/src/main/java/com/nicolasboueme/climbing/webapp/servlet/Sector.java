package com.nicolasboueme.climbing.webapp.servlet;

import com.nicolasboueme.climbing.business.SectorBusiness;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Sector extends HttpServlet {
    private SectorBusiness webappToConsumer = new SectorBusiness();

    @Override
    public void init() throws ServletException {
        webappToConsumer.initDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sectorList", webappToConsumer.getSectorDao().listSectorsFromParent(Integer.parseInt(request.getParameter("id"))));
        this.getServletContext().getRequestDispatcher("/WEB-INF/sector.jsp").forward(request, response);
    }
}
