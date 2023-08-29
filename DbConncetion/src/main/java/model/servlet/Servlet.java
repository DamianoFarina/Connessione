package model.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Articolo;
import model.DAO.ArticoloDAO;


@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Servlet() {
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String operazione = request.getParameter("operazione");
		ArticoloDAO aDao = new ArticoloDAO();
		
		switch(operazione){
		
			case "inserimento":
			{
				
				String descrizione = request.getParameter("descrizione");
				String q = request.getParameter("quantita");
				int quantita= Integer.parseInt(q);
				
				Articolo a = new Articolo();
				
				a.setDescrizione(descrizione);
				a.setQuantita(quantita);
				
				try {
					aDao.insert(a);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}	
			case "update":
			{
				String descrizione = request.getParameter("descrizione");
				String q = request.getParameter("quantita");
				int quantita= Integer.parseInt(q);
				String cod = request.getParameter("codice");
				int codice = Integer.parseInt(cod);
				
				Articolo a = new Articolo();
				
				a.setDescrizione(descrizione);
				a.setQuantita(quantita);
				a.setCodice(codice);
				
				try {
					aDao.update(a);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}	
			case "delete":
			{
				String cod = request.getParameter("codice");
				int codice = Integer.parseInt(cod);
				
				try {
					aDao.delete(codice);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "find":
				List<Articolo> lista = aDao.findCode();
				
				request.getSession().setAttribute("elencolista", lista);;
				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ElencoArticoli.jsp");
				dispatcher.forward(request, response);
				
				break;
			default:
				System.out.println("Errore");
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
