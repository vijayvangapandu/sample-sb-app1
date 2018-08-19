
package app.web.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;

@SpringBootApplication
@EnableMetrics
@ComponentScan(basePackages = { "app.web" })
@EnableAutoConfiguration(exclude = { ErrorMvcAutoConfiguration.class })
public class SampleWebServiceApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SampleWebServiceApplication.class);
	}

	public static void main(String[] args) {
		new SampleWebServiceApplication()
				.configure(new SpringApplicationBuilder(SampleWebServiceApplication.class)).run(args);
	}


}