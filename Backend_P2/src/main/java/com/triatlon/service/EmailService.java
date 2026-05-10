package com.triatlon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * Servicio encargado del envío de correos electrónicos.
 * Usa JavaMailSender de Spring Boot para notificaciones de registro.
 */
@Service
public class EmailService {

    /** Componente de Spring para envío de correos. */
    @Autowired
    private JavaMailSender mailSender;

    /**
     * Envía un correo de bienvenida al atleta recién registrado.
     * @param destinatario correo del atleta
     * @param nombre       nombre del atleta para personalizar el mensaje
     */
    public void enviarCorreoRegistro(String destinatario, String nombre) {
        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            mensaje.setTo(destinatario);
            mensaje.setSubject("¡Bienvenido al Sistema de Triatlón!");
            mensaje.setText(
                "Hola " + nombre + ",\n\n" +
                "Tu registro en el sistema de competencia olímpica de Triatlón " +
                "ha sido exitoso.\n\n" +
                "Gracias por participar.\n\n" +
                "Universidad Distrital Francisco José de Caldas\n" +
                "Sistema de Triatlón"
            );
            mailSender.send(mensaje);
        } catch (Exception e) {
            // Si el correo falla, el registro sigue siendo válido
            System.err.println("Advertencia: No se pudo enviar el correo a " + destinatario + ": " + e.getMessage());
        }
    }
}
