package chuwa.backend.springdidemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ClassName: Message
 * Package: chuwa.backend.springdidemo.model
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/15 2:10
 * @version 1.0
 */
@Data
@AllArgsConstructor
public class Message {
    private String content;
    private String recipient;
}
