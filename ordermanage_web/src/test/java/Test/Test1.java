package Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-security.xml")
public class Test1 {
    @Autowired
    private BCryptPasswordEncoder encoder;

    @Test
    public void t1(){
        String password = "123456";
        String encode1 = encoder.encode(password);
        String encode2 = encoder.encode(password);
        String encode3 = encoder.encode(password);
        System.out.println(encode1);
        System.out.println(encode2);
        System.out.println(encode3);
    }
}
