package ec.com.danyLassoSolution.emailNotifier.model.dto;

import java.util.Optional;

import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 
 * @author dlasso
 * Datos a ser enviados en el inicio de sesión
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginNotification extends Notification {

	private String username;
	private String host;
	private String dateLogin;
	
	public static Optional<LoginNotification> build(Optional<? extends Notification> notification) {
		if(notification.get() instanceof LoginNotification) {
			return Optional.of((LoginNotification) notification.get()); 
		}
		
		return Optional.empty();
	}
}
