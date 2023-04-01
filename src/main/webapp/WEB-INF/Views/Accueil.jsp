<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./CSS/style.css">
    <link rel="stylesheet" href="./CSS/Accueil.css">
    <title>Accueil</title>
</head>
<body>
    <aside>
        <div class="page selectedPage">Accueil</div>
        <a class="page" href=".\Sales">Ventes</a>
        <a class="page" href=".\Inventory">Inventaire</a>
        <a class="page" href=".\Clients">Clients</a>
        <% if(session.getAttribute("isAdmin").equals("true")){
            		out.print("<a class=\"page\" href=\".\\Users\">Utilisateurs</a>");
            	}%>
    </aside>
    <section>
        <header><div>Accueil</div> <a href="./Connect?logout=1">se Déconnecter</a></header>
        <main>
            <a class="item" href=".\Sales">Ventes</a>
            <a class="item" href=".\Inventory">Inventaire</a>
            <a class="item" href=".\Clients">Clients</a>
            <% if(session.getAttribute("isAdmin").equals("true")){
            		out.print("<a class=\"item\" href=\".\\Users\">Utilisateurs</a>");
            	}%>
        </main>
    </section>
</body>
</html>