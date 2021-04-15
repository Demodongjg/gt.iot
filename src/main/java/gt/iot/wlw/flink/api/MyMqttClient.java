package gt.iot.wlw.flink.api;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyMqttClient {
    public static MqttClient mqttClient = null;
    private static MemoryPersistence memoryPersistence = null;
    private static MqttConnectOptions mqttConnectOptions = null;
    private static String ClientName = "emqx:v1";				//待填	将在服务端出现的名字  emqx:v1   emqx/emqx:4.2.9
    private static String IP = "iot.gt.cn";						//待填  服务器IP 10.152.160.183  iot.gt.cn
    private static String username = "dashboard";
    private static String password = "dashboard";

    @Autowired
    private MQTTReceiveCallback MQTTReceiveCallback ;

    public void start(String clientId) {
        //初始化连接设置对象
        mqttConnectOptions = new MqttConnectOptions();
        //设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，
        //这里设置为true表示每次连接到服务器都以新的身份连接
        mqttConnectOptions.setCleanSession(true);
        //设置连接超时时间，单位是秒
        mqttConnectOptions.setConnectionTimeout(10);
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setPassword(password.toCharArray());
        //设置持久化方式
        memoryPersistence = new MemoryPersistence();
        if(null != clientId) {
            try {
                mqttClient = new MqttClient("ssl://"+IP+":8883", clientId,memoryPersistence);//ssl 8883
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("连接状态："+mqttClient.isConnected());
        //设置连接和回调
        if(null != mqttClient) {
            if(!mqttClient.isConnected()) {
                //创建回调函数对象
                //客户端添加回调函数
                mqttClient.setCallback(MQTTReceiveCallback);
                //创建连接
                try {
                    System.out.println("创建连接");
                    mqttClient.connect(mqttConnectOptions);
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }else {
            System.out.println("mqttClient为空");
        }
        System.out.println("连接状态"+mqttClient.isConnected());
    }

    //	关闭连接
    public void closeConnect() {
        //关闭存储方式
        if(null != memoryPersistence) {
            try {
                memoryPersistence.close();
            } catch (MqttPersistenceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            System.out.println("memoryPersistence is null");
        }

        //关闭连接
        if(null != mqttClient) {
            if(mqttClient.isConnected()) {
                try {
                    mqttClient.disconnect();
                    mqttClient.close();
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }else {
                System.out.println("mqttClient is not connect");
            }
        }else {
            System.out.println("mqttClient is null");
        }
    }

    //	发布消息
    public void publishMessage(String pubTopic, String message, int qos) {
        if(null != mqttClient&& mqttClient.isConnected()) {
            System.out.println("发布消息   "+mqttClient.isConnected());
            System.out.println("id:"+mqttClient.getClientId());
            MqttMessage mqttMessage = new MqttMessage();
            mqttMessage.setQos(qos);
            mqttMessage.setPayload(message.getBytes());

            MqttTopic topic = mqttClient.getTopic(pubTopic);

            if(null != topic) {
                try {
                    MqttDeliveryToken publish = topic.publish(mqttMessage);
                    if(!publish.isComplete()) {
                        System.out.println("消息发布成功");
                    }
                } catch (MqttException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }else {
            reConnect();
        }
    }

    //	重新连接
    public  void reConnect() {
        if(null != mqttClient) {
            if(!mqttClient.isConnected()) {
                if(null != mqttConnectOptions) {
                    try {
                        mqttClient.connect(mqttConnectOptions);
                    } catch (MqttException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }else {
                    System.out.println("mqttConnectOptions is null");
                }
            }else {
                System.out.println("mqttClient is null or connect");
            }
        }else {
            start(ClientName);
        }
    }
    //	订阅主题
    public void subTopic(String topic) {
        if(null != mqttClient&& mqttClient.isConnected()) {
            try {
                mqttClient.subscribe(topic, 1);
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            System.out.println("mqttClient is error");
        }
    }

    //	清空主题
    public void cleanTopic(String topic) {
        if(null != mqttClient&& !mqttClient.isConnected()) {
            try {
                mqttClient.unsubscribe(topic);
            } catch (MqttException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            System.out.println("mqttClient is error");
        }
    }


}
