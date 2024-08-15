package sv.com.grupogd.clientsdte.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

    @Autowired
    private CompanyInterceptor companyInterceptor;

    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(companyInterceptor);
    }

}
