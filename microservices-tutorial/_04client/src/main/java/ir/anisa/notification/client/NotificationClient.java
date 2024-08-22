package ir.anisa.notification.client;

import ir.anisa.notification.dto.NotificationDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "NOTIFICATION", fallback = NotificationFallBack.class)
public interface NotificationClient {

    @PostMapping("/api/v1/notification/")
    void saveNotification(@RequestBody NotificationDTO notificationDTO);
}