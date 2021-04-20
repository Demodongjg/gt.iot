package gt.iot.wlw.flink.emqx;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("gt.iot.wlw.flink")
@MapperScan("gt.iot.wlw.flink.dao")
public class EmqxApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmqxApplication.class, args);
    }

}
