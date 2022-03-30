package org.pinky83.bean;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ExampleBean {

    private final InjectedBean injectedBean;

    public ExampleBean(InjectedBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    public void hello() {
        System.out.format("Hello, autowired bean!%n %s", injectedBean.getMessage());
    }
}
