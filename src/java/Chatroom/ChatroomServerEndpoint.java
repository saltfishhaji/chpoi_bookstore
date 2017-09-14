/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chatroom;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.websocket.OnMessage;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.OnOpen;
import javax.websocket.OnClose;
import javax.websocket.Session;

@ServerEndpoint("/chatroom")
public class ChatroomServerEndpoint{
    
    static Set<Session> USERS=Collections.synchronizedSet(new HashSet<Session>());
    
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Session " + session.getId() + " connected.");
        USERS.add(session);
    }
    
    @OnClose
    public void onClose(Session session)
    {
        USERS.remove(session);
        System.out.println("Session "+session.getId()+" removed.");
    }
    
    @OnMessage
    public void onMessage(Session session,String message)
    {
        System.out.println("Received message from session:"+session.getId()+":"+message);
        Iterator<Session> iter=USERS.iterator();
        while(iter.hasNext())
        {
            try
            {
                iter.next().getBasicRemote().sendText(message);
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
    }
}
