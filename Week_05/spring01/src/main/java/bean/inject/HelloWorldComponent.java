package bean.inject;

import org.springframework.stereotype.Component;

@Component
public class HelloWorldComponent {

    public void print() {
        System.out.println("======= HelloWorldComponent =======");
    }
}
