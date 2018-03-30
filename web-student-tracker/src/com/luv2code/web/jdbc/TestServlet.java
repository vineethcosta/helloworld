package com.luv2code.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource dataSource;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        
        java.sql.Connection myConn=null;
        Statement myStmt=null;
        ResultSet myRs=null;
      
        try {
        	myConn = dataSource.getConnection();
        	
        	String sql="select * from student";
        	myStmt = myConn.createStatement();
        	myRs=myStmt.executeQuery(sql);
        	
        	while(myRs.next()) {
        		String email=myRs.getString("email");
        		out.println(email);
        	}
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
	}

}
