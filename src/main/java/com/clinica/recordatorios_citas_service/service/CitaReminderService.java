package com.clinica.recordatorios_citas_service.service;

public interface CitaReminderService {
    int enviarRecordatoriosPendientes();
    void enviarRecordatorioUrgente(Long citaId);
}

