package ec.com.danyLassoSolution.emailNotifier.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "email_settings")
@Data
public class EmailProviderSetting {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_email_settings")
	private Long id;
	
	@Column(name = "host_emset", length = 1023)
	private String host;
	
	@Column(name = "port_emset", length = 200)
    private Integer port;
	
	@Column(name = "username_emset", length = 200)
	private String username;

	@Column(name = "password_emset", length = 200)
    private String password;
	
	@Column(name = "from_emset", length = 200)
    private String from;
	
	@Column(name = "properties_emset", length = 2047)
    private String propertiesAsJson;

	@Column(name = "by_default_emset")
	private Boolean byDefault;
}