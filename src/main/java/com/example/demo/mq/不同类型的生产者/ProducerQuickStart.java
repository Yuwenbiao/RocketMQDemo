package com.example.demo.mq.不同类型的生产者;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * DefaultMQProducer
 *
 * @author yuwb@corp.21cn.com
 * @date 19-4-11 下午2:54
 */
public class ProducerQuickStart {
    public static void main(String[] args) throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("testGroup");
        producer.setInstanceName("instance1");
        producer.setRetryTimesWhenSendFailed(3);
        producer.setNamesrvAddr("172.17.0.2:9876");
        producer.start();
        for (int i = 0; i < 1000; i++) {
            try {
                Message msg = new Message("TopicTest", "TagA", ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
                producer.send(msg, new SendCallback() {
                    @Override
                    public void onSuccess(SendResult sendResult) {
                        System.out.printf("%s%n", sendResult);
                        sendResult.getSendStatus();
                    }

                    @Override
                    public void onException(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }
}
