package ec.com.danyLassoSolution.emailNotifier.services.model;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import ec.com.danyLassoSolution.emailNotifier.exceptions.BusinessRulesException;
import ec.com.danyLassoSolution.emailNotifier.model.constants.AppErrorsCodesAndMessages;
import ec.com.danyLassoSolution.emailNotifier.model.dto.ApiError;
import ec.com.danyLassoSolution.emailNotifier.model.dto.ApiValidationError;
import ec.com.danyLassoSolution.emailNotifier.model.entities.EmailProviderSetting;
import ec.com.danyLassoSolution.emailNotifier.utils.LoggerCustom;

@Service
public class TransformerEmailProviderSettingProperties {
	
	private Gson gson;
	
	@Autowired
	private LoggerCustom logger;
	
	@PostConstruct
	public void init() {
		gson = new Gson();
	}
	
	public Map<String, Object> transformToMap(EmailProviderSetting emailProviderSetting){
		String propertiesAsJson = emailProviderSetting.getPropertiesAsJson();
		
		Map properties;
		
		try {
			properties = gson.fromJson(propertiesAsJson, Map.class);
			return properties;
		} catch (JsonSyntaxException e) {
			ApiError apiError =  new ApiError(HttpStatus.CONFLICT, propertiesAsJson);
			
			ApiValidationError apiSubError = ApiValidationError.builder()
												.code(AppErrorsCodesAndMessages.CREDENTIALS_PROPERTIES_CANNOT_SERIALIZER.getMessage())
												.object("EmailProviderSetting")
												.field("propertiesAsJson")
												.rejectedValue(propertiesAsJson)
												.message(AppErrorsCodesAndMessages.CREDENTIALS_PROPERTIES_CANNOT_SERIALIZER.getCode())
												.build();
			
			apiError.addSubError(apiSubError);
			
			logger.error(apiError, getClass(), "transform", e);
			
			throw new BusinessRulesException(apiError);
		}
	}
}
