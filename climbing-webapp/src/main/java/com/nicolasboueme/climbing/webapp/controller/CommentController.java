package com.nicolasboueme.climbing.webapp.controller;

import com.nicolasboueme.climbing.business.contract.manager.PublicationManager;
import com.nicolasboueme.climbing.model.entity.Comment;
import com.nicolasboueme.climbing.model.entity.UserAccount;
import com.nicolasboueme.climbing.webapp.resource.AbstractResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class CommentController extends AbstractResource {
    private PublicationManager comments = getManagerFactory().getPublicationManager();

    @PostMapping("/comment/{parentId}")
    public String addComment(@PathVariable String parentId, @SessionAttribute UserAccount user, @RequestParam int publicationId, @RequestParam String content, @RequestParam String currentURI) {
        Comment comment = new Comment();
        comment.setUserAccountId(user.getId());
        comment.setPublicationId(publicationId);
        if (parentId != null) comment.setParentId(Integer.parseInt(parentId));
        comment.setContent(content);

        comments.addComment(comment);
        return "redirect:" + currentURI;
    }

    @PostMapping("/comment/{commentId}/update")
    public String updateComment(@PathVariable String commentId, @RequestParam String content, @RequestParam String currentURI) {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(commentId));
        comment.setContent(content);

        comments.updateComment(comment);
        return "redirect:" + currentURI;
    }

    @PostMapping("/comment/{commentId}/delete")
    public String deleteComment(@PathVariable String commentId, @RequestParam String currentURI) {
        Comment comment = new Comment();
        comment.setId(Integer.parseInt(commentId));

        comments.deleteComment(comment);
        return "redirect:" + currentURI;
    }
}
