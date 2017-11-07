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
                <li><a href="#">Créer un compte</a></li>
                <li><a href="#">S’identifier</a></li>
            </ul>
        </div>

    </div>
</nav>