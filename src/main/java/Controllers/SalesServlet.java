package Controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import Models.Sale;
import Controllers.DAO.DAOSales;

public class SalesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String Views = "/WEB-INF/Views/";
	private DAOSales daoSales;
       
    public SalesServlet() {
        super();
        daoSales = new DAOSales();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getSession().getAttribute("isAdmin")!=null) {
			request.getRequestDispatcher(Views+"Sales.jsp").forward(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/Connect");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String req = request.getParameter("req").replaceAll("#", "%");
		ArrayList<Sale> list = (ArrayList<Sale>) daoSales.getDetailedSales(req);
		response.getWriter().append(list.toString());
	}

}
