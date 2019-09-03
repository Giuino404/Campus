package controller.corsiFacolta;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.implementations.CorsoDao;
import dao.implementations.FacoltaDao;
import dao.interfaces.CrudGenerico;
import model.Facolta;
import model.Cattedra;

/**
 * Servlet implementation class FacoltaVisualizzaServlet
 */
@WebServlet("/Facolta/*")
public class FacoltaVisualizzaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FacoltaVisualizzaServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	// Carico dal database la facolta con id uguale alla pathInfo (Per dubbi vedere i commenti in CorsoDeleteServlet 
    // o FacoltaDeleteServlet.
    // La carico con i suoi corsi annessi così da poterli visualizzare 
    // Carico inoltre tutti i corsi così da popolare la form che associera i corsi alla Facolta
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	CrudGenerico dao = new FacoltaDao();
		CrudGenerico cDao = new CorsoDao();
		
		int id = Integer.parseInt(request.getPathInfo().substring(1));
		
		Facolta f = ((FacoltaDao) dao).getByIdWithCorsiAndCattedre(id);
		
		HashMap<Integer, Cattedra> cattedre = new HashMap<Integer, Cattedra>();
		for(Cattedra pc : f.getCattedre()) {
			cattedre.put(pc.getCorso().getId(), pc);
		}
		
		request.setAttribute("cattedre", cattedre);
		request.setAttribute("facolta", f);
		request.setAttribute("corsi", cDao.getAll());
		
		request.getRequestDispatcher("/views/corsiFacolta/facolta.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
