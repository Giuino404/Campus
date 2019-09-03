package controller.prenotazione;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.implementations.StudenteDao;
import dao.interfaces.CrudGenerico;
import model.Account;
import model.Studente;

/**
 * Servlet implementation class PrenotazioneVisualizza
 */
@WebServlet("/PrenotazioneVisualizza")
public class PrenotazioneVisualizza extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrenotazioneVisualizza() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CrudGenerico daos = new StudenteDao ();
	
		
		Account s =(Account) request.getSession().getAttribute("account");

		Studente st = ((StudenteDao) daos).getByIdWithPrenotazioni(s.getUtente().getId());
				
		request.setAttribute("prenotazioni", st.getPrenotazioni() );
		request.getRequestDispatcher("/views/prenotazione/prenotazioni.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
