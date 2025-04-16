package chuwa.backend.springdidemo.service;

import chuwa.backend.springdidemo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * ClassName: EmailService
 * Package: chuwa.backend.springdidemo.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/15 2:28
 * @version 1.0
 */
@Slf4j
@Component("emailService")
@Primary
public class EmailService implements MessageService{
    @Override
    public void sendMessage(Message message) {
        log.info("Sending EMAIL to {}: {}", message.getRecipient(), message.getContent());
    }

    @Override
    public String getServiceName() {
        return "Email Service";
    }
}
