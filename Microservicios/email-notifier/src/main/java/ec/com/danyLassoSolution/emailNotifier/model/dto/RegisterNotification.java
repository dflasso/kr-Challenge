package ec.com.danyLassoSolution.emailNotifier.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author dlasso
 * Datos enviados al registrar un usuario
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RegisterNotification extends Notification {
	
	private String username;
	private String rol;
	private String tempPassword;
	private String expirationDatePassword;
}
