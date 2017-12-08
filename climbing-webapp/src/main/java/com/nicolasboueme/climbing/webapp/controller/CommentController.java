package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CommentController extends AbstractResource {
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @PostMapping("/comment/{parentId}")
    public void addComment(@PathVariable String parentId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Comment comment = new Comment();
        comment.setUserAccountId(((UserAccount) request.getSession().getAttribute("user")).getId());
        comment.setPublicationId(Integer.parseInt(request.getParameter("publication_id")));
        if (parentId != null) comment.setParentId(Integer.parseInt(parentId));
        comment.setContent(request.getParameter("content"));

        comments.addComment(comment);
        response.sendRedirect(request.getParameter("current_uri"));
    }

    @PostMapping("/comment/{commentId}/update")
    public void updateComment(@PathVariable String commentId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(commentId));
        comment.setContent(request.getParameter("content"));

        comments.updateComment(comment);
        response.sendRedirect(request.getParameter("current_uri"));
    }

    @PostMapping("/comment/{commentId}/delete")
    public void deleteComment(@PathVariable String commentId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(commentId));
        if (request.getParameter("parent_id") != null)
            comment.setParentId(Integer.parseInt(request.getParameter("parent_id")));

        comments.deleteComment(comment);
        response.sendRedirect(request.getParameter("current_uri"));
    }
}
