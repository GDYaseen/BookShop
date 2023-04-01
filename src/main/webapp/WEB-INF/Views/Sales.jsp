<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./CSS/style.css">
    <link rel="stylesheet" type="text/css" href="./CSS/Sales.css">
    <title>Ventes</title>
</head>
<body>
    <aside>
        <a class="page" href=".\Accueil">Accueil</a>
        <div class="page selectedPage">Ventes</div>
        <a class="page" href=".\Inventory">Inventaire</a>
        <a class="page" href=".\Clients">Clients</a>
        <% if(session.getAttribute("isAdmin").equals("true")){
            		out.print("<a class=\"page\" href=\".\\Users\">Utilisateurs</a>");
            	}%>
    </aside>
    <section>
    	<%@ page import="Models.Category"%>
    	<%@ page import="Controllers.DAO.DAOCategories"%>
    	<%@ page import="Models.Sale"%>
    	<%@ page import="Controllers.DAO.DAOSales"%>
    	
    	<%! DAOCategories daoCatg = new DAOCategories(); %>
    	<%! DAOSales daoSales = new DAOSales(); %>
    	
        <header><div>Ventes</div> <a href="./Connect?logout=1">se Déconnecter</a></header>
        <main>
            <div>
            <div id="search">
                <div id="period">
                    Période: <input required type="date" name="startDate"> à  <input required type="date" name="endDate">
                </div>
                <div id="inputSearch">
                    <input type="number" name="serialCode" placeholder="Numéro du livre">
                    <input type="text" name="Title" placeholder="Titre">
                    <input type="text" name="Auteur" placeholder="Auteur">
                    <select name="category"  id="categorySelect">
                        <option value="" selected>--Catégorie--</option>
                        <% for(Category c : daoCatg.getElements()){
                        		out.print("<option value=\""+c.getCatg()+"\">"+c.getCatg()+"</option>");
                        }%>
                    </select>
                    <button onClick="search()">Chercher<img src="./IMG/search.png" width="20px" height="20px" alt=""/></button>
                </div>
            </div>
                <div id="InventoryTable">
                    <h1>Bénéfice:</h1>
                    <table cellspacing="0">
                    <tr>
                        <th>Code</th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Category</th>
                        <th>Date de vente</th>
                        <th>Prix</th>
                        <th>Nombre Vendus</th>
                        <th>Total</th>
                    </tr>
						<% for(Sale b : daoSales.getDetailedSales("1")){
                        out.print("<tr><td>"+b.getId()+"</td>");
                            out.print("<td>"+b.getTitre()+"</td>");
                            out.print("<td>"+b.getAuteur()+"</td>");
                            out.print("<td>"+b.getCategories()+"</td>");
							out.print("<td>"+b.getDate_vente()+"</td>");
							out.print("<td>"+b.getPrix()+"</td>");
							out.print("<td>"+b.getSold()+"</td>");
							out.print("<td>"+b.getTotal()+"</td>");
                            out.print("<td hidden>"+b.getDate_vente()+"</td></tr>");
                        }%>   
					</table>
                </div>
            </div>
        </main>
    </section>
    <script src="./JS/Sales.js"></script>
</body>
</html>