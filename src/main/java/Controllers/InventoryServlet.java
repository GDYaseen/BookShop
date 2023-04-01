package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;

import Controllers.DAO.DAOBook;
import Models.Book;
import Models.Client;

public class InventoryServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;   
	private static final String Views = "/WEB-INF/Views/";
	private DAOBook daoBook;
       
    public InventoryServlet() {
        super();
        daoBook = new DAOBook();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isAdmin")!=null) {
			request.getRequestDispatcher(Views+"Inventory.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/Connect");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int id_livre=0,Stock=0;
		float Prix=0;
		byte[] Cover=null;
		if(request.getParameter("id")!=null) {			
			id_livre = Integer.parseInt(request.getParameter("id"));
		}
		if(request.getParameter("Prix")!=null) {
			Prix = Float.parseFloat(request.getParameter("Prix"));
		}
		if(request.getParameter("stock")!=null) {
			Stock = Integer.parseInt(request.getParameter("stock"));
		}
		if(request.getParameter("Cover")!=null) {
			Cover = request.getParameter("Cover").getBytes();			
		}
		String Title = request.getParameter("Title");
		String Auteur = request.getParameter("Auteur");
		String Categories = request.getParameter("Categories");

		switch(request.getParameter("type")) {
			case "add":
				
				Book b = daoBook.getElement(new Book(id_livre,Title,Auteur,Categories,Prix,Cover,0));
				ArrayList<Book> list_ = (ArrayList<Book>) daoBook.getElements();
				
				if(b == null) {					
					daoBook.addElement(new Book(id_livre,Title,Auteur,Categories,Prix,Cover,Stock));
				}else {
					daoBook.modifyElement(new Book(id_livre,Title,Auteur,Categories,Prix,Cover,Stock+b.getStock()));
				}
				response.getWriter().append(list_.toString());
				break;
			case "req":
				String req = request.getParameter("req").replaceAll("#", "%");
				ArrayList<Book> list = (ArrayList<Book>) daoBook.getElements(req);
				if(list.size()>0) {			
					response.getWriter().append(list.toString());
				}else {response.getWriter().append("[{}]");}
				break;
			case "modify":
				daoBook.modifyElement(new Book(id_livre,Title,Auteur,Categories,Prix,Cover,Stock));
				ArrayList<Book> list__ = (ArrayList<Book>) daoBook.getElements();
				response.getWriter().append(list__.toString());
		}
	}
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_livre = Integer.parseInt(request.getParameter("id"));

		daoBook.deleteElement(new Book(id_livre,"","","",0,null,0));
		ArrayList<Book> list = (ArrayList<Book>) daoBook.getElements();
		
		if(list.size()>0) {			
			response.getWriter().append(list.toString());
		}else {response.getWriter().append("[{}]");}
	}
}
