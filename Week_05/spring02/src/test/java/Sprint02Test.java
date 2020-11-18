import io.kimming.Klass;
import io.kimming.School;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Sprint02Test {
    
    @Resource(name="class1")
    private Klass klass;

    @Autowired
    private School school;

    @Test
    public void klassTest(){
        klass.dong();
    }

    @Test
    public void schoolTest(){
        school.ding();
    }
}
