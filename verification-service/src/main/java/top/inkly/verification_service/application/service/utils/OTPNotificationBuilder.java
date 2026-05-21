package top.inkly.verification_service.application.service.utils;

import top.inkly.shared.infrastructure.input.dto.notification.EmailNotificationRequestDTO;
import top.inkly.shared.infrastructure.input.dto.notification.NotificationRequestDTO;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class OTPNotificationBuilder {

    public static NotificationRequestDTO<EmailNotificationRequestDTO> buildOtpNotification(String email, String username, String otp) {
        NotificationRequestDTO<EmailNotificationRequestDTO> notificationRequestDTO = new NotificationRequestDTO<>();
        EmailNotificationRequestDTO emailRequestDTO = new EmailNotificationRequestDTO();

        ZoneId colombiaZone = ZoneId.of("America/Bogota");
        ZonedDateTime colombiaTime = LocalDateTime.now().atZone(colombiaZone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss z");

        emailRequestDTO.setEmailReceiver(email);
        emailRequestDTO.setNotificationTemplateId("OTP");
        emailRequestDTO.setDataValues(
                Map.of("username", username,
                "otpCode", otp,
                "expiresIn", "5 Minutos",
                "fechaSolicitud", colombiaTime.format(formatter)
        ));
        notificationRequestDTO.setNotificationType("EMAIL");
        notificationRequestDTO.setNotificationData(emailRequestDTO);

        return notificationRequestDTO;
    }

}
