package local.kapinos.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import local.kapinos.jpa.NewsEntity;

@WebServlet(name = "PostMessage", urlPatterns = {"/PostMessage"})
public class PostMessage extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Resource(mappedName="java:app/jms/NewMessageFactory")
    private  ConnectionFactory connectionFactory;

    @Resource(mappedName="java:app/jms/NewMessage")
    private  Queue queue;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// Add the following code to send the JMS message
    	String title=request.getParameter("title");
    	String body=request.getParameter("body");
    	if ((title!=null) && (body!=null)) {
    	    try {
    	        Connection connection = connectionFactory.createConnection();
    	        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    	        MessageProducer messageProducer = session.createProducer(queue);

    	        ObjectMessage message = session.createObjectMessage();
    	        // here we create NewsEntity, that will be sent in JMS message
    	        NewsEntity e = new NewsEntity();
    	        e.setTitle(title);
    	        e.setBody(body);

    	        message.setObject(e);                
    	        messageProducer.send(message);
    	        messageProducer.close();
    	        connection.close();
    	        
    	        response.sendRedirect("ListNews");

    	    } catch (JMSException ex) {
    	        ex.printStackTrace();
    	    }
    	}
    }
}
