package bean.inject;

import org.springframework.stereotype.Service;

@Service
public class HelloWorldInjectService {

    public void print() {
        System.out.println("======= HelloWorldInjectService =======");
    }
}
