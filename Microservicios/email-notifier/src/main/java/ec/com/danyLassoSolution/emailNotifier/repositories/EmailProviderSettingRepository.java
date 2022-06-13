package ec.com.danyLassoSolution.emailNotifier.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ec.com.danyLassoSolution.emailNotifier.model.entities.EmailProviderSetting;

public interface EmailProviderSettingRepository  extends JpaRepository<EmailProviderSetting, Long> {

	List<EmailProviderSetting> findByByDefault(Boolean byDefault);
}
