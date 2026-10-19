package com.clinica.recordatorios_citas_service.channel;

import com.clinica.recordatorios_citas_service.domain.Cita;
import org.springframework.stereotype.Component;

@Component
public class EmailReminderChannel implements ReminderChannel {

    @Override
    public void enviar(Cita cita, String mensaje) {
        // En producción: cliente SMTP real. El punto de las pruebas de esta
        // unidad no es verificar el transporte, sino que el servicio
        // seleccionó y usó el canal correcto — ver Paso 5.
        System.out.printf("[EMAIL a %s] %s%n", cita.getPacienteContacto(), mensaje);
    }
}