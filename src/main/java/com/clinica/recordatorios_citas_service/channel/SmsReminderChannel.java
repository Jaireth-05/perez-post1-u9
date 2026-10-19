package com.clinica.recordatorios_citas_service.channel;

import com.clinica.recordatorios_citas_service.channel.gateway.NotificadorSmsGateway;
import com.clinica.recordatorios_citas_service.domain.Cita;
import org.springframework.stereotype.Component;

@Component
public class SmsReminderChannel implements ReminderChannel {

    private final NotificadorSmsGateway gateway;

    public SmsReminderChannel(NotificadorSmsGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void enviar(Cita cita, String mensaje) {
        gateway.enviarSms(cita.getPacienteContacto(), mensaje);
    }
}