package collection.card.identy.api;

import org.springframework.boot.SpringApplication;

import java.awt.*;
import java.net.URI;

public class WebStarter {
    public static void main(String[] args) throws Exception {
        System.setProperty("java.awt.headless", "false");
        SpringApplication.run(WebInitializer.class);
        Desktop.getDesktop().browse(new URI("http://127.0.0.1:8080"));
    }
}
