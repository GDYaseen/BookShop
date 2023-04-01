<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./CSS/style.css">
    <link rel="stylesheet" href="./CSS/Clients.css">
    <title>Clients</title>
</head>
<body>
    <aside>
        <a class="page" href=".\Accueil">Accueil</a>
        <a class="page" href=".\Sales">Ventes</a>
        <a class="page" href=".\Inventory">Inventaire</a>
        <div class="page selectedPage">Clients</div>
        <% if(session.getAttribute("isAdmin").equals("true")){
            		out.print("<a class=\"page\" href=\".\\Users\">Utilisateurs</a>");
            	}%>
    </aside>
    <section>
    	<%@ page import="Models.Client"%>
    	<%@ page import="Controllers.DAO.DAOClients"%>

		<%! DAOClients daoClients = new DAOClients(); %>
        <header><div>Clients</div> <a href="./Connect?logout=1">se Déconnecter</a></header>
        <main>
            <div id="clients">
                <table cellspacing="0">
                    <tr>
                        <th>Id</th>
                        <th>Nom Complet</th>
                        <th></th>
                    </tr>
                    <% for(Client b : daoClients.getElements()){
                        out.print("<tr><td>"+b.getId()+"</td>");
                            out.print("<td>"+b.getNom()+"</td>");
                            out.print("<td hidden>"+b.getEmail()+"</td>");
                            out.print("<td><button class='details' onClick='showDetails("+b.getId()+",\""+b.getEmail()+"\",\""+b.getNom()+"\")'>Details</button></td></tr>");
                        }%>
                </table>
                    <div class="ajouter">
                		<input type="text" id="nameAdd" placeholder="Nom Complet"/>
	                	<input type="email" id="emailAdd" placeholder="Email"/>
                        <button onClick="addClient()">Ajouter</button>
                        <button id="modifyButton" hidden onClick="modifyClient()">Modifier</button>
                    </div>
            </div>
            <div id="achats">
                <div>
                    <div id="emailAndDelete">
                        <h4>
                            Email:
                        </h4>
                        <button onClick="deleteUser(currentUserId)" hidden>Supprimer</button>
                    </div>
                    <table cellspacing="0">
                        <tr>
                            <th>Code</th>
                        <th>Livre</th>
                        <th>Auteur</th>
                        <th>Date d'achat</th>
                        <th>Prix</th>
                        <th></th>
                    </tr>
                </table>
            </div>
                
                <div class="ajouter" id="bookAdd">
                </div>
            </div>
        </main>
    </section>
    <script src="./JS/Clients.js"></script>
</body>
</html>