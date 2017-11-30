<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">

        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="/">${application.name}</a>
        </div>

        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li><a href="${pageContext.request.contextPath}/">Accueil</a></li>
                <li><a href="${pageContext.request.contextPath}/climbing">Sites</a></li>
                <li><a href="${pageContext.request.contextPath}/topo">Topo</a></li>
                <li><a href="${pageContext.request.contextPath}/search">Recherche</a></li>
                <c:choose>
                    <c:when test="${ empty sessionScope.user }">
                        <li><a href="${pageContext.request.contextPath}/inscription">Créer un compte</a></li>
                        <li><a href="${pageContext.request.contextPath}/login">S’identifier</a></li>
                    </c:when>
                    <c:otherwise>
                        <li><a href="${pageContext.request.contextPath}/logout">Se déconnecter</a></li>
                        <li>Bonjour ${ sessionScope.user.pseudo }</li>
                    </c:otherwise>
                </c:choose>

            </ul>
        </div>

    </div>
</nav>