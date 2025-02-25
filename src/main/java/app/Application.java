package app;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Theme
@PWA(name = "My Progressive Web Application",
        shortName = "MyPWA")
public class Application implements AppShellConfigurator {


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
