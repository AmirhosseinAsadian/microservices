package ir.anisa.controller;

import ir.anisa.notification.dto.NotificationDTO;
import ir.anisa.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping("/saveNotification")
    public void saveNotification(NotificationDTO notificationDTO) {
        notificationService.saveNotification(notificationDTO);
    }
}
