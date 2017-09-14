/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JMS;

/**
 *
 * @author Administrator
 */
import Order.Order.Order;
import Order.OrderService;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * @author liang
 * @description  队列消息监听器
 * 
 */
public class QueueReceiver implements MessageListener {
        private static OrderService service;
        public void setService(OrderService service){QueueReceiver.service=service;}
	@Override
	public void onMessage(Message message) {
            ObjectMessage temp=(ObjectMessage)message;
            Order order;
            
		try {
                    order=(Order)temp.getObject();
                    boolean p = service.updateOrder(order);
                    if(p)
                        System.out.println("QueueReceiver接收到消息");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
