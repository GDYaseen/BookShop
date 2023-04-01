<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page session="true" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="./CSS/style.css">
    <link rel="stylesheet" type="text/css" href="./CSS/Inventory.css">
    <title>Inventaire</title>
</head>
<body>
    	<%@ page import="Models.Book"%>
    	<%@ page import="Controllers.DAO.DAOBook"%>
    	<%@ page import="Models.Category"%>
    	<%@ page import="Controllers.DAO.DAOCategories"%>
		<%@ page import="java.io.*" %>
		
		<%! DAOBook daoBook = new DAOBook(); %>
		<%! DAOCategories daoCatg = new DAOCategories(); %>
    <aside>
        <a class="page" href=".\Accueil">Accueil</a>
        <a class="page" href=".\Sales">Ventes</a>
        <div class="page selectedPage">Inventaire</div>
        <a class="page" href=".\Clients">Clients</a>
        <% if(session.getAttribute("isAdmin").equals("true")){
            		out.print("<a class=\"page\" href=\".\\Users\">Utilisateurs</a>");
            	}%>
    </aside>
    <section>
        <header><div>Inventaire</div> <a href="./Connect?logout=1">se Déconnecter</a></header>
        <main>
            <div>
            <div id="search">
                <input type="number" name="serialCode" placeholder="Numéro de série">
                <input type="text" name="Title" placeholder="Titre">
                <input type="text" name="Auteur" placeholder="Auteur">
                <select name="category" id="categorySelect" value="">
                    <option value="">--Catégorie--</option>
                    <% for(Category c : daoCatg.getElements()){
                        out.print("<option value=\""+c.getCatg()+"\">"+c.getCatg()+"</option>");
                        }%>    
                </select>
                <button onClick="search()">Chercher</button>
            </div>
                <div id="InventoryTable">
                	<div id="add">
                        <input type="number" name="serialCode" placeholder="Code série">
                        <input type="text" name="Title" placeholder="Titre">
                        <input type="auteur" name="Auteur" placeholder="Auteur">
                        <select name="category"  id="categorySelect" value="">
                            <option value="" >--Catégorie--</option>
                            <% for(Category c : daoCatg.getElements()){
                        out.print("<option value=\""+c.getCatg()+"\">"+c.getCatg()+"</option>");
                        }%>  
                        </select>
                        <input type="number" name="Prix" placeholder="Prix">
						<label for="files" class="btn">+ Image</label>
						<input id="files" name="cover" style="display:none;" type="file">
                        <input type="number" name="count" placeholder="Nombre">
                        <button onClick="addBook()">Ajouter</button>
                    </div>
                    <table cellspacing="0">
                    <tr>
                        <th>Code</th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Category</th>
                        <th>Prix</th>
                        <th></th>
                    </tr>
                    <% 	for(Book b : daoBook.getElements()){
                        	out.print("<tr><td id=\"selectedId\">"+b.getId()+"</td>");
                            out.print("<td id=\"selectedTitre\">"+b.getTitre()+"</td>");
                            out.print("<td id=\"selectedAuteur\">"+b.getAuteur()+"</td>");
                            out.print("<td id=\"selectedCategories\">"+b.getCategories()+"</td>");
							out.print("<td id=\"selectedPrix\">"+b.getPrix()+"</td>");
							out.print("<td id=\"selectedStock\" hidden>"+b.getStock()+"</td>");
							out.print("<td id=\"selectedCover\" hidden>"+new String(b.getCover())+"</td>");
                            out.print("<td><button onclick=\"selectBook(this)\">Selectionner</button></td></tr>");
                        }%>                  
                    </table>
                </div>
            </div>
            <div id="bookDetails">
                
            </div>
        </main>
    </section>
    <script src="./JS/Inventory.js"></script>
</body>
</html>