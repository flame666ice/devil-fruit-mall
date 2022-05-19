package mall.fruit.devil.devilmbg;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class MPGeneratorTest {

    @Test
    public void run(){
        FastAutoGenerator.create("jdbc:mysql://43.138.158.204:3306/devil","luffy","luffy159265")
                .globalConfig(builder->{
                    builder.author("flame")
                            .enableSwagger()
                            .fileOverride()
                            .outputDir("D:\\temp\\mall\\test\\devil-fruit-mall\\devil-mbg\\src\\main\\java");
                })
                .packageConfig(builder -> {
                    builder.parent("mall.fruit.devil.devilmbg")
                            .pathInfo(Collections.singletonMap(OutputFile.xml,"D:\\temp\\mall\\test\\devil-fruit-mall\\devil-mbg\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.controllerBuilder().enableRestStyle();
                })
                .execute();

    }
}
