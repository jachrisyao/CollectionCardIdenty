package collection.card.identy.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@ServletComponentScan
public class WebInitializer extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WebInitializer.class, args);
    }

    /**
     * Configure your application when it’s launched by the servlet container
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebInitializer.class);
    }
}