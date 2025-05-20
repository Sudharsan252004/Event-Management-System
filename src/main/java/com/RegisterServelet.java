package com;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 * Servlet implementation class RegisterServelet
 */
@WebServlet("/RegisterServelet")
public class RegisterServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServelet() {
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
		//doGet(request, response);
		
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();

		    String name = request.getParameter("namee");
		    String emails = request.getParameter("email");
		    String pass = request.getParameter("password");
		    String num = request.getParameter("mobilenumber");
		    String events = request.getParameter("event");
		    String dist = request.getParameter("district");
		    String dat = request.getParameter("date");

		    // Debugging
		    System.out.println("Mobile Number received: " + num);

		    try {
		        String query = "INSERT INTO `register` (`Name`, `Email`, `Password`, `Number`, `Events`, `District`, `Date`) VALUES (?, ?, ?, ?, ?, ?, ?)";
		        Connection conn = connection.getConnection();

		        if (conn != null) {
		            PreparedStatement ps = conn.prepareStatement(query);
		            ps.setString(1, name);
		            ps.setString(2, emails);
		            ps.setString(3, pass);
		            ps.setString(4, num);
		            ps.setString(5, events);
		            ps.setString(6, dist);
		            ps.setString(7, dat);

		            int row = ps.executeUpdate();
		            if (row > 0) {
		                RequestDispatcher rd = request.getRequestDispatcher("register.html");
		                out.print("<p style='text-align:center;color:green;'>Registration Successful</p>");
		                rd.include(request, response);
		            } else {
		                out.print("<p style='text-align:center;color:red;'>Registration Failed</p>");
		            }
		        } else {
		            out.print("<p style='text-align:center;color:red;'>Database Connection Failed</p>");
		        }
		    } catch (Exception es) {
		        es.printStackTrace();
		        out.print("<p style='text-align:center;color:red;'>Error: " + es.getMessage() + "</p>");
		    }
		}
	}