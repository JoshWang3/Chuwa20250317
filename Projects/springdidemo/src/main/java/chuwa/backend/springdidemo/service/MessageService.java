package chuwa.backend.springdidemo.service;

import chuwa.backend.springdidemo.model.Message;

/**
 * ClassName: MessageService
 * Package: chuwa.backend.springdidemo.service
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/15 2:14
 * @version 1.0
 */
public interface MessageService {
    void sendMessage(Message message);
    String getServiceName();
}
