package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccueilServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String Views = "/WEB-INF/Views/";
	
    public AccueilServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isAdmin")!=null) {
			request.getRequestDispatcher(Views+"Accueil.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/Connect");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
