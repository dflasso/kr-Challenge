package ec.com.danyLassoSolution.emailNotifier.senders.imp;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import ec.com.danyLassoSolution.emailNotifier.exceptions.BusinessRulesException;
import ec.com.danyLassoSolution.emailNotifier.model.constants.AppErrorsCodesAndMessages;
import ec.com.danyLassoSolution.emailNotifier.model.dto.ApiError;
import ec.com.danyLassoSolution.emailNotifier.model.dto.ApiValidationError;
import ec.com.danyLassoSolution.emailNotifier.model.dto.LoginNotification;
import ec.com.danyLassoSolution.emailNotifier.model.dto.Notification;
import ec.com.danyLassoSolution.emailNotifier.model.dto.NotificationSendResponse;
import ec.com.danyLassoSolution.emailNotifier.senders.Notifier;
import ec.com.danyLassoSolution.emailNotifier.senders.SenderProvider;
import ec.com.danyLassoSolution.emailNotifier.utils.LoggerCustom;

@Service("email-notifier")
@Primary
public class EmailNotifier implements Notifier {

	@Autowired
	@Qualifier("sender-email-smtp")
	private SenderProvider senderProvider;

	@Autowired
	private LoggerCustom logger;
	
	@Override
	@Async
	public void sendEmailLoginAsync(Optional<? extends Notification> notification) {
		this.sendEmailLoginSync(notification);
	}

	@Override
	public NotificationSendResponse<? extends Notification> sendEmailLoginSync(
			Optional<? extends Notification> notification) {
		logger.info(String.format("Send Email Login to:  %s", notification.get().getTo()));
		JavaMailSender sender = senderProvider.getSenderDefault();
		MimeMessage message = sender.createMimeMessage();

		Optional<LoginNotification> optLoginNotification = LoginNotification.build(notification);

		if(optLoginNotification.isEmpty()) {
			ApiError apiError = new ApiError(HttpStatus.EXPECTATION_FAILED, AppErrorsCodesAndMessages.EMAIL_LOGIN_DTO_ILLEGAL_ARGUMENTS.getMessage());
			
			ApiValidationError subError =  ApiValidationError.builder()
															.code(AppErrorsCodesAndMessages.EMAIL_LOGIN_DTO_ILLEGAL_ARGUMENTS.getCode())
															.object("notification")
															.message(AppErrorsCodesAndMessages.EMAIL_LOGIN_DTO_ILLEGAL_ARGUMENTS.getMessage())
															.build();
			apiError.addSubError(subError);
			logger.error(apiError, getClass(), "sendEmailLoginSync");
			throw new BusinessRulesException(apiError);
		}
		
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setTo(optLoginNotification.get().getTo());
			helper.setSubject(optLoginNotification.get().getSubject());
			helper.setText(optLoginNotification.get().getMessage());
			helper.setFrom(senderProvider.getSettingsDefault().getFrom());
			sender.send(message);
			logger.info(String.format("Send Email Login Finished to:  %s", notification.get().getTo()));
		} catch (MessagingException e) {
			System.out.println("Error");
		}
		return null;
	}

	@Override
	public NotificationSendResponse<? extends Notification> sendEmailRegister(
			Optional<? extends Notification> notification) {
		// TODO Auto-generated method stub
		return null;
	}

}
