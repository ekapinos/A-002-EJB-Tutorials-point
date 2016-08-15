package local.kapinos.ejb;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import local.kapinos.jpa.NewsEntity;

@MessageDriven(mappedName = "java:app/jms/NewMessage", activationConfig = {
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })		
public class NewMessage implements MessageListener {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Resource
	private MessageDrivenContext mdc;
	
	@PersistenceContext(unitName = "NewsApp-ejbPU")
	private EntityManager em;

	@PostConstruct
	private void postConstruct() {
		logger.info("---------------------------------------------");	
		logger.info("NewMessage postConstruct");
		logger.info("---------------------------------------------");	
	}

	public void onMessage(Message message) {
		logger.info("---------------------------------------------");	
		logger.info("NewMessage onMessage");
		logger.info("---------------------------------------------");	

		ObjectMessage msg = null;
		try {
			if (message instanceof ObjectMessage) {
				msg = (ObjectMessage) message;
				NewsEntity e = (NewsEntity) msg.getObject();
				save(e);
			}
		} catch (JMSException e) {
			e.printStackTrace();
			mdc.setRollbackOnly();
		} catch (Throwable te) {
			te.printStackTrace();
		}
	}

	public void save(Object object) {
		em.persist(object);
	}
}
