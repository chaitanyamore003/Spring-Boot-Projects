package in.springBoot.profileDemo.controller;


import in.springBoot.profileDemo.service.NotificationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    NotificationService notificationService;

    DemoController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Value("${app.welcome.message}")
    private String message;

    @Value("${app.welcome.code}")
    private int code;

    @GetMapping("/greet")
    public ResponseEntity<String> greet(){
        return ResponseEntity.ok(message + " : " + code);
    }

    @GetMapping("/notification")
    public ResponseEntity<String> notification(){
        return ResponseEntity.ok(notificationService.send());
    }
}
