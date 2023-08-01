import java.sql.*;
public class Database {
    Connection c;
    Statement s;
    public Database()
    {
        try{
            c = DriverManager.getConnection("jdbc:mysql:///cms", "root", "Tejas@358");
            s = c.createStatement();


        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}
