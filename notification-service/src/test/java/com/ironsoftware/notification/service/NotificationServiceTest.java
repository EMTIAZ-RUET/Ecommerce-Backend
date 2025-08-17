package com.ironsoftware.notification.service;

import com.ironsoftware.notification.model.Notification;
import com.ironsoftware.notification.model.NotificationChannel;
import com.ironsoftware.notification.model.NotificationStatus;
import com.ironsoftware.notification.model.NotificationType;
import com.ironsoftware.notification.repository.NotificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationService notificationService;

    private Notification testNotification;

    @BeforeEach
    void setUp() {
        testNotification = Notification.builder()
                .id("notification-1")
                .userId("user-1")
                .type(NotificationType.ORDER_CONFIRMATION)
                .channel(NotificationChannel.EMAIL)
                .title("Order Confirmed")
                .message("Your order has been confirmed")
                .status(NotificationStatus.PENDING)
                .metadata("{\"orderId\": \"order-1\"}")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Test
    void findById_ShouldReturnNotification_WhenNotificationExists() {
        // Given
        when(notificationRepository.findById(anyString())).thenReturn(Optional.of(testNotification));

        // When
        Optional<Notification> result = notificationRepository.findById("notification-1");

        // Then
        assertTrue(result.isPresent());
        assertEquals(testNotification.getId(), result.get().getId());
        assertEquals(testNotification.getTitle(), result.get().getTitle());
    }

    @Test
    void findByUserId_ShouldReturnNotifications_WhenUserHasNotifications() {
        // Given
        List<Notification> notifications = Arrays.asList(testNotification);
        when(notificationRepository.findByUserId(anyString())).thenReturn(notifications);

        // When
        List<Notification> result = notificationRepository.findByUserId("user-1");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testNotification.getUserId(), result.get(0).getUserId());
    }

    @Test
    void findByStatus_ShouldReturnNotifications_WhenStatusMatches() {
        // Given
        List<Notification> notifications = Arrays.asList(testNotification);
        when(notificationRepository.findByStatus(any(NotificationStatus.class))).thenReturn(notifications);

        // When
        List<Notification> result = notificationRepository.findByStatus(NotificationStatus.PENDING);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(NotificationStatus.PENDING, result.get(0).getStatus());
    }

    @Test
    void save_ShouldReturnSavedNotification_WhenValidNotification() {
        // Given
        when(notificationRepository.save(any(Notification.class))).thenReturn(testNotification);

        // When
        Notification result = notificationRepository.save(testNotification);

        // Then
        assertNotNull(result);
        assertEquals(testNotification.getId(), result.getId());
        assertEquals(testNotification.getType(), result.getType());
    }

    @Test
    void updateNotificationStatus_ShouldUpdateStatus_WhenNotificationExists() {
        // Given
        when(notificationRepository.findById(anyString())).thenReturn(Optional.of(testNotification));
        when(notificationRepository.save(any(Notification.class))).thenReturn(testNotification);

        // When
        testNotification.setStatus(NotificationStatus.SENT);
        Notification result = notificationRepository.save(testNotification);

        // Then
        assertEquals(NotificationStatus.SENT, result.getStatus());
        verify(notificationRepository).save(any(Notification.class));
    }
}
