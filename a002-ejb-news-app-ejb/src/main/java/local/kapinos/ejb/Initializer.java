package local.kapinos.ejb;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSDestinationDefinitions;
import javax.jms.Queue;

/* 
 * Unfortunately doesn't work
 *
@JMSDestinationDefinitions(
        value = {
            @JMSDestinationDefinition(
                    name = "java:/queue/duke-queue",
                    interfaceName = "javax.jms.Queue",
                    destinationName = "duke"
            ),
            @JMSDestinationDefinition(
                    name = "java:/topic/duke-topic",
                    interfaceName = "javax.jms.Topic",
                    destinationName = "duke-topic"
            )
        }
)*/

@Startup
@Singleton
public class Initializer {
	//@Resource(lookup = "java:/queue/duke-queue")
	//    private Queue queue;    
}
