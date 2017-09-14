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
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 
 * @author liang
 * @description  队列消息生产者，发送消息到队列
 * 
 */
public class QueueSender {
	private JmsTemplate jmstemplate;
        public void setJmstemplate(JmsTemplate jmstemplate){this.jmstemplate=jmstemplate;}
	public void send(Destination destination,final Order message){
			jmstemplate.send(destination, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage(message);
			}
		});
	}
}

