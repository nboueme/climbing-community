<h4>Commentaires</h4>
<c:choose>
    <c:when test="${ parentsComments.size() > 0 }">
        <div class="comment-list">
            <c:forEach var="parentComment" items="${ parentsComments }">
                <div class="media thumbnail">
                    <a class="pull-left option-cursor">
                        <img class="media-object comment-object" src="${ parentComment.author.imageUrl }">
                    </a>
                    <div class="media-body">
                        <div><a class="option-cursor"><b>${ parentComment.author.pseudo }</b></a> ${ parentComment.content }</div>
                        <div>
                            <c:if test="${ !empty sessionScope.user }">
                                <a class="option-cursor" data-toggle="collapse" data-target=".collapse-reply-${parentComment.id}">Répondre</a><span class="comment-point"> · </span>

                                <c:if test="${ sessionScope.user.id == parentComment.userAccountId }">
                                    <a class="option-cursor" data-toggle="modal" data-target=".modal-parent-${ parentComment.id }" data-backdrop="static" data-keyboard="false">Modifier</a><span class="comment-point"> · </span>
                                </c:if>

                                <c:if test="${ sessionScope.user.role == 'admin' }">
                                    <form hidden method="post" action="/comment/${parentComment.id}/delete" class="parent-comment-delete${ parentComment.id }">
                                        <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                                    </form>
                                    <a class="option-cursor" onclick="$('.parent-comment-delete${ parentComment.id }').submit();">Supprimer</a><span class="comment-point"> · </span>
                                </c:if>
                            </c:if>
                            <span class="comment-point">${ !empty parentComment.updatedAt ? parentComment.updatedAt : parentComment.createdAt }</span>
                        </div>

                        <c:if test="${ childrenComments.size() > 0 }">
                            <div class="reply-border-left">
                                <c:forEach var="childComment" items="${ childrenComments }">
                                    <c:if test="${ childComment.parentId == parentComment.id }">
                                        <div class="reply-list">
                                            <a class="pull-left pull-left-child option-cursor">
                                                <img class="media-object comment-object-child" src="${ childComment.author.imageUrl }">
                                            </a>
                                            <div class="media-body">
                                                <div><a href=""><b>${ childComment.author.pseudo }</b></a> ${ childComment.content }</div>
                                                <div>
                                                    <c:if test="${ !empty sessionScope.user }">
                                                        <a class="option-cursor" data-toggle="collapse" data-target=".collapse-reply-${childComment.parentId}">Répondre</a><span class="comment-point"> · </span>

                                                        <c:if test="${ sessionScope.user.id == childComment.userAccountId }">
                                                            <a class="option-cursor" data-toggle="modal" data-target=".modal-child-${ childComment.id }" data-backdrop="static" data-keyboard="false">Modifier</a><span class="comment-point"> · </span>
                                                        </c:if>

                                                        <c:if test="${ sessionScope.user.role == 'admin' }">
                                                            <form hidden method="post" action="/comment/${childComment.id}/delete" class="child-comment-delete${ childComment.id }">
                                                                <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                                                            </form>
                                                            <a class="option-cursor" title="Delete" onclick="$('.child-comment-delete${ childComment.id }').submit();">Supprimer</a><span class="comment-point"> · </span>
                                                        </c:if>
                                                    </c:if>
                                                    <span class="comment-point">${ !empty childComment.updatedAt ? childComment.updatedAt : childComment.createdAt }</span>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>

                                    <c:if test="${ !empty sessionScope.user && childComment.parentId == parentComment.id }">
                                        <!-- Child Comment UPDATE -->
                                        <div class="modal fade modal-child-${ childComment.id }">
                                            <div class="modal-dialog">
                                                <div class="modal-content">

                                                    <div class="modal-header">
                                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                        <h4 class="modal-title">Modification du commentaire</h4>
                                                    </div>

                                                    <div class="modal-body">
                                                        <form method="post" action="/comment/${childComment.id}/update" class="form-horizontal publication-update-${childComment.id}">
                                                            <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                                                            <input required type="text" class="form-control" name="content" placeholder="Votre commentaire..." value="${ childComment.content }" />
                                                        </form>
                                                    </div>

                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                        <button type="button" class="btn btn-primary" onclick="$('.publication-update-${childComment.id}').submit();">Save changes</button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </c:if>

                    </div>

                    <!-- Child Comment CREATE -->
                    <c:if test="${ !empty sessionScope.user }">
                        <form method="post" action="/comment/${parentComment.id}" class="form-horizontal collapse collapse-reply-${parentComment.id}">
                            <div class="input-group">
                                <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                                <input hidden name="publication_id" title="publication_id" value="${ publicationId }" />
                                <input required type="text" class="form-control" name="content" placeholder="Votre réponse..."/>
                                <span class="input-group-btn">
                                    <button type="submit" class="btn btn-default">Envoyer</button>
                                </span>
                            </div>
                        </form>
                    </c:if>
                </div>

                <c:if test="${ !empty sessionScope.user }">
                    <!-- Parent Comment UPDATE -->
                    <div class="modal fade modal-parent-${ parentComment.id }">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <h4 class="modal-title">Modification du commentaire</h4>
                                </div>

                                <div class="modal-body">
                                    <form method="post" action="/comment/${parentComment.id}/update" class="form-horizontal publication-update-${ parentComment.id }">
                                        <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                                        <input required type="text" class="form-control" name="content" placeholder="Votre commentaire..." value="${ parentComment.content }" />
                                    </form>
                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="button" class="btn btn-primary" onclick="$('.publication-update-${ parentComment.id }').submit();">Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>

            <!-- Parent Comment CREATE -->
            <c:if test="${ !empty sessionScope.user }">
                <form method="post" action="/comment/0" class="form-horizontal">
                    <div class="input-group">
                        <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                        <input hidden name="publication_id" title="publication_id" value="${ publicationId }" />
                        <input required type="text" class="form-control" name="content" placeholder="Votre commentaire..."/>
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-default">Envoyer</button>
                        </span>
                    </div>
                </form>
            </c:if>
        </div>
    </c:when>
    <c:otherwise>
        <div class="comment-list">
            <div class="media thumbnail">
                Il n'y a encore aucun commentaire pour cette publication.
            </div>
            <!-- Parent Comment CREATE -->
            <c:if test="${ !empty sessionScope.user }">
                <form method="post" action="/comment/0" class="form-horizontal">
                    <div class="input-group">
                        <input hidden name="current_uri" title="current_uri" value="${ currentURI }" />
                        <input hidden name="publication_id" title="publication_id" value="${ publicationId }" />
                        <input required type="text" class="form-control" name="content" placeholder="Votre commentaire..."/>
                        <span class="input-group-btn">
                            <button type="submit" class="btn btn-default">Envoyer</button>
                        </span>
                    </div>
                </form>
            </c:if>
        </div>
    </c:otherwise>
</c:choose>
