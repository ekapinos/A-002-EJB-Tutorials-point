package local.kapinos.ejb;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Session Bean implementation class SessionManagerBean
 */
@Singleton
@LocalBean
@WebListener
public class SessionManagerBean implements HttpSessionListener{

	private Logger logger = Logger.getLogger(getClass().getName());
	
	private static int counter = 0;
	
	public SessionManagerBean(){
		logger.info("---------------------------------------------");	
		logger.info("SessionManagerBean created");	
		logger.info("---------------------------------------------");	
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		counter++;
		logger.info("---------------------------------------------");	
		logger.info("SessionManagerBean sessionCreated " + counter);	
		logger.info("---------------------------------------------");	
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		counter--;
		logger.info("---------------------------------------------");	
		logger.info("SessionManagerBean sessionDestroyed " + counter);	
		logger.info("---------------------------------------------");	
	}

	public int getActiveSessionsCount() {
        return counter;
    }
}
