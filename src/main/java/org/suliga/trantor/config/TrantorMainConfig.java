package org.suliga.trantor.config;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class TrantorMainConfig {
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory(HibernateEntityManagerFactory factory) {
		return factory.getSessionFactory();
	}

	@Bean
	public ViewResolver jspViewResolver() {
		// ViewResolver resolver = new InternalResourceViewResolver();
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/");
		resolver.setSuffix(".jsp");
		// resolver.setViewClass(JstlView.class);
		resolver.setViewNames("jsp/*");
		resolver.setOrder(1);
		return resolver;
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("greetings")
				.apiInfo(apiInfo())
				.select()
				.paths(PathSelectors.regex("/rest/greeting.*"))
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Trantor Swagger Title")
				.description("Trantor Swagger Desc")
				.termsOfServiceUrl("http://termsofservice.com")
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/tomsuliga/trantor/master/LICENSE")
				.version("2.0")
				.build();
	}
	
/*	@Bean
	public ServletRegistrationBean h2servletRegistration() {
	    ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
	    registration.addUrlMappings("/console/*");
	    return registration;
	}*/
	
 /*   private static final String TEMP_DIRECTORY = System.getProperty("java.io.tmpdir");
    
    @Bean(name = "mainDataSource")
    public DataSource createMainDataSource() {
        JdbcDataSource ds = new JdbcDataSource();
        ds.setURL("jdbc:h2:"+TEMP_DIRECTORY+"/testdb;MODE=MySQL");
        return ds;
    }*/


	/*
	 * @Bean public EmbeddedServletContainerFactory servletContainer() {
	 * TomcatEmbeddedServletContainerFactory tomcat = new
	 * TomcatEmbeddedServletContainerFactory() {
	 * 
	 * @Override protected void postProcessContext(Context context) {
	 * SecurityConstraint securityConstraint = new SecurityConstraint();
	 * securityConstraint.setUserConstraint("CONFIDENTIAL"); SecurityCollection
	 * collection = new SecurityCollection(); collection.addPattern("/*");
	 * securityConstraint.addCollection(collection);
	 * context.addConstraint(securityConstraint); } };
	 * 
	 * tomcat.addAdditionalTomcatConnectors(initiateHttpConnector()); return
	 * tomcat; }
	 * 
	 * private Connector initiateHttpConnector() { Connector connector = new
	 * Connector("org.apache.coyote.http11.Http11NioProtocol");
	 * connector.setScheme("http"); connector.setPort(8080);
	 * connector.setSecure(false); connector.setRedirectPort(8443);
	 * 
	 * return connector; }
	 */

	/*
	 * @Bean public ViewResolver jspViewResolver() { //ViewResolver resolver =
	 * new InternalResourceViewResolver(); UrlBasedViewResolver resolver = new
	 * UrlBasedViewResolver(); resolver.setPrefix("/WEB-INF/");
	 * resolver.setSuffix(".jsp"); resolver.setViewClass(JstlView.class);
	 * resolver.setViewNames("jsp/*"); resolver.setOrder(1); return resolver; }
	 */

	/*
	 * //@Bean public ViewResolver thymeleafViewResolver(SpringTemplateEngine
	 * templateEngine) { ThymeleafViewResolver viewResolver = new
	 * ThymeleafViewResolver(); viewResolver.setTemplateEngine(templateEngine);
	 * return viewResolver; }
	 * 
	 * //@Bean public TemplateEngine templateEngine(TemplateResolver
	 * templateResolver) { SpringTemplateEngine templateEngine = new
	 * SpringTemplateEngine();
	 * templateEngine.setTemplateResolver(templateResolver); return
	 * templateEngine; }
	 * 
	 * //@Bean TemplateResolver templateResolver() { TemplateResolver resolver =
	 * new ServletContextTemplateResolver();
	 * resolver.setPrefix("/templates/bogus/"); resolver.setSuffix("*.html");
	 * //resolver.setViewNames("thymeleaf/*");
	 * resolver.setTemplateMode("HTML5"); resolver.setOrder(1); return resolver;
	 * }
	 */

	/*
	 * @Bean public HibernateJpaSessionFactoryBean sessionFactory() { return new
	 * HibernateJpaSessionFactoryBean(); }
	 */

	/*
	 * @Autowired private EntityManagerFactory entityManagerFactory;
	 * 
	 * @Bean public SessionFactory getSessionFactory() { if
	 * (entityManagerFactory.unwrap(SessionFactory.class) == null) { throw new
	 * NullPointerException("factory is not a hibernate factory"); } return
	 * entityManagerFactory.unwrap(SessionFactory.class); }
	 */

	/*
	 * @Autowired
	 * 
	 * @Bean(name="sessionFactory") public SessionFactory
	 * sessionFactory(HibernateEntityManagerFactory factory) { return
	 * factory.getSessionFactory(); }
	 */

	/*
	 * @Bean public HibernateJpaSessionFactoryBean sessionFactory() { return new
	 * HibernateJpaSessionFactoryBean(); }
	 */

	/*
	 * @Bean public SessionFactory sessionFactory() { return new
	 * LocalSessionFactoryBuilder(getDataSource())
	 * .addAnnotatedClasses(RareBook.class) .buildSessionFactory(); }
	 * 
	 * @Bean public DataSource getDataSource() { BasicDataSource dataSource =
	 * new BasicDataSource();
	 * dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	 * dataSource.setUrl("jdbc:mysql://localhost:3306/trantor");
	 * dataSource.setUsername("tom"); dataSource.setPassword("Secret1");
	 * 
	 * return dataSource; }
	 */
}
