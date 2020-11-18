import bean.inject.HelloWorldComponent;
import bean.inject.HelloWorldInjectService;
import bean.inject.util.SpringUtils;
import bean.xml.HelloWorld;
import io.kimming.Klass;
import io.kimming.School;
import org.checkerframework.checker.units.qual.A;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Sprint02Test {
    
    @Autowired
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
