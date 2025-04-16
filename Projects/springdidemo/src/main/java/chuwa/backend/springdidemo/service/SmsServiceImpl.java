package chuwa.backend.springdidemo.service;

import chuwa.backend.springdidemo.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ClassName: SmsServiceImpl
 * Package: chuwa.backend.springdidemo.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/15 2:31
 * @version 1.0
 */
@Slf4j
@Component("smsService")
public class SmsServiceImpl implements MessageService{
    @Override
    public void sendMessage(Message message) {
        log.info("Sending SMS to {}: {}", message.getRecipient(), message.getContent());
    }

    @Override
    public String getServiceName() {
        return "SMS Service";
    }
}
