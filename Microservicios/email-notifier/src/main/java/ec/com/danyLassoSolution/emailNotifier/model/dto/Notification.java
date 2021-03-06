package ec.com.danyLassoSolution.emailNotifier.model.dto;

import lombok.Data;

/**
 * 
 * @author dany_lasso
 * Información Básica que respondera los servicios al enviar un correo
 */
@Data
public abstract class Notification {
	private String from;
    private String subject;
    private String to;
    private String message;
    private Boolean sentSuccessfully;
}
