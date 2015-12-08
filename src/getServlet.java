import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

/**
 * Created by brian on 08/12/2015.
 */
public class getServlet extends HttpServlet {
    private Connection connection;
    private Statement statement;
    private PreparedStatement lastId;




    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cspExam?user=root&password=root");
            lastId = connection.prepareStatement(
                    "select idNum,firstName, lastName, email, phone,comments "
                    +" FROM person "
                    +"WHERE idNum = last_insert_id()" );
            statement = connection.createStatement();

        }catch (SQLException SQLE){SQLE.printStackTrace();}
        catch(ClassNotFoundException CNFE){CNFE.printStackTrace();}
    }
}
