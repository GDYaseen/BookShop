package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import Controllers.DBConnection.*;
import Models.User;
import Controllers.DAO.DAOUsers;

public class ConnectionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;   
	private static final String Views = "/WEB-INF/Views/";
	
	private DAOUsers daoUsers;
    public ConnectionServlet() {
        super();
        daoUsers = new DAOUsers();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isAdmin")!=null) {
			if(request.getParameter("logout")!=null) {
				if(request.getParameter("logout").equals("1")) {
					request.getSession().invalidate();
					request.getRequestDispatcher(Views+"login.jsp").forward(request, response);
					return;
				}
			}
			response.sendRedirect(request.getContextPath() + "/Accueil");
			return;
		}
		request.getRequestDispatcher(Views+"login.jsp").forward(request, response);
	}

	/**
	 *
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = daoUsers.matchUser(request.getParameter("username"),request.getParameter("password"));
		HttpSession session = request.getSession();
		if(u!=null) {
			session.setAttribute("fail", "false");
			session.setAttribute("isAdmin", Boolean.toString(u.isAdmin()));
			session.setAttribute("id", u.getId());
			response.sendRedirect(request.getContextPath() + "/Accueil");
		}else {
			session.setAttribute("fail", "true");
			request.getRequestDispatcher(Views+"login.jsp").forward(request, response);
		}
	}

}
