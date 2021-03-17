package nl.tudelft.oopp.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.image.DataBuffer;

@SpringBootApplication
public class DemoApplication {
    DatabaseLoader databaseLoader;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
