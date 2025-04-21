package chuwa.backend.httpsdemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName: ApiController
 * Package: chuwa.backend.httpsdemo.controller
 * Description:
 *
 * @author Fan Peng
 * Create 2025/4/21 18:39
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ApiController {
    @GetMapping("/hello")
    public ResponseEntity<Void> hello() {
        log.info("Received request to /api/v1/hello endpoint");
        return ResponseEntity.ok().build(); // Empty response
    }

}
