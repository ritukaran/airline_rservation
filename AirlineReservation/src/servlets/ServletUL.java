package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import repository.UserDAO;

/**
 * Servlet implementation class ServletUL
 */
public class ServletUL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUL() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("text/html");  
		    PrintWriter out = response.getWriter();  
		          System.out.println("in servlet");
		    String n=request.getParameter("username");  
		    String p=request.getParameter("userpass");  
		          System.out.println(" after name");
		    try {
				if(UserDAO.validate(n, p)){  
					// System.out.println(" inside method");
				    RequestDispatcher rd=request.getRequestDispatcher("ULogin.jsp");  
				    rd.forward(request,response);  
				}  
				else{  
				    out.print("Sorry username or password error");  
				    RequestDispatcher rd=request.getRequestDispatcher("UserLogin.html");  
				    rd.include(request,response);  
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		          
		    out.close(); 
	}

}
