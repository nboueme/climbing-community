<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Recherche</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1>Recherche</h1>

    <form:form method="post" action="/search" modelAttribute="searchPublication">
        <form:label path="name">Nom : </form:label>
        <form:input path="name" />

        <input type="submit" />
    </form:form>

    <c:if test="${ !empty publicationName }">
        <ul>
            <c:forEach var="publication" items="${ publicationList }">
                <li><c:out value="${ publication.name }" /></li>
            </c:forEach>
            <li>TEST : ${ publicationName }</li>
        </ul>
    </c:if>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
