package me.training.jpaTraining.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice  // Indica que essa classe vai tratar exceções globalmente para todos os controllers.
public class GlobalExceptionHandler {

    /**
     * Tratador para exceções de validação de campos de entrada (MethodArgumentNotValidException).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Erro de validação. Verifique os campos.");
        response.put("status", HttpStatus.BAD_REQUEST.value());

        Map<String, String> erros = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            erros.put(error.getField(), error.getDefaultMessage())
        );

        response.put("erros", erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     * Tratador para exceções do tipo ResponseStatusException.
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> response = new HashMap<>();
        HttpStatus status = (HttpStatus) ex.getStatusCode();
        response.put("status", status.value());
        response.put("mensagem", ex.getReason());

        return ResponseEntity.status(status).body(response);
    }

    /**
     * Tratador para exceção de rota não encontrada (NoHandlerFoundException).
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNoHandlerFoundException(NoHandlerFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "A rota solicitada não existe.");
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("detalhes", ex.getRequestURL());  // Mostra a URL da requisição

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    /**
     * Tratador para exceções genéricas (qualquer outra exceção não tratada).
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("mensagem", "Erro inesperado.");
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("detalhes", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
