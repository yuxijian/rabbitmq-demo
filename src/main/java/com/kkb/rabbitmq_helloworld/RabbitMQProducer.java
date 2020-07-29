package com.kkb.rabbitmq_helloworld;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQProducer {

    private static String QUEUE_NAME = "hello_queue";
    private static String EXCHANGE_NAME = "hello_exchange";

    public static void main(String[] args) throws Exception{

        // 1. 创建一个 ConnectionFactory 工厂
        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setVirtualHost("/");

        // 2. 创建一个 Connection
        Connection conn = factory.newConnection();

        // 3. 获取一个信道 Channel
        Channel channel = conn.createChannel();

        // 声明一个交换机
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT,true);

        // 4. 通过 Channel 发送消息
        String msg = "hello world,rabbitmq";

        for (int i = 0; i < 100; i++) {
            //这里的hello是Routing key
            channel.basicPublish(EXCHANGE_NAME, "hello", null, msg.getBytes());
            // System.out.println(i);
            // Thread.sleep(1000);
        }

//        // 5. 关闭资源
//        channel.close();
//        conn.close();


    }

}
