package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Controllers.DAO.DAOUsers;
import Models.User;

public class UsersServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String Views = "/WEB-INF/Views/";
	
	private DAOUsers daoUsers;
	
	public UsersServlet() {
        super();
        daoUsers = new DAOUsers();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isAdmin")!=null) {
			if(request.getSession().getAttribute("isAdmin").equals("true")) {
				request.getRequestDispatcher(Views+"Users.jsp").forward(request, response);
			}else {
				response.sendRedirect(request.getContextPath() + "/Accueil");
			}
			return;
		}
		response.sendRedirect(request.getContextPath() + "/Connect");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isAdmin = Boolean.parseBoolean(request.getParameter("admin"));
		daoUsers.addElement(new User(0,username,password,isAdmin));
		response.getWriter().append("success");
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isAdmin = Boolean.parseBoolean(request.getParameter("admin"));
		
		daoUsers.modifyElement(new User(id,username,password,isAdmin));
		response.getWriter().append("success");
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		daoUsers.deleteElement(new User(id,"","",false));
	}

}
