package chuwa.backend.springdidemo.service;

import chuwa.backend.springdidemo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ClassName: PushNotificationService
 * Package: chuwa.backend.springdidemo.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/15 2:33
 * @version 1.0
 */
@Slf4j
@Component("pushService")
public class PushNotificationService implements MessageService {
    @Override
    public void sendMessage(Message message) {
        log.info("Sending PUSH NOTIFICATION to {}: {}", message.getRecipient(), message.getContent());
    }

    @Override
    public String getServiceName() {
        return "Push Notification Service";
    }
}
