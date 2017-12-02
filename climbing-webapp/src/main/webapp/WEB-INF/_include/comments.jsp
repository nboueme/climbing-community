<c:if test="${ parentsComments.size() > 0 }">
    <div class="media-list">
        <c:forEach var="parentComment" items="${ parentsComments }">
            <div class="media thumbnail">
                <a class="pull-left" href="#">
                    <img class="media-object" src="${ parentComment.author.imageUrl }">
                </a>
                <div class="media-body">
                    <h5 class="media-heading">${ parentComment.author.pseudo }, le ${ parentComment.createdAt }</h5>
                    <p>${ parentComment.content }</p>

                    <c:forEach var="childComment" items="${ childrenComments }">
                        <c:if test="${ childComment.parentId == parentComment.id }">
                            <div class="media thumbnail">
                                <a class="pull-left" href="#">
                                    <img class="media-object" src="${ childComment.author.imageUrl }">
                                </a>
                                <div class="media-body">
                                    <h5 class="media-heading">${ childComment.author.pseudo }, le ${ childComment.createdAt }</h5>
                                    <p>${ childComment.content }</p>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>

                </div>
            </div>
        </c:forEach>
    </div>
</c:if>