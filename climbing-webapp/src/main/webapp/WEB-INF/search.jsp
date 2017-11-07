<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <form method="post" action="search">
        <label for="publication_name">Nom : </label>
        <input type="text" name="publication_name" id="publication_name">

        <input type="submit" />
    </form>

    <c:if test="${ !empty publication_name }">
        <ul>
            <c:forEach var="publication" items="${ publicationList }">
                <li><c:out value="${ publication.name }" /></li>
            </c:forEach>
        </ul>
    </c:if>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
