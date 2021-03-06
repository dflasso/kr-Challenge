package ec.com.danyLassoSolution.emailNotifier.services.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import ec.com.danyLassoSolution.emailNotifier.exceptions.NotFoundException;
import ec.com.danyLassoSolution.emailNotifier.model.constants.AppErrorsCodesAndMessages;
import ec.com.danyLassoSolution.emailNotifier.model.dto.ApiError;
import ec.com.danyLassoSolution.emailNotifier.model.dto.ApiValidationError;
import ec.com.danyLassoSolution.emailNotifier.model.entities.EmailProviderSetting;
import ec.com.danyLassoSolution.emailNotifier.repositories.EmailProviderSettingRepository;
import ec.com.danyLassoSolution.emailNotifier.utils.LoggerCustom;

/**
 * Servicio orientado a la busqueda de las configuraciones del Email
 * @author dlasso
 *
 */
@Service
public class FindEmailProviderSettingService {

	@Autowired
	private EmailProviderSettingRepository emailProviderSettingsDao;
	
	@Autowired
	private LoggerCustom logger;
	
	/**
	 * Encuentra las credenciales por defecto
	 * @throws NotFoundException - Si no encuentra ningun registro
	 * @apiNote Si encuentra más de una, toma la primera
	 * @return EmailProviderSetting
	 */
	public EmailProviderSetting findSettingsByDefault() {
		
		List<EmailProviderSetting> ltsEmailSettings = emailProviderSettingsDao.findByByDefault(true);
		
		
		
		if(ltsEmailSettings == null || ltsEmailSettings.isEmpty()) {
			ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, AppErrorsCodesAndMessages.CREDENTIALS_BY_DEFAULT_NOT_FOUND.getMessage());
			
			ApiValidationError apiValidationError = ApiValidationError.builder()
				.code(AppErrorsCodesAndMessages.CREDENTIALS_BY_DEFAULT_NOT_FOUND.getCode())
				.message(AppErrorsCodesAndMessages.CREDENTIALS_BY_DEFAULT_NOT_FOUND.getMessage())
				.object(EmailProviderSetting.class.getSimpleName())
				.build();
			
			apiError.addSubError(apiValidationError);
			
			logger.error(apiError, getClass(), "findSettingsByDefault()");
			
			throw new NotFoundException(apiError);
		}
		
		
		if(ltsEmailSettings.size() > 1) {
			logger.warn(getClass(), "findSettingsByDefault", AppErrorsCodesAndMessages.EXIST_MORE_THAT_ONE_CREDENTIALS_BY_DEFAULT.getMessage());
		}
		
		return ltsEmailSettings.get(0);
	}
}
