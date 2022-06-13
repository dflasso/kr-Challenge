package ec.com.danyLassoSolution.emailNotifier.senders;

import java.util.Optional;

import ec.com.danyLassoSolution.emailNotifier.model.dto.Notification;
import ec.com.danyLassoSolution.emailNotifier.model.dto.NotificationSendResponse;

/**
 * 
 * @author danny_lasso
 *
 */
public interface Notifier {
	
	/**
	 * 
	 * @param notification - datos necesarios para enviar el correo de login
	 * @return Objeto con la respuesta del resultado del proceso de Envío
	 */
	public void sendEmailLoginAsync(Optional<? extends Notification> notification);
	
	/**
	 * 
	 * @param notification - datos necesarios para enviar el correo de login
	 * @return Objeto con la respuesta del resultado del proceso de Envío
	 */
	public NotificationSendResponse<? extends Notification> sendEmailLoginSync(Optional<? extends Notification> notification);
	
	/**
	 * 
	 * @param notification - datos necesarios para enviar el correo de registro
	 * @return Objeto con la respuesta del resultado del proceso de Envío  
	 */
	public NotificationSendResponse<? extends Notification> sendEmailRegister(Optional<? extends Notification> notification);
	
	
}
