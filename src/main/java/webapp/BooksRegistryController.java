package webapp;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.RejestrKsiazekDao;
import dao.UserDao;
import model.ExtendedBook;
import model.User;

public class BooksRegistryController extends HttpServlet {

	private static final String BOOKS_REGISTER = "/views/BooksRegister.jsp";
	private static final String HISTORY_OF_BOOK = "views/BookHistory.jsp";

	private RejestrKsiazekDao dao;
	private UserDao userDao;
	private ServletContext context;

	public void init(ServletConfig config) throws ServletException {
		this.context = config.getServletContext();
	}

	public BooksRegistryController() {
		super();
		this.dao = new RejestrKsiazekDao();
		this.userDao = new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration e = request.getParameterNames();
	    while(e.hasMoreElements()){
	    	String parName =  e.nextElement().toString();
	    	System.out.println("GET BooksRegistryController Element: " + parName);
	    	System.out.println("Value " + request.getParameter(parName));
	    }
		
		String forward = "";
		String action = request.getParameter("action");
		System.err.println("DETECTED ACTION: " + action);
		if (action == null || action.equalsIgnoreCase("main")){
			forward = BOOKS_REGISTER;
			List<ExtendedBook> books = dao.getAllBooks();
			List<User> users = userDao.getAllUsers();
			request.setAttribute("extended_books", books);
			request.setAttribute("users_list", users);
		}
		else if (action.equalsIgnoreCase("bookDetails")){
			System.err.println("DETECTED bookDetails action");
			String bookID = request.getParameter("bookId");
			request.setAttribute("bookTransactions", dao.getListOfBookTransactions(Integer.parseInt(bookID)));
			forward = HISTORY_OF_BOOK;
		}		
		System.err.println("FORWARDED TO: " + forward);
		request.getRequestDispatcher(forward).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Enumeration e = request.getParameterNames();
		while (e.hasMoreElements()) {
			String parName = e.nextElement().toString();
			System.out.println("POST BooksRegistryController Element: " + parName);
			System.out.println("Value " + request.getParameter(parName));
		}
		String userID = request.getParameter("userID");
		String bookID = request.getParameter("bookID");
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("lend")) {
			if (userID != null && !userID.isEmpty() && bookID != null && !bookID.isEmpty())
				if (dao.lendBook(Integer.parseInt(bookID), Integer.parseInt(userID))) {
					List<ExtendedBook> books = dao.getAllBooks();
					List<User> users = userDao.getAllUsers();
					request.setAttribute("extended_books", books);
					request.setAttribute("users_list", users);
					request.getRequestDispatcher(BOOKS_REGISTER).forward(request, response);
					return;
				}
		} else if (action.equalsIgnoreCase("return")) {
			if(bookID != null && !bookID.isEmpty()){
				if (dao.returnBook(Integer.parseInt(bookID))){
					List<ExtendedBook> books = dao.getAllBooks();
					List<User> users = userDao.getAllUsers();
					request.setAttribute("extended_books", books);
					request.setAttribute("users_list", users);
					request.getRequestDispatcher(BOOKS_REGISTER).forward(request, response);
					return;
				}
			}
		}
		System.err.println("ERROR IN POST TYPE: UNABLE TO LEND BOOK");
	}
}
