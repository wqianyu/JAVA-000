import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Properties;

public class HikariTest {

    public static void main(String[] args) {
        Connection conn = null;
        try (InputStream is = HikariTest.class.getClassLoader().getResourceAsStream("hikari.properties")) {
            // 加载属性文件并解析：
            Properties props = new Properties();
            props.load(is);
            HikariConfig config = new HikariConfig(props);
            HikariDataSource sHikariDataSource = new HikariDataSource(config);
            conn =sHikariDataSource.getConnection();
            conn.setAutoCommit(false);
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
            //不执行update，不会真正插入记录
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
