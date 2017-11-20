<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="_include/html_head.jsp" %>
    <title>${application.name} - Accueil</title>
</head>
<body>

<%@ include file="_include/header.jsp" %>

<div class="container">
    <h1><spring:message code="menu.home"/></h1>
</div>

<%@ include file="_include/footer.jsp" %>

</body>
</html>
