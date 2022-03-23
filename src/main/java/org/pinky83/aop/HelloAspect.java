package org.pinky83.aop;


import org.pinky83.bean.InjectedBean;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class HelloAspect implements MethodBeforeAdvice {

    private final InjectedBean injectedBean;

    public HelloAspect(InjectedBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    @Override
    public void before(Method method, Object[] args, Object target) {
        injectedBean.setMessage("Injected bean's message changed!");
    }
}
