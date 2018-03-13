package shoponline.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("ShopOnlineVersion1")
@EnableTransactionManagement
//Load to Environment
@PropertySource("classpath:config_db_hibernate.properties")
public class ApplicationContextConfig {
  //The Environment class server as the property holder
  //and stores all the properties loaded by the @PropertySource
  @Autowired
  private Environment env;

  @Bean
  public ResourceBundleMessageSource messageSource() {
    ResourceBundleMessageSource rb = new ResourceBundleMessageSource();
    // Load property in message/validator.properties
    rb.setBasenames(new String[] { "messages/validator" });
    return rb;
  }

}
