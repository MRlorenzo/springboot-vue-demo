package org.spmul.web;

import org.mybatis.spring.annotation.MapperScan;
import org.spmul.web.shutdown.GracefulShutdownTomcat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@MapperScan("org.spmul.dao")
@SpringBootApplication
@ComponentScan(basePackages = {"org.spmul"})
public class WebApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}


	@Autowired
	private GracefulShutdownTomcat gracefulShutdownTomcat;


	/**
	 * 当接收到kill -2(ctrl+c)指令时，将不会立即退出必须等待业务处理完成之后结束进程。
	 * 注意超过30秒可能会强制退出
	 * @return
	 */
	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addConnectorCustomizers(gracefulShutdownTomcat);
		return tomcat;
	}

}
