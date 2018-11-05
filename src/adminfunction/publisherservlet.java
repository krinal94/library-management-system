package adminfunction;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class publisherservlet
 */
@WebServlet("/publisherservlet")
public class publisherservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public publisherservlet() {
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
        PrintWriter pw = response.getWriter(); 
		  //get the variables entered in the form

		  Connection connection=null;
		  ResultSet rs;
		  
		  /*boolean status = false;
	        HttpSession session = request.getSession();
	       session.setAttribute("name", n);
	       System.out.print(n+", "+p);*/
	        
      String a = request.getParameter("publisher_name");
      
      String b = request.getParameter("publisher_address");
      
      
      try {
          Class.forName("com.mysql.jdbc.Driver");
          Connection conn = DriverManager.getConnection(
                  "jdbc:mysql://localhost/library_management_system", "root", "");

          PreparedStatement ps = conn
                  .prepareStatement("INSERT INTO `publisher`( `publisher_name`,`publisher_address`) VALUES (?,?)");

          ps.setString(1, a);
          ps.setString(2, b);
          
          
          int i = ps.executeUpdate();
       //   conn.commit(); 
          String msg=" ";
          if(i!=0){  
        	  System.out.print("Recorded Added");
        	  response.sendRedirect("publisher_list.jsp");
        	  //RequestDispatcher rd=request.getRequestDispatcher("adminpanel.jsp");  
              //rd.forward(request,response);    
          }  
          else{  
            msg="failed to insert the data";
            pw.println("<font size='6' color=blue>" + msg + "</font>");
           }  
          ps.close();
        }  
        catch (Exception e1){  
          pw.println(e1);  
        }  

}

}