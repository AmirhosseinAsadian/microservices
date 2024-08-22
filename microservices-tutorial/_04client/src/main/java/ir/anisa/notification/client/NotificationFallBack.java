package ir.anisa.notification.client;

import ir.anisa.notification.dto.NotificationDTO;
import org.springframework.stereotype.Component;

@Component
public class NotificationFallBack implements NotificationClient {
    @Override
    public void saveNotification(NotificationDTO notificationDTO) {
        System.out.println("saveNotification failed!");
    }
}
