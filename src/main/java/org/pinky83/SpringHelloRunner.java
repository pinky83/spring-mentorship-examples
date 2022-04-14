package org.pinky83;

import lombok.Getter;
import org.pinky83.pojo.User;
import org.pinky83.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = {"org.pinky83.*"})
@Component
public class SpringHelloRunner implements InitializingBean, ApplicationContextAware {

    static {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringHelloRunner.class);
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
        user.setPassword("secret");

        User user1 = instance.getUserService().create(user, user.getId());

//        User user2 = instance.getUserService().getById(user1.getId(), user.getId());
//        System.out.println(user2);
//
//        List<User> users = instance.getUserService().getAll(user.getId());
//        System.out.println(users);
//
//        instance.getUserService().delete(user1.getId(), user.getId());
//        System.out.println("Users after deletion: ");
//        users = instance.getUserService().getAll(user.getId());
//        System.out.println(users);
    }
}
