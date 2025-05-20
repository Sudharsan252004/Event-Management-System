package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class LoginServelet
 */
@WebServlet("/LoginServelet")
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServelet() {
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
		doGet(request, response);
		
		PrintWriter out=response.getWriter();
		
		String emails=request.getParameter("email");
		
		String pass=request.getParameter("password");
		
		try {
			String query="SELECT * FROM register WHERE Email=? and Password=?";
			PreparedStatement ps=connection.getConnection().prepareStatement(query);
			
			ps.setString(1, emails);
			ps.setString(2, pass);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
			  RequestDispatcher rd=request.getRequestDispatcher("index.html");
			  rd.forward(request, response);

			}
			else {
				out.print("<p style=text-align:center;color:red;>Username or Password is Mismatched</p>");
				
				RequestDispatcher rd=request.getRequestDispatcher("login.html");
				rd.include(request, response);
			}
			
			
		}
		catch(Exception es)
		{
			System.out.print(es);
		}
		
		
	}
	

}
