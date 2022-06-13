package ec.com.danyLassoSolution.emailNotifier.exceptions;

import ec.com.danyLassoSolution.emailNotifier.model.dto.ApiError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Excepciones lanzadas en ejecución al no encontrar los datos solicitados
 * @author dlasso
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ApiError apiError;
}
