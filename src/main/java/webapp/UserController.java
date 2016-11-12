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

import dao.UserDao;
import model.User;

public class UserController extends HttpServlet {
	
	private static final String LIST_USER = "/views/ListUser.jsp";
	private static final String INSERT_OR_EDIT = "/views/User.jsp";
	
	private UserDao dao;
	
	public UserController(){
		super();
		dao = new UserDao();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forward = "";
		String action = request.getParameter("action");
		if(action == null){
			forward = LIST_USER;
			request.setAttribute("users", dao.getAllUsers());
		} else if(action.equalsIgnoreCase("delete")){
			int userId = Integer.parseInt(request.getParameter("userId"));
			dao.deleteUser(userId);
			forward = LIST_USER;
			request.setAttribute("users", dao.getAllUsers());
		} else if(action.equalsIgnoreCase("edit")){
			forward = INSERT_OR_EDIT;
			int userId = Integer.parseInt(request.getParameter("userId"));
			User user = dao.getUserById(userId);
			request.setAttribute("user", user);
		} else if(action.equalsIgnoreCase("listUser")) {
			forward = LIST_USER;
			request.setAttribute("users", dao.getAllUsers());
		} else {
			forward = INSERT_OR_EDIT;
		}
        request.getRequestDispatcher(forward).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    User user = new User();
	    user.setImie(request.getParameter("firstName"));
	    user.setNazwisko(request.getParameter("lastName"));
	    try {
	    	Date dob = new SimpleDateFormat("MM/dd/yyyy").parse(request.getParameter("dob"));
	    	user.setDataUrodzenia(dob);
	    } catch (ParseException e){
	    	e.printStackTrace();
	    }
	    user.setEmail(request.getParameter("email"));
	    user.setTelefon(request.getParameter("telephone"));
	    Enumeration e = request.getParameterNames();
	    while(e.hasMoreElements()){
	    	System.out.println("Element: " + e.nextElement().toString());
	    }
	    String userId = request.getParameter("userid");
	    if(userId == null || userId.isEmpty()){
	    	dao.addNewUser(user);
	    } else {
	    	user.setUserId(Integer.parseInt(userId));
	    	dao.updateUser(user);
	    }
	    request.setAttribute("users", dao.getAllUsers());
	    request.getRequestDispatcher(LIST_USER).forward(request, response);
	}
}
