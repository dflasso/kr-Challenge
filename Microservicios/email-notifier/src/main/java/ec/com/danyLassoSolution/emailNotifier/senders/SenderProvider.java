package ec.com.danyLassoSolution.emailNotifier.senders;

import org.springframework.mail.javamail.JavaMailSender;

import ec.com.danyLassoSolution.emailNotifier.model.entities.EmailProviderSetting;


public interface SenderProvider {
	
	public void loadDefaultProvider();

	public JavaMailSender getSenderDefault();
	
	public EmailProviderSetting getSettingsDefault();
	
}
