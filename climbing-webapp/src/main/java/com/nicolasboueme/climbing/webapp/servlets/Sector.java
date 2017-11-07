package com.nicolasboueme.climbing.webapp.servlets;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.SectorDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Sector extends HttpServlet {
    private SectorDao sectorDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        sectorDao = daoFactory.getSectorDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("sectorList", sectorDao.listSector());
        this.getServletContext().getRequestDispatcher("/WEB-INF/sector.jsp").forward(request, response);
    }
}
