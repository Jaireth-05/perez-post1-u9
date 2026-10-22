package com.clinica.recordatorios_citas_service.service;

public class CitaNoEncontradaException extends RuntimeException {
    public CitaNoEncontradaException(Long citaId) {
        super("Cita no encontrada: " + citaId);
    }
}