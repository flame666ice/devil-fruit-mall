package mall.fruit.devil;

import mall.fruit.devil.entity.UmsAdmin;
import mall.fruit.devil.service.IUmsAdminService;
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

    @Autowired
    IUmsAdminService adminService;

    @Test
    void changePassword(){
        String passwdTest = passwordEncoder.encode("test123");
        UmsAdmin umsAdmin1 = new UmsAdmin();
        umsAdmin1.setPassword("test123");
        adminService.update((long) 1,umsAdmin1);
        LOGGER.warn("passwdTest",passwdTest);
        String passwdAmind = passwordEncoder.encode("admin159265");
        UmsAdmin umsAdmin2 = new UmsAdmin();
        umsAdmin2.setPassword("admin159265");
        adminService.update((long) 3,umsAdmin2);
        LOGGER.warn("passwdAmind",passwdAmind);
        String passwdMacro = passwordEncoder.encode("macro123");
        UmsAdmin umsAdmin3 = new UmsAdmin();
        umsAdmin3.setPassword("macro123");
        adminService.update((long) 4,umsAdmin3);
        LOGGER.warn("passwdMacro",passwdMacro);
        String passwdProduct = passwordEncoder.encode("product159265");
        UmsAdmin umsAdmin4 = new UmsAdmin();
        umsAdmin4.setPassword("product159265");
        adminService.update((long) 6,umsAdmin4);
        LOGGER.warn("passwdProduct",passwdProduct);
        String passwdOrder = passwordEncoder.encode("order159265");
        UmsAdmin umsAdmin5 = new UmsAdmin();
        umsAdmin5.setPassword("order159265");
        adminService.update((long) 7,umsAdmin5);
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
