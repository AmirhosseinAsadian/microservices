package ir.anisa.service;

import ir.anisa.entity.Notification;
import ir.anisa.notification.dto.NotificationDTO;
import ir.anisa.repository.NotificationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void saveNotification(NotificationDTO notificationDTO) {
        Notification notification = new Notification();
        BeanUtils.copyProperties(notificationDTO, notification);
        notificationRepository.save(notification);
    }
}
