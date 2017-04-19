package org.maxwell.springboot.http2;

import javax.annotation.PreDestroy;

import org.apache.coyote.http2.Http2Protocol;
import org.apache.tomcat.util.net.SSLHostConfig;
import org.apache.tomcat.util.net.SSLHostConfig.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner.Mode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;

/**
 * Hello world! with Http2
 *
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class })
public class SpringBootWithHttp2 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootWithHttp2.class);

//	@Value("${file.key-store}")
//	private Resource certificateKeystoreFile;

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

	private SSLHostConfig createOpenSSLConfig() {
		SSLHostConfig sslHostConfig = new SSLHostConfig();
		sslHostConfig.setConfigType(Type.OPENSSL);
		//sslHostConfig.set
//		sslHostConfig.setCertificateKeystoreFile(certificateKeystoreFile.getFilename());
//		sslHostConfig.setCertificateKeystorePassword("changeme");
//		sslHostConfig.setCertificateKeystoreType("PKCS12");
//		sslHostConfig.setCertificateKeyAlias("DUMMY");
		

		return sslHostConfig;

		// server.port: 8443
		// security.require-ssl=true
		// server.ssl.key-store:classpath:localhost_self-signed.p12
		// server.ssl.key-store-password: changeme
		// server.ssl.keyStoreType: PKCS12
		// server.ssl.keyAlias: DUMMY
	}

	@Bean
	public EmbeddedServletContainerCustomizer tomcatCustomizer() {
		return container -> {
			if (container instanceof TomcatEmbeddedServletContainerFactory) {
				((TomcatEmbeddedServletContainerFactory) container).addConnectorCustomizers((connector) -> {
					connector.addUpgradeProtocol(new Http2Protocol());
					connector.setSecure(true);
					//connector.addSslHostConfig(createOpenSSLConfig());
				});
			}
		};
	}

}
