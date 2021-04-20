package gt.iot.wlw.flink.api;

import java.sql.ResultSet;
import java.sql.Statement;

public class Test {

    public static void main(String[] args) {
        try {
            Statement stmt = DbTest.getConnect();//调用连接配置文件
            String sql = "select * from wlwmsg";
            ResultSet rs = stmt.executeQuery(sql);//简单的查询语句
            String name=null;
            String job = null;
            while (rs.next()) {	// 判断是否还有下一个数据
                // 根据字段名获取相应的值
                name = rs.getString("theme");
                job = rs.getString(("wlwmsg"));
                //输出查到的记录的各个字段的值
                System.out.println(name+"  "+job);
            }//简单的while循环语句示例（需要预先在数据库中输入相关信息）
        } catch (Exception e) {
            System.out.println("error sdffsw");
        }
    }

}
