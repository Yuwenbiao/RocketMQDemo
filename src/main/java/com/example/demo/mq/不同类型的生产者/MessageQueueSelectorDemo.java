package com.example.demo.mq.不同类型的生产者;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * MessageQueueSelector示例
 *
 * @author yuwb@corp.21cn.com
 * @date 19-4-15 下午3:53
 */
public class MessageQueueSelectorDemo implements MessageQueueSelector {
    @Override
    public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
        int id = Integer.parseInt(o.toString());
        int idMainIndex = id / 100;
        int size = list.size();
        int index = idMainIndex % size;
        return list.get(index);
    }
}
