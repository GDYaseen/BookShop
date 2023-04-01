<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="./CSS/style.css">
    <link rel="stylesheet" href="./CSS/Users.css">
    <title>Utilisateurs</title>
</head>
<body>
    <aside>
        <a class="page" href=".\Accueil">Accueil</a>
        <a class="page" href=".\Sales">Ventes</a>
        <a class="page" href=".\Inventory">Inventaire</a>
        <a class="page" href=".\Clients">Clients</a>
        <div class="page selectedPage">Utilisateurs</div><!-- use <% %> to check if admin -->
    </aside>
    <section>
        <%@ page import="Models.User"%>
    	<%@ page import="Controllers.DAO.DAOUsers"%>

		<%! DAOUsers daoUsers = new DAOUsers(); %>
        <header><div>Utilisateurs</div> <a href="./Connect?logout=1">se Déconnecter</a></header>
        <main>
            <div id="users">
                <div class="ajouter">
                    Nom d'utilisateur: <input type="text" id="username" placeholder="Nom d'utilisateur"/>
                    Mot de passe: <input type="text" id="password" placeholder="Mot de passe"/>
                    Admin: <input type="checkbox" id="admin"/>
                    <button id="addUser" onClick="addUser()">Ajouter<img src="./IMG/add.png" width="20px" height="20px" alt=""/></button>
                    <button hidden id="modifyUser" onClick="modifyUser(currentSelectedUser)">Confirmer<img src="./IMG/confirm.png" width="20px" height="20px" alt=""/></button>
                </div>
                <table cellspacing="0">
                    <tr>
                        <th>Id</th>
                        <th>Nom d'utilisateur</th>
                        <th>Mot de passe</th>
                        <th>Est admin</th>
                        <th></th>
                    </tr>
                    <% for(User u : daoUsers.getElements()){
                        out.print("<tr><td>"+u.getId()+"</td>");
                            out.print("<td>"+u.getUsername()+"</td>");
                            out.print("<td>"+u.getPassword()+"</td>");
                            out.print("<td>"+u.isAdmin()+"</td>");
                            out.print("<td><button onClick='toggleModify("+u.getId()+",\""+u.getUsername()+"\",\""+u.getPassword()+"\",\""+u.isAdmin()+"\")'>Modifier<img src=\"./IMG/edit.png\" width=\"15px\" height=\"15px\" alt=\"\"/></button>");
                            if((int)session.getAttribute("id")!=u.getId()){
                            	out.print("<button onClick='deleteUser("+u.getId()+")'>Supprimer<img src='./IMG/delete.png' width='12px' height='12px' alt=''/></button>");
                            }
                            out.print("</td></tr>");
                        }%>
                </table>
            </div>
        </main>
    </section>
    <script src="./JS/Users.js"></script>
</body>
</html>