package sv.com.grupogd.clientsdte.model.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public class DatabaseCredentialService {

	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	public Map<String, Object> getCredentialsForCompany(String companyId) {
        String sql = "SELECT jdbc_url, username, password, driver_class_name FROM company_databases WHERE company_id = ?";
        return jdbcTemplate.queryForMap(sql, companyId);
    }


}
