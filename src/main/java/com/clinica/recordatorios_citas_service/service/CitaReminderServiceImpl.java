package com.clinica.recordatorios_citas_service.service;

import org.springframework.beans.factory.annotation.Qualifier;

import com.clinica.recordatorios_citas_service.channel.ReminderChannel;
import com.clinica.recordatorios_citas_service.channel.ReminderChannelFactory;
import com.clinica.recordatorios_citas_service.domain.Cita;
import com.clinica.recordatorios_citas_service.repository.CitaRepository;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CitaReminderServiceImpl implements CitaReminderService {

    private final CitaRepository citaRepository;
    private final ReminderChannelFactory reminderChannelFactory;
    private final ReminderChannel canalUrgente;
    private final Clock clock;

    public CitaReminderServiceImpl(CitaRepository citaRepository,
                                   ReminderChannelFactory reminderChannelFactory,
                                   @Qualifier("canalUrgente") ReminderChannel canalUrgente,
                                   Clock clock) {
        this.citaRepository = citaRepository;
        this.reminderChannelFactory = reminderChannelFactory;
        this.canalUrgente = canalUrgente;
        this.clock = clock;
    }

    @Override
    public int enviarRecordatoriosPendientes() {
        LocalDateTime ahora = LocalDateTime.now(clock);
        LocalDateTime en24Horas = ahora.plusHours(24);

        List<Cita> citasPorNotificar = citaRepository
                .findByFechaHoraCitaBetweenAndRecordatorioEnviadoFalse(ahora, en24Horas);

        for (Cita cita : citasPorNotificar) {
            ReminderChannel canal = reminderChannelFactory.obtenerCanal(cita.getCanalPreferido());
            canal.enviar(cita, construirMensaje(cita));
            cita.setRecordatorioEnviado(true);
            citaRepository.save(cita);
        }
        return citasPorNotificar.size();
    }

    @Override
    public void enviarRecordatorioUrgente(Long citaId) {
        Cita cita = citaRepository.findById(citaId)
                .orElseThrow(() -> new CitaNoEncontradaException(citaId));

        canalUrgente.enviar(cita, "URGENTE: su cita es en menos de 2 horas y no ha sido confirmada");
        cita.setRecordatorioEnviado(true);
        citaRepository.save(cita);
    }

    private String construirMensaje(Cita cita) {
        return "Recordatorio: tiene una cita el " + cita.getFechaHoraCita();
    }
}