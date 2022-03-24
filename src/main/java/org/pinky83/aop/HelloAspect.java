package org.pinky83.aop;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.pinky83.bean.InjectedBean;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HelloAspect {

    private final InjectedBean injectedBean;

    public HelloAspect(InjectedBean injectedBean) {
        this.injectedBean = injectedBean;
    }

    @Pointcut("execution(* org.pinky83.bean.ExampleBean.*(..))")
    public void anyName() {
    }

    @Before("anyName()")
    public void before() {
        injectedBean.setMessage("Injected bean's message changed!");
    }
}
