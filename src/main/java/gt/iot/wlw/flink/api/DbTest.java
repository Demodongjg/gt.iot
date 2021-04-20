package gt.iot.wlw.flink.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbTest {

    public static Statement getConnect() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.taosdata.jdbc.rs.RestfulDriver");
            String jdbcUrl = "jdbc:TAOS-RS://10.152.160.183:6041/gt_iot?user=root&password=taosdata&timezone=Asia/Beijing&charset=utf-8";
            //上述地址为本地TDengine通用地址 “test”为TDengine中的一个数据库 需要根据具体需求做更改 这里跟mysql连接大致相同 可以先学习mysql
            Connection conn = DriverManager.getConnection(jdbcUrl);
            Statement stmt = conn.createStatement();
            return stmt;
        } catch (Exception e) {
            System.out.println("error");
            return null;//try catch为固定格式 catch报错这里不需要配置太复杂 照抄即可
        }
    }


}
