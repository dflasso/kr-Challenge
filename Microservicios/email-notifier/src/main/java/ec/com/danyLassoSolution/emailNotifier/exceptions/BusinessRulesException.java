package ec.com.danyLassoSolution.emailNotifier.exceptions;

import ec.com.danyLassoSolution.emailNotifier.model.dto.ApiError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Excepciones lanzadas cuando se rompe con las reglas de negocio
 * @author dlasso
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BusinessRulesException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ApiError apiError;
	
	
}
