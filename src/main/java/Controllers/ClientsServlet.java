package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import Models.Client;
import Models.Sale;
import Models.Book;
import Controllers.DAO.DAOClients;
import Controllers.DAO.DAOSales;
import Controllers.DAO.DAOBook;
public class ClientsServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String Views = "/WEB-INF/Views/";
	private DAOClients daoClient;
	private DAOSales daoSales;
	private DAOBook daoBook;
    public ClientsServlet() {
        super();
        daoClient = new DAOClients();
        daoSales = new DAOSales();
        daoBook = new DAOBook();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isAdmin")!=null) {
			request.getRequestDispatcher(Views+"Clients.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/Connect");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String livre = request.getParameter("id");
		String client = request.getParameter("id_client");
		String date = request.getParameter("date");
		
		switch(request.getParameter("type")) {
			case "add":
				daoClient.addElement(new Client(0,name,email));
				response.getWriter().append(name);
				break;
			case "details":
				ArrayList<Sale> list = (ArrayList<Sale>) daoSales.getDetailedSales(Integer.parseInt(request.getParameter("id")));
				response.getWriter().append(list.toString());
				break;
			case "deleteSale":
				daoSales.deleteElement(new Sale(Integer.parseInt(client),Integer.parseInt(livre),date));
				response.getWriter().append(livre);
				break;
			case "deleteClient":
				daoClient.deleteElement(new Client(Integer.parseInt(client),null,null));
				response.getWriter().append(client);
				break;
			case "modifyClient":
				daoClient.modifyElement(new Client(Integer.parseInt(client),name,email));
				response.getWriter().append(client);
				break;
			case "addSale":
				Book b = daoBook.getElement(new Book(Integer.parseInt(livre),"","","",0,null,0));
				if(b!=null) {
					daoSales.addElement(new Sale(Integer.parseInt(client),Integer.parseInt(livre),date));
					response.getWriter().append("Success");
				}else {
					response.getWriter().append("Fail");
				}
				break;
		}
	}

}
