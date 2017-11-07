package com.nicolasboueme.climbing.webapp.servlets;

import com.nicolasboueme.climbing.business.SearchBusiness;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Search extends HttpServlet {
    private SearchBusiness webappToConsumer = new SearchBusiness();

    @Override
    public void init() {
        webappToConsumer.initDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String publicationName = request.getParameter("publication_name");
        request.setAttribute("publication_name", publicationName);
        request.setAttribute("publicationList", webappToConsumer.getPublicationDao().listPublication());

        this.getServletContext().getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
    }
}
