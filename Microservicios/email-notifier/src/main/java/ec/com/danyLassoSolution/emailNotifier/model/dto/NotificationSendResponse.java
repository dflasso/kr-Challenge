package ec.com.danyLassoSolution.emailNotifier.model.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * 
 * @author dlasso
 *
 * @param <T> Implementación de la información relevante respecto a la notificación  
 */
@Data
public class NotificationSendResponse<T extends Notification> {
	private Map<String,  String > moreInformation;
    private T notification;
    
    
	public NotificationSendResponse(T notification) {
		super();
		this.moreInformation = new HashMap<String, String>();
		this.notification = notification;
	}
    
	public NotificationSendResponse() {
		super();
		this.moreInformation = new HashMap<String, String>();
	}
	
	public void addMoreInfo(String key, String value) {
		this.moreInformation.put(key, value);
	}
	

}
