package mall.fruit.devil;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class MPGeneratorTest {

    @Test
    public void run(){
        FastAutoGenerator.create("jdbc:mysql://43.138.158.204:3306/devil?useUnicode=true&useSSL=false&characterEncoding=utf8","luffy","luffy159265")
                .globalConfig(builder->{
                    builder.author("flame")
                            .enableSwagger()
                            .outputDir("D:\\temp\\mall\\test\\devil-fruit-mall\\devil-test\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("mall.fruit.devil")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,"D:\\temp\\mall\\test\\devil-fruit-mall\\devil-test\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.controllerBuilder().enableRestStyle().mapperBuilder().enableBaseResultMap();
                })
                .execute();

    }
}
