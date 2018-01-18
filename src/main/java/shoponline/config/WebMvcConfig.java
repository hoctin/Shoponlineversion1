package shoponline.config;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

  private static final Charset UTF8 = Charset.forName("UTF-8");

  //Config UTF-8 Encoding
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
    stringHttpMessageConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("text", "plain", UTF8)));
    converters.add(stringHttpMessageConverter);
  }

  //Static Resource Config
  //equivalents for <mvc:resources/> tags

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(347126186);
    registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(347126186);
    registry.addResourceHandler("/js/**").addResourceLocations("/js").setCachePeriod(347126186);
  }

  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

}
