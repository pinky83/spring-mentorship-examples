package org.pinky83.bean;

import org.pinky83.aop.HelloAspect;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.stereotype.Component;

@Component
public class ExampleBean {

    private final InjectedBean injectedBean;

    public ExampleBean(InjectedBean injectedBean, HelloAspect helloAspect) {

        ProxyFactory proxyFactory  = new ProxyFactory();
        proxyFactory.addAdvice(helloAspect);
        proxyFactory.setTarget(injectedBean);

        this.injectedBean = (InjectedBean) proxyFactory.getProxy();
    }

    public void hello() {
        System.out.format("Hello, autowired bean!%n %s", injectedBean.getMessage());
    }
}
