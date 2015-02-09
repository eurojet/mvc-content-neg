package rewardsonline;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Bean;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;

//import org.springframework.context.annotation.Import;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

//import org.springframework.boot.autoconfigure.SpringBootApplication;
//@SpringBootApplication same as: 
//@Configuration @EnableAutoConfiguration @ComponentScan


/**
 * Central application class containing both general application and web 
 * configuration as well as a main-method to bootstrap the app using Spring Boot.
 * 
 * @see #main(String[])
 * @see SpringApplication
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
@ImportResource( { "classpath:security-config.xml" } )
//@EnableJpaRepositories
//@Import(RepositoryRestMvcConfiguration.class)
@EnableHypermediaSupport(type = HypermediaType.HAL)  //@EnableEntityLinks
public class Application extends SpringBootServletInitializer {
	public static String CURIE_NAMESPACE = "hal";

    @Bean
    public CurieProvider curieProvider() {
        return new DefaultCurieProvider(CURIE_NAMESPACE, 
                    new UriTemplate("http://www.example.com/docs/{rel}"));
    }

    /**
     * Bootstraps the application in standalone mode (i.e. java -jar).
     * 
     * @param args
     */
    public static void main(String[] args) {
        //SpringApplication.run(Application.class, args);
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
/*        
        System.out.println("Let's inspect the beans provided by Spring Boot:");
        
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println(beanName);
        }     */
    }

    /**
     * Allows the application to be started when being deployed into a Servlet 3 container.
     * 
     * @see org.springframework.boot.web.SpringBootServletInitializer#configure(org.springframework.boot.builder.SpringApplicationBuilder)
     */
    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
}
