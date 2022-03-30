package org.pinky83;

import lombok.Getter;
import org.pinky83.bean.ExampleBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = {"org.pinky83.bean", "org.pinky83.aop", "org.pinky83.configuration"})
@Component
public class SpringHelloRunner implements InitializingBean, ApplicationContextAware {

    static {
        new AnnotationConfigApplicationContext(SpringHelloRunner.class);
    }

    private static SpringHelloRunner instance;

    @Getter
    private ApplicationContext context;

    @Getter
    private final ExampleBean exampleBean;


    public SpringHelloRunner(ApplicationContext context, ExampleBean exampleBean) {
        this.context = context;
        this.exampleBean = exampleBean;
    }

    @Override
    public void afterPropertiesSet() {
        instance = this;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static void main(String[] args) {
        instance.getExampleBean().hello();
    }
}
