package Mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.support.ManagedMap;

import java.util.HashMap;

/**
 * Created by chett_000 on 2017/05/12.
 */
public class Mqtt_sub implements MqttCallback {
    MqttClient client;
    public boolean message=false;
     public String rfid ="";
    public Mqtt_sub() throws MqttException {
        client = new MqttClient("tcp://192.168.0.134:1883", MqttClient.generateClientId());
        MqttConnectOptions conOpt = new MqttConnectOptions();
        conOpt.setCleanSession(true);
        client.connect(conOpt);
        client.setCallback(this);
        client.subscribe("rfid");


    }


    public void ClientClose() {
        try {
            client.disconnect();
            client.close();
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void connectionLost(Throwable throwable) {

    }



    @Override
    public void messageArrived(String s, MqttMessage mqttMessage)  {
rfid =  new String(mqttMessage.getPayload());
       // System.out.println(rfid);
message=true;
    }



    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }

}
