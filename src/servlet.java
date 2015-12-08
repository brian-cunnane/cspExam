import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Created by brian on 08/12/2015.
 */
public class servlet extends HttpServlet {
    private Connection connection;
    private Statement statement;
    private PreparedStatement find;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspExam?user=root&password=root");

            find = connection.prepareStatement("SELECT *"
                    + "FROM person"
            );

            statement = connection.createStatement();
            String sqlString = "INSERT INTO person (firstName, lastName, email, phone,comments) " +
                    "VALUES("
                    //+"'" + request.getParameter("id") +"'" +','
                    +"'" + request.getParameter("first_name") +"'" +','
                    +"'" + request.getParameter("last_name")+"'" +','
                    +"'" + request.getParameter("email") +"'"+ ','
                    +"'" + request.getParameter("telephone") +"'" +','
                    +"'" + request.getParameter("comments") + "')"
                    ;

            //String sqlString = "INSERT INTO person (idNum,firstName, lastName, email, phone,comments) " +
            //      "VALUES (0,'a','b','c',9,'e')";
            statement.execute(sqlString);
            ResultSet results = find.executeQuery();

            out.println("<html><head><title>Query Results</title></head><body>");
            out.println("<h2>Thank you for your query.</h2>");
            out.println("<p>You query is: " + sqlString + "</p>");
            out.println();
            out.println("<p> First Name, Last Name, email, phone, comments");

            while (results.next()) {
                out.println("<p>" + results.getString("firstName")
                        + ", " + results.getString("lastName")
                        + ", " + results.getString("email")
                        + ", " + results.getString("phone")
                        + ", " + results.getString("comments")
                        + "</p>");
            }


        }catch(SQLException SQLE){SQLE.printStackTrace();}
        catch(ClassNotFoundException CNFE){CNFE.printStackTrace();}
        finally {
            out.close();
            try {
                // Step 5: Close the Statement and Connection
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }


    }
}
