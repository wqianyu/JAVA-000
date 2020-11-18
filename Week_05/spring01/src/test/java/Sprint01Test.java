import bean.inject.HelloWorldComponent;
import bean.inject.HelloWorldInjectService;
import bean.inject.util.SpringUtils;
import bean.xml.HelloWorld;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class Sprint01Test {
    
    @Autowired
    private HelloWorld helloWorld;

    @Autowired
    private HelloWorldInjectService injectService;

    @Autowired
    private HelloWorldComponent helloWorldComponent;

    @Test
    public void helloWorldTest(){
        helloWorld.print();
    }

    @Test
    public void injectServiceTest(){
        injectService.print();
    }

    @Test
    public void componentTest(){
        helloWorldComponent.print();
    }

    @Test
    public void springUtilsTest(){
        HelloWorldInjectService service = SpringUtils.getBean(HelloWorldInjectService.class);
        service.print();
    }
}
