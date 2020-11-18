import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class PreparedStateTest {
    public static void main(String[] args) {
        Connection conn = null;
        try {
            //1.注册数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2.获取数据库的连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false", "root", "root");
            //3.创建传输器对象
            PreparedStatement pstm = conn.prepareStatement("insert into jinhong_account values (?,?,?,?,?,?)");
            conn.setAutoCommit(false);
            pstm.setInt(1,4);
            pstm.setString(2,"adminJh");
            pstm.setString(3,"salt");
            pstm.setString(4,"test");
            pstm.setString(5,"管理员");
            pstm.setDate(6,new Date(System.currentTimeMillis()));
            //4.利用传输器对象传输sql语句到数据库中执行操作，将结果用结果集返回
            pstm.addBatch();
            pstm.executeUpdate();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
                if(!conn.isClosed())
                {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}