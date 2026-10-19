package com.clinica.recordatorios_citas_service.channel;

import com.clinica.recordatorios_citas_service.domain.CanalNotificacion;
import org.springframework.stereotype.Component;

@Component
public class ReminderChannelFactoryImpl implements ReminderChannelFactory {

    private final EmailReminderChannel emailReminderChannel;
    private final SmsReminderChannel smsReminderChannel;

    public ReminderChannelFactoryImpl(EmailReminderChannel emailReminderChannel,
                                      SmsReminderChannel smsReminderChannel) {
        this.emailReminderChannel = emailReminderChannel;
        this.smsReminderChannel = smsReminderChannel;
    }

    @Override
    public ReminderChannel obtenerCanal(CanalNotificacion tipo) {
        return switch (tipo) {
            case EMAIL -> emailReminderChannel;
            case SMS -> smsReminderChannel;
        };
    }
}