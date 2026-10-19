package com.clinica.recordatorios_citas_service.channel;

import com.clinica.recordatorios_citas_service.domain.CanalNotificacion;

public interface ReminderChannelFactory {
    ReminderChannel obtenerCanal(CanalNotificacion tipo);
}