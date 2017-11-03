package com.nicolasboueme.climbing.webapp.servlets;

import com.nicolasboueme.climbing.consumer.dao.DaoFactory;
import com.nicolasboueme.climbing.consumer.dao.TopoDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Topo extends HttpServlet {
    private TopoDao topoDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.topoDao = daoFactory.getTopoDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("topoList", topoDao.listTopo());
        this.getServletContext().getRequestDispatcher("/WEB-INF/topo.jsp").forward(request, response);
    }
}
