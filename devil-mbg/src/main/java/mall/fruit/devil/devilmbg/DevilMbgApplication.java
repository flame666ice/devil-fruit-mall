package mall.fruit.devil.devilmbg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"mall.fruit.devil.devilmbg.mapper","mall.fruit.devil.devilmbg.mappercustom"})
public class DevilMbgApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevilMbgApplication.class, args);
    }

}
