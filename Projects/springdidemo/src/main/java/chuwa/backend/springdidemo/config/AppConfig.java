package chuwa.backend.springdidemo.config;

import chuwa.backend.springdidemo.model.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * ClassName: AppConfig
 * Package: chuwa.backend.springdidemo.config
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/15 2:35
 * @version 1.0
 */
@Configuration
public class AppConfig {

    // Bean definition using @Bean
    @Bean
    public Message welcomeMessage() {
        return new Message("Welcome to Spring DI Demo", "user@example.com");
    }

    // Singleton scope bean (default)
    @Bean
    public Message singletonMessage() {
        return new Message("I am a singleton bean", "singleton@example.com");
    }

    // Prototype scope bean
    @Bean
    @Scope("prototype")
    public Message prototypeMessage() {
        return new Message("I am a prototype bean", "prototype@example.com");
    }

    // Named bean
    @Bean("customMessage")
    public Message namedMessage() {
        return new Message("I am a named bean", "named@example.com");
    }
}
