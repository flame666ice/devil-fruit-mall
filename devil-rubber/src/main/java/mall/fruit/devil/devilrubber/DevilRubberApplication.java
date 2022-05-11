package mall.fruit.devil.devilrubber;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DevilRubberApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevilRubberApplication.class, args);
    }

}
