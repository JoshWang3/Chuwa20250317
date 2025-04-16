package chuwa.backend.springdidemo.runner;

import chuwa.backend.springdidemo.model.Message;
import chuwa.backend.springdidemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * ClassName: DemoRunner
 * Package: chuwa.backend.springdidemo.runner
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/15 2:37
 * @version 1.0
 */
@Slf4j
@Component
public class DemoRunner implements CommandLineRunner {
    private final UserService userService1;
    private final UserService userService2;
    private final Message welcomeMessage;
    private final Message singletonMessage1;
    private final Message singletonMessage2;
    private final Message prototypeMessage1;
    private final Message prototypeMessage2;
    private final Message customMessage;
    private final ApplicationContext context;

    @Autowired
    public DemoRunner(
            UserService userService1,
            UserService userService2,
            @Qualifier("welcomeMessage") Message welcomeMessage,
            @Qualifier("singletonMessage") Message singletonMessage1,
            @Qualifier("singletonMessage") Message singletonMessage2,
            @Qualifier("prototypeMessage") Message prototypeMessage1,
            @Qualifier("prototypeMessage") Message prototypeMessage2,
            @Qualifier("customMessage") Message customMessage,
            ApplicationContext context) {
        this.userService1 = userService1;
        this.userService2 = userService2;
        this.welcomeMessage = welcomeMessage;
        this.singletonMessage1 = singletonMessage1;
        this.singletonMessage2 = singletonMessage2;
        this.prototypeMessage1 = prototypeMessage1;
        this.prototypeMessage2 = prototypeMessage2;
        this.customMessage = customMessage;
        this.context = context;
    }

    @Override
    public void run(String... args) {
        log.info("\n========== SPRING DEPENDENCY INJECTION DEMO ==========\n");

        // Demonstrate different types of dependency injection
        log.info("---------- DEPENDENCY INJECTION TYPES ----------");
        userService1.sendMessageUsingDefaultService(welcomeMessage);
        userService1.sendMessageUsingSmsService(welcomeMessage);
        userService1.sendMessageUsingPushService(welcomeMessage);
        userService1.sendMessageUsingConstructorInjectedService(welcomeMessage);
        userService1.sendMessageUsingSetterInjectedService(welcomeMessage);

        // Demonstrate bean scopes
        log.info("\n---------- BEAN SCOPES ----------");
        // Singleton scope - same instances
        log.info("Singleton Message 1: {}", System.identityHashCode(singletonMessage1));
        log.info("Singleton Message 2: {}", System.identityHashCode(singletonMessage2));
        log.info("Are singleton beans the same instance? {}", (singletonMessage1 == singletonMessage2));

        // Prototype scope - different instances
        log.info("Prototype Message 1: {}", System.identityHashCode(prototypeMessage1));
        log.info("Prototype Message 2: {}", System.identityHashCode(prototypeMessage2));
        log.info("Are prototype beans the same instance? {}", (prototypeMessage1 == prototypeMessage2));

        // UserService is prototype-scoped
        log.info("UserService 1: {}", System.identityHashCode(userService1));
        log.info("UserService 2: {}", System.identityHashCode(userService2));
        log.info("Are UserService beans the same instance? {}", (userService1 == userService2));

        // Demonstrate @Bean and named beans
        log.info("\n---------- BEAN REGISTRATION ----------");
        log.info("Welcome Message: {}", welcomeMessage);
        log.info("Custom Named Message: {}", customMessage);

        log.info("\n=========== DEMO COMPLETED ===========");
    }
}
