package com.example.TallerCrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Maneja errores 404 (Recurso o Ruta no encontrada)
    @ExceptionHandler({NoHandlerFoundException.class, NoResourceFoundException.class})
    public ResponseEntity<Map<String, Object>> manejarError404(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now().toString());
        respuesta.put("status", HttpStatus.NOT_FOUND.value());
        respuesta.put("type", "NOT_FOUND");
        respuesta.put("title", "Recurso No Encontrado");
        respuesta.put("message", "La ruta o elemento solicitado no existe en el servidor.");

        return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
    }

    // Maneja errores 500 (Error Interno del Servidor)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> manejarError500(Exception ex) {
        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("timestamp", LocalDateTime.now().toString());
        respuesta.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        respuesta.put("type", "SERVER_ERROR");
        respuesta.put("title", "Error Interno del Servidor");
        respuesta.put("message", ex.getMessage() != null ? ex.getMessage() : "Ocurrió un fallo inesperado en el sistema.");

        return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}