package webapp;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.KsiazkaDao;
import model.Ksiazka;
import model.User;

public class BooksController extends HttpServlet {

	private static final String LIST_BOOK = "/views/ListBooks.jsp";
	private static final String INSERT_OR_EDIT = "/views/Book.jsp";
	
	private KsiazkaDao dao;
	
	
	public BooksController(){
		super();
		dao = new KsiazkaDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		if(action == null){
			forward = LIST_BOOK;
			request.setAttribute("books", dao.getAllBooks());
		}else if(action.equalsIgnoreCase("delete")){
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			dao.deleteBook(bookId);
			forward = LIST_BOOK;
			request.setAttribute("books", dao.getAllBooks());
		} else if(action.equalsIgnoreCase("edit")){
			forward = INSERT_OR_EDIT;
			int bookId = Integer.parseInt(request.getParameter("bookId"));
			Ksiazka book = dao.getBookById(bookId);
			request.setAttribute("book", book);
		} else if(action.equalsIgnoreCase("listBook")) {
			forward = LIST_BOOK;
			request.setAttribute("books", dao.getAllBooks());
		} else {
			forward = INSERT_OR_EDIT;
		}
        request.getRequestDispatcher(forward).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration e = request.getParameterNames();
	    while(e.hasMoreElements()){
	    	String parName =  e.nextElement().toString();
	    	System.out.println("POST BOOKS CONTROLER Element: " + parName);
	    	System.out.println("Value " + request.getParameter(parName));
	    }
		Ksiazka book = new Ksiazka();
	    book.setTytul(request.getParameter("title"));
	    book.setISBN(request.getParameter("ISBN"));
	    String rokWydania = request.getParameter("publishYear");
	    if(rokWydania != null && !rokWydania.isEmpty()){
	    	book.setRokWydania(Integer.parseInt(rokWydania));
	    }
	    book.setOpis(request.getParameter("description"));
	    book.setURLOkladki(request.getParameter("coverURL"));
	    String bookID = request.getParameter("bookid");
	    if(bookID == null || bookID.isEmpty()){
	    	dao.addNewBook(book);
	    	System.out.println("Detected completely new book");
	    } else {
	    	book.setId(Integer.parseInt(bookID));
	    	dao.updateBook(book);
	    	System.out.println("Detected already added book with id: " + bookID);
	    }
	    request.setAttribute("books", dao.getAllBooks());
	    request.getRequestDispatcher(LIST_BOOK).forward(request, response);
	}
}
