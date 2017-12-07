<h4>Commentaires</h4>
<c:choose>
    <c:when test="${ parentsComments.size() > 0 }">
    <div class="comment-list">
            <c:forEach var="parentComment" items="${ parentsComments }">
                <div class="media thumbnail">
                    <a class="pull-left comment-object" href="#">
                        <img class="media-object" src="${ parentComment.author.imageUrl }">
                    </a>
                    <div class="media-body">
                        <div><a href=""><b>${ parentComment.author.pseudo }</b></a> ${ parentComment.content }</div>
                        <div>
                            <c:if test="${ !empty sessionScope.user }">
                                <a href="">Répondre</a><span style="color: #90949c;"> · </span>
                            </c:if>
                            <span style="color: #90949c;">${ parentComment.createdAt }</span>
                        </div>

                        <c:if test="${ childrenComments.size() > 0 }">
                            <div class="reply-border-left">
                                <c:forEach var="childComment" items="${ childrenComments }">
                                    <c:if test="${ childComment.parentId == parentComment.id }">
                                        <div class="reply-list">
                                            <a class="pull-left comment-object-child" href="#">
                                                <img class="media-object" src="${ childComment.author.imageUrl }">
                                            </a>
                                            <div class="media-body">
                                                <div><a href=""><b>${ childComment.author.pseudo }</b></a> ${ childComment.content }</div>
                                                <div>
                                                    <c:if test="${ !empty sessionScope.user }">
                                                        <a href="">Répondre</a><span style="color: #90949c;"> · </span>
                                                    </c:if>
                                                    <span style="color: #90949c;">${ childComment.createdAt }</span>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </div>
                        </c:if>

                    </div>
                </div>
            </c:forEach>
        </div>
    </c:when>
    <c:otherwise>
        <div class="comment-list">
            <div class="media thumbnail">
                Il n'y a encore aucun commentaire pour cette publication.
            </div>
        </div>
    </c:otherwise>
</c:choose>
