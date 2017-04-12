package org.maxwell.springboot.http2;

import javax.annotation.PreDestroy;

import org.apache.coyote.http2.Http2Protocol;
import org.maxwell.springboot.http2.controller.SampleController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world! with Http2
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
@ComponentScan(basePackageClasses = SampleController.class)
public class SpringBootWithHttp2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootWithHttp2.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootWithHttp2.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
		LOGGER.info("0;Started http2 Service");
	}

	@PreDestroy
	public static void shutDownMethod() {
		LOGGER.info("0;http2 is shutting down.");
	}

	@Bean
	public EmbeddedServletContainerCustomizer tomcatCustomizer() {
		return container -> {
			if (container instanceof TomcatEmbeddedServletContainerFactory) {
				((TomcatEmbeddedServletContainerFactory) container).addConnectorCustomizers((connector) -> {
					connector.addUpgradeProtocol(new Http2Protocol());
				});
			}
		};
	}
}
