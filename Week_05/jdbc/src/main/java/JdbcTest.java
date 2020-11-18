import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcTest {
    public static void main(String[] args) {
        //1.注册数据库驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");

            try (
                    //2.获取数据库的连接
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "root");
                    //3.创建传输器对象
                    Statement stat = conn.createStatement();
                    //4.利用传输器对象传输sql语句到数据库中执行操作，将结果用结果集返回
                    ResultSet rs = stat.executeQuery("select * from jinhong_account");
            ) {
                //5.遍历结果集，并获取查询结果
                while (rs.next()) {
                    String name = rs.getString("account");
                    System.out.println(name);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
    }
}