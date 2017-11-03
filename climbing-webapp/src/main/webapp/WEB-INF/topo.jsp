<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />

    <title>${application.name} - Topo</title>

    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
          integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
          crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/style/custom.css" />
</head>
<body>

    <%@ include file="_include/header.jsp" %>

    <p>Topo</p>
    <ul>
        <c:forEach var="topo" items="${ topoList }">
            <li><a href="?name=${ topo.name }"><c:out value="${ topo.name }" /></a> -> <c:out value="${ topo.description }" /></li>
        </c:forEach>
    </ul>

    <%@ include file="_include/footer.jsp" %>

</body>
</html>
