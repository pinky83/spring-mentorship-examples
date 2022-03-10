package org.pinky83;

import org.pinky83.custom.component.PrintService;
import org.pinky83.custom.scanner.CustomBeanFactory;
import org.pinky83.custom.scanner.CustomBeanScanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringHelloRunner {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("bean-config.xml");
        SpringHello springHello = (SpringHello) applicationContext.getBean("helloBean");

        System.out.printf("Hello message: %s", springHello.getHello());

        CustomBeanScanner beanScanner = new CustomBeanScanner();
        beanScanner.scanComponents();
        PrintService printService = (PrintService) CustomBeanFactory.getBean("org.pinky83.custom.component.PrintService");
    }
}
