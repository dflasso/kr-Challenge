package ec.com.danyLassoSolution.emailNotifier.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import ec.com.danyLassoSolution.emailNotifier.senders.SenderProvider;

@Component
public class LoadDefaultSettings implements ApplicationListener<ApplicationReadyEvent> {
	
	@Autowired
	@Qualifier("sender-email-smtp")
	private SenderProvider senderProvider;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		senderProvider.loadDefaultProvider();
	}

}
