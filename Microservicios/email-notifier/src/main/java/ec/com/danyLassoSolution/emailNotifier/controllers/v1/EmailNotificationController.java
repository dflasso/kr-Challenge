package ec.com.danyLassoSolution.emailNotifier.controllers.v1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.com.danyLassoSolution.emailNotifier.model.dto.LoginNotification;
import ec.com.danyLassoSolution.emailNotifier.senders.Notifier;

@RestController
@RequestMapping("/v1.0.0")
public class EmailNotificationController {
	
	@Autowired
	@Qualifier("email-notifier")
	private Notifier notifier;

	@PostMapping("/send/email")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void sendNotificationLgin(@RequestBody LoginNotification loginNotification) {
		notifier.sendEmailLoginAsync(Optional.of(loginNotification));
	}
}
