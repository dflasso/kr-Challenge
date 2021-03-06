package ec.com.danyLassoSolution.emailNotifier.senders.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import ec.com.danyLassoSolution.emailNotifier.model.entities.EmailProviderSetting;
import ec.com.danyLassoSolution.emailNotifier.senders.SenderProvider;
import ec.com.danyLassoSolution.emailNotifier.services.model.FindEmailProviderSettingService;
import ec.com.danyLassoSolution.emailNotifier.services.model.TransformerEmailProviderSettingProperties;

@Service("sender-email-smtp")
@Primary
public class SenderProviderSMTP implements SenderProvider {
	
	@Autowired
	private FindEmailProviderSettingService findEmailProviderSettingService; 
	
	@Autowired
	private TransformerEmailProviderSettingProperties transformerEmailProviderSettingProperties;
	
	private JavaMailSender senderDefault;
	
	private EmailProviderSetting emailProviderSettingDefault;
	
	@Override
	public void loadDefaultProvider() {
		EmailProviderSetting emailProviderSetting = findEmailProviderSettingService.findSettingsByDefault();
		
		JavaMailSenderImpl sender = new JavaMailSenderImpl();
		sender.setHost(emailProviderSetting.getHost());
		sender.setPort(emailProviderSetting.getPort());
		sender.setUsername(emailProviderSetting.getUsername());
		sender.setPassword(emailProviderSetting.getPassword());
		
		if(emailProviderSetting.getPropertiesAsJson() != null && !emailProviderSetting.getPropertiesAsJson().trim().isEmpty()) {
			Map<String, Object> properties = transformerEmailProviderSettingProperties.transformToMap(emailProviderSetting);
			sender.getJavaMailProperties().putAll(properties);
		}
		
		this.senderDefault = sender;
		this.emailProviderSettingDefault = emailProviderSetting;
	}

	@Override
	public JavaMailSender getSenderDefault() {
		return this.senderDefault;
	}
	
	@Override
	public EmailProviderSetting getSettingsDefault() {
		return emailProviderSettingDefault;
	}


}
