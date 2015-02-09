package rewardsonline;

import java.net.URI;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;





@Configuration
public class MvcConfiguration extends WebMvcConfigurerAdapter {
    //Falling Back On the "Default" Servlet To Serve Resources <mvc:default-servlet-handler/>
    @Override 
    public void configureDefaultServletHandling(
            DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("welcome");
        registry.addViewController("/home").setViewName("welcome");
        registry.addViewController("/denied").setViewName("denied");
    }

     @Override
    public void addInterceptors(InterceptorRegistry registry) {
//      registry.addInterceptor(new LocalInterceptor());
//      registry.addInterceptor(new SecurityInterceptor()).addPathPatterns("/secure/*");
        registry.addInterceptor(new rewardsonline.ViewNameInModelInterceptor());
    }
/*
    @Override
    public void configureContentNegotiation(
            ContentNegotiationConfigurer configurer) {
        // Simple strategy: only path extension is taken into account
        configurer.favorPathExtension(true);
                //.ignoreAcceptHeader(true);
                //.defaultContentType(MediaType.APPLICATION_JSON);
    }
*/
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
                DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean emf = 
                new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan("rewardsonline.accounts");
        /* Properties prop = new Properties();
        prop.setProperty("hibernate.format_sql", "true");
        emf.setJpaProperties(prop);  */
        return emf;
    }

    @Bean
    @Profile("production")
    public DataSource productionDataSource() throws Exception {
        if (databaseUrl==null) databaseUrl = env.getProperty("DATABASE_URL");
        URI dbUrl = new URI(databaseUrl);
        String[] userinfo = dbUrl.getUserInfo().split(":");
System.setProperty("jdbc.drivers",  "com.mysql.jdbc.Driver:org.postgresql.Driver:org.hsqldb.jdbcDriver");
        org.apache.tomcat.jdbc.pool.DataSource datasource = 
                new org.apache.tomcat.jdbc.pool.DataSource();
        datasource.setUrl( "jdbc:postgresql://" + dbUrl.getHost() + ":" 
                           + dbUrl.getPort() + dbUrl.getPath() );
        datasource.setUsername(userinfo[0]);
        datasource.setPassword(userinfo[1]);
        datasource.setInitialSize(1);
        datasource.setMaxActive(10);
        return datasource;
    }

    @Value("#{systemEnvironment['DATABASE_URL']}")
              //systemProperties.DATABASE_URL}")
    String databaseUrl;

    @Autowired
    org.springframework.core.env.Environment env;
}
