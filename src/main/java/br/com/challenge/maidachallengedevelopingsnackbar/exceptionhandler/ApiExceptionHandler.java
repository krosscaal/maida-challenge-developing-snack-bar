/*
 * Author: Krossby Adhemar Camacho Alviz
 *
 */

package br.com.challenge.maidachallengedevelopingsnackbar.exceptionhandler;

import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.COSTUMER_NOT_FOUND;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.MANAGER_NOT_FOUND;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.ORDER_NOT_FOUND;
import static br.com.challenge.maidachallengedevelopingsnackbar.mensagens.MensageEstatica.PRODUCT_NOT_FOUND;

import br.com.challenge.maidachallengedevelopingsnackbar.exception.BusinessException;
import br.com.challenge.maidachallengedevelopingsnackbar.exceptionhandler.ApiErrors.Campo;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
  DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

  @Autowired
  private MessageSource messageSource;
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {

    List<Campo> campos = new ArrayList<>();

    for(ObjectError error: ex.getBindingResult().getAllErrors()) {
      String nome = ((FieldError) error).getField();
      String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
      campos.add(new Campo(nome, mensagem));
    }
    ApiErrors apiErrors =
        new ApiErrors(
            status.value(),
            LocalDateTime.now().format(dataFormatada),
            "Um o mais campos estão inválidos, favor verifique e tente novamente!",
            campos);

    return handleExceptionInternal(ex, apiErrors, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(final MissingPathVariableException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    ApiErrors apiErrors =
        new ApiErrors(
            status.value(),
            LocalDateTime.now().format(dataFormatada),
            "obrigatorio na URI conter variavel id");

    return handleExceptionInternal(ex, apiErrors, headers, status, request);
  }
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ApiErrors apiErrors =
        new ApiErrors(
            status.value(),
            LocalDateTime.now().format(dataFormatada),
            "Formato de data errada, deve estar em formato yyyy-MM-ddTHH:mm:ss.SSSZ");

    return handleExceptionInternal(ex, apiErrors, headers, status, request);

  }

  @ExceptionHandler(BusinessException.class)
  public ResponseEntity<Object> handleBusisnessException(BusinessException ex, WebRequest request) {
    HttpStatus status = HttpStatus.BAD_REQUEST;
    if(ex.getMessage().equals(PRODUCT_NOT_FOUND)
        || ex.getMessage().equals(MANAGER_NOT_FOUND)
        || ex.getMessage().equals(COSTUMER_NOT_FOUND)
        || ex.getMessage().equals(ORDER_NOT_FOUND)) {
      status = HttpStatus.NOT_FOUND;
    }

    ApiErrors apiErrors =
        new ApiErrors(
            status.value(),
            LocalDateTime.now().format(dataFormatada),
            ex.getMessage());

    return handleExceptionInternal(ex,apiErrors,new HttpHeaders(),status,request);
  }



}
