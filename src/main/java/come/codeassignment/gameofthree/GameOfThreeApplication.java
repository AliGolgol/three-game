package come.codeassignment.gameofthree;

import come.codeassignment.gameofthree.config.PropertiesConfig;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GameOfThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(GameOfThreeApplication.class, args);
    }

    static {
        initializeGlobalConfiguration();
    }
    private static void initializeGlobalConfiguration() {
        PropertyConfigurator.configure(PropertyConfigurator.class.getClassLoader().getResourceAsStream("log4j.properties"));
        PropertiesConfig.initialize("application.properties");
    }
}
