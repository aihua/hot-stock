/**
 * 
 */
package assgn.stock;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Jogireddy Kotam
 * @date 16-May-2017
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "assgn.stock")
public class HotStockCoreApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(HotStockCoreApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(HotStockCoreApplication.class, args);
    }
    
    @Bean
    public Mapper getMapper() {
        return new DozerBeanMapper();
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("i18n/messages_en"); // name of the resource bundle
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
    
    @Bean
    public WebMvcConfigurerAdapter forwardToIndex() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                // forward requests to / and / to their index.html
                registry.addViewController("/").setViewName(
                        "forward:/");
                registry.addViewController("/admin/**").setViewName(
                        "forward:/");
                registry.addViewController("/broker/**").setViewName(
                        "forward:/");
                
            }
        };
    }
}
