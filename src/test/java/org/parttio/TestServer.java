package org.parttio;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@StyleSheet(Lumo.STYLESHEET)
public class TestServer implements AppShellConfigurator {
    
    public static void main(String... args) throws Exception {
        SpringApplication.run(TestServer.class, args);
    }

}
