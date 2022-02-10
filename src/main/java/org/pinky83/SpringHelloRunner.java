package org.pinky83;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelloRunner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-config.xml");
        SpringHello springHello = (SpringHello) applicationContext.getBean("helloBean");

        System.out.printf("Hello message: %s", springHello.getHello());
    }
}
