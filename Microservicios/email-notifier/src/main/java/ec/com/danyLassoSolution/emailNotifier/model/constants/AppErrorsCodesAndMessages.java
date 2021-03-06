package ec.com.danyLassoSolution.emailNotifier.model.constants;

public enum AppErrorsCodesAndMessages {
	FIELD_VALIDATION("N0001", "Field validation"),
	
	// EMAIL ERRORS
	CREDENTIALS_BY_DEFAULT_NOT_FOUND("N0002", "CREDENTIALS BY DEFAULT NOT FOUND"),
	EXIST_MORE_THAT_ONE_CREDENTIALS_BY_DEFAULT("N0003", "EXIST MORE THAT ONE CREDENTIALS BY DEFAULT"),
	CREDENTIALS_PROPERTIES_CANNOT_SERIALIZER("N0004", "CREDENTIALS PROPERTIES CANNOT SERIALIZER"),
	
	EMAIL_LOGIN_DTO_ILLEGAL_ARGUMENTS("N0005", "EMAIL LOGIN ILLEGAL ARGUMENTS"),
	
	;
	
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public String getMessage() {
		return message;
	}
	private AppErrorsCodesAndMessages(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	
	
}
