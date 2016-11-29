package org.suliga.trantor.config;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//@ComponentScan
//@EnableAutoConfiguration
//@EnableTransactionManagement
public class TrantorMainConfig {
    @Autowired
    @Bean(name="sessionFactory")
    public SessionFactory sessionFactory(HibernateEntityManagerFactory factory) {
        return factory.getSessionFactory();
    }  
    
	@Bean
    public ViewResolver jspViewResolver() {
        //ViewResolver resolver = new InternalResourceViewResolver();
		InternalResourceViewResolver  resolver = new InternalResourceViewResolver ();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        //resolver.setViewClass(JstlView.class);
        resolver.setViewNames("jsp/*");
        //resolver.s
        resolver.setOrder(1);
        return resolver;
    }
    
/*	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(initiateHttpConnector());
		return tomcat;
	}

	private Connector initiateHttpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		connector.setPort(8080);
		connector.setSecure(false);
		connector.setRedirectPort(8443);

		return connector;
	}*/
	
/*	@Bean
    public ViewResolver jspViewResolver() {
        //ViewResolver resolver = new InternalResourceViewResolver();
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
        resolver.setPrefix("/WEB-INF/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setViewNames("jsp/*");
        resolver.setOrder(1);
        return resolver;
    }*/
	
/*	//@Bean
	public ViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		return viewResolver;
	}
	
	//@Bean
	public TemplateEngine templateEngine(TemplateResolver templateResolver) {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver);
		return templateEngine;
	}
	
	//@Bean 
	TemplateResolver templateResolver() {
		TemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/templates/bogus/");
		resolver.setSuffix("*.html");
		//resolver.setViewNames("thymeleaf/*");
		resolver.setTemplateMode("HTML5");
		resolver.setOrder(1);
		return resolver;
	}*/
	
	/*@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
	    return new HibernateJpaSessionFactoryBean();
	}*/
	
/*	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Bean
	public SessionFactory getSessionFactory() {
	    if (entityManagerFactory.unwrap(SessionFactory.class) == null) {
	        throw new NullPointerException("factory is not a hibernate factory");
	    }
	    return entityManagerFactory.unwrap(SessionFactory.class);
	}*/
	
/*	   @Autowired
	    @Bean(name="sessionFactory")
	    public SessionFactory sessionFactory(HibernateEntityManagerFactory factory) {
	        return factory.getSessionFactory();
	    }  */
	
/*	@Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }*/
	
/*	@Bean
	public SessionFactory sessionFactory() {
		return new LocalSessionFactoryBuilder(getDataSource())
		   .addAnnotatedClasses(RareBook.class)
		   .buildSessionFactory();
	}
	@Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/trantor");
	    dataSource.setUsername("tom");
	    dataSource.setPassword("Secret1");
	 
	    return dataSource;
	}*/
}






























