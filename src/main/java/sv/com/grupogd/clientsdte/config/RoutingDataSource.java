package sv.com.grupogd.clientsdte.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import sv.com.grupogd.clientsdte.model.service.DatabaseCredentialService;

public class RoutingDataSource extends AbstractRoutingDataSource {

    @Autowired
    private DatabaseCredentialService credentialService;

    private Map<Object, Object> targetDataSources = new HashMap<>();

    @Override
    protected DataSource determineTargetDataSource() {
        String companyId = DatabaseContextHolder.getCurrentCompany();
        if (!targetDataSources.containsKey(companyId)) {
            Map<String, Object> credentials = credentialService.getCredentialsForCompany(companyId);
            DataSource dataSource = createDataSource(
                credentials.get("jdbc_url").toString(),
                credentials.get("username").toString(),
                credentials.get("password").toString(),
                credentials.get("driver_class_name").toString()
            );
            targetDataSources.put(companyId, dataSource);
            setTargetDataSources(targetDataSources);
            afterPropertiesSet(); // Required to refresh the targetDataSources map
        }
        return (DataSource) targetDataSources.get(companyId);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getCurrentCompany();
    }

    public DataSource createDataSource(String jdbcUrl, String username, String password, String driverClassName) {
        return DataSourceBuilder.create()
                .url(jdbcUrl)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

}