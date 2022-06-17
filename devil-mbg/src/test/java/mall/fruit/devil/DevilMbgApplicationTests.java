package mall.fruit.devil;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class DevilMbgApplicationTests {
    private static final Logger LOGGER = LoggerFactory.getLogger(DevilMbgApplicationTests.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void changePassword(){
        String passwdTest = passwordEncoder.encode("test123");
        LOGGER.warn("passwdTest",passwdTest);
        String passwdAmind = passwordEncoder.encode("admin159265");
        LOGGER.warn("passwdAmind",passwdAmind);
        String passwdMacro = passwordEncoder.encode("macro123");
        LOGGER.warn("passwdMacro",passwdMacro);
        String passwdProduct = passwordEncoder.encode("product159265");
        LOGGER.warn("passwdProduct",passwdProduct);
        String passwdOrder = passwordEncoder.encode("order159265");
        LOGGER.warn("passwdOrder",passwdOrder);
        LOGGER.warn("over");


    }



    @Test
    void contextLoads() {
    }

    @Test
    void testPassWord(){

    }

}
