package gt.iot.wlw.flink.emqx;

import gt.iot.wlw.flink.api.MyMqttClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class InirProject implements ApplicationRunner {

    @Resource
    private MyMqttClient myMqttClient;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        myMqttClient.start("emqx:v1");
        myMqttClient.subTopic("/#");
    }
}
