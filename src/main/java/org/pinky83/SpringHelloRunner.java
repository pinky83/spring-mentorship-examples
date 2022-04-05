package org.pinky83;

import lombok.Getter;
import org.pinky83.configuration.AppConfiguration;
import org.pinky83.pojo.User;
import org.pinky83.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.util.Date;

@ComponentScan(basePackages = {"org.pinky83.*"})
@Component
@EnableJpaRepositories()
public class SpringHelloRunner implements InitializingBean, ApplicationContextAware {

    static {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringHelloRunner.class);
        applicationContext.register(AppConfiguration.class);
    }

    private static SpringHelloRunner instance;

    @Getter
    private ApplicationContext context;

    @Getter
    private final UserService userService;

    public SpringHelloRunner(ApplicationContext context, UserService userService) {
        this.context = context;
        this.userService = userService;
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

        User user = new User();
        user.setEmail("testUser1@gmain.com");
        user.setName("Test");
        user.setEnabled(true);
        user.setRegistered(new Date());
        user.setPassword("secret");

        instance.getUserService().create(user, AuthorizedUser.id());
    }
}
