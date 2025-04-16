package chuwa.backend.springdidemo.service;

import chuwa.backend.springdidemo.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * ClassName: UserService
 * Package: chuwa.backend.springdidemo.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/15 2:27
 * @version 1.0
 */
@Component
@Scope("prototype")
@RequiredArgsConstructor
public class UserService {
    // Field injection
    @Autowired
    private MessageService defaultMessageService; // Will use the @Primary bean (EmailService)

    // Qualifier injection - resolving by bean name
    @Autowired
    @Qualifier("smsService")
    private MessageService smsMessageService;

    // Injection by name - the variable name matches the bean name
    // Note: Due to @Primary on EmailService, it will actually inject EmailService instead of PushService
    // This demonstrates that @Primary has higher priority than name matching
    @Autowired
    private MessageService pushService;

    // Constructor injection (recommended approach) - using Lombok's @RequiredArgsConstructor
    private final @Qualifier("emailService") MessageService constructorInjectedService;

    // Setter injection
    private MessageService setterInjectedService;

    @Autowired
    public void setSetterInjectedService(MessageService setterInjectedService) {
        this.setterInjectedService = setterInjectedService;
    }

    public void sendMessageUsingDefaultService(Message message) {
        System.out.println("Using default service: " + defaultMessageService.getServiceName());
        defaultMessageService.sendMessage(message);
    }

    public void sendMessageUsingSmsService(Message message) {
        System.out.println("Using SMS service: " + smsMessageService.getServiceName());
        smsMessageService.sendMessage(message);
    }

    public void sendMessageUsingPushService(Message message) {
        System.out.println("Using Push service: " + pushService.getServiceName());
        pushService.sendMessage(message);
    }

    public void sendMessageUsingConstructorInjectedService(Message message) {
        System.out.println("Using constructor-injected service: " + constructorInjectedService.getServiceName());
        constructorInjectedService.sendMessage(message);
    }

    public void sendMessageUsingSetterInjectedService(Message message) {
        System.out.println("Using setter-injected service: " + setterInjectedService.getServiceName());
        setterInjectedService.sendMessage(message);
    }
}
