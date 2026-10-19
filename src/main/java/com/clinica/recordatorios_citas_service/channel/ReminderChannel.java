package com.clinica.recordatorios_citas_service.channel;

import com.clinica.recordatorios_citas_service.domain.Cita;

public interface ReminderChannel {
    void enviar(Cita cita, String mensaje);
}