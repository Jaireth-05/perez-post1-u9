package com.clinica.recordatorios_citas_service.channel.gateway;


public interface NotificadorSmsGateway {
    void enviarSms(String numeroDestino, String texto);
}