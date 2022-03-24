package org.pinky83;

import org.pinky83.bean.ExampleBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@ComponentScan(basePackages = {"org.pinky83.bean", "org.pinky83.aop"})
@Configuration
@EnableAspectJAutoProxy
public class SpringHelloRunner {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringHelloRunner.class);
        ExampleBean exampleBean = (ExampleBean) applicationContext.getBean("exampleBean");

        exampleBean.hello();
    }
}
