package gt.iot.wlw.flink.api;


import gt.iot.wlw.flink.dao.WlwMsgMapper;
import gt.iot.wlw.flink.entity.WLwMsg;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class MQTTReceiveCallback implements MqttCallback {

    @Resource
    private WlwMsgMapper wlwMsgMapper;

    @Resource
    private WLwMsg wLwMsg;

    @Override
    public void connectionLost(Throwable cause) {
        // 连接丢失后，一般在这里面进行重连
        System.out.println("连接断开，可以做重连");
    }
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        System.out.println("deliveryComplete---------" + token.isComplete());
    }
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // subscribe后得到的消息会执行到这里面
        System.out.println("接收消息主题 : " + topic);
        System.out.println("接收消息Qos : " + message.getQos());
        System.out.println("接收消息内容 : " + new String(message.getPayload(),"UTF-8"));
        try {
            wLwMsg.setTheme(topic);
            wLwMsg.setWlwMsg(new String(message.getPayload(),"UTF-8"));
            int a = wlwMsgMapper.insertWlwMsg(wLwMsg);
            //wlwMsgMapper.selectMsg();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
