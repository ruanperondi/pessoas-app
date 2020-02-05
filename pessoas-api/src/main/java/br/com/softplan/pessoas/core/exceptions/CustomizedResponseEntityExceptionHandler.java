package br.com.softplan.pessoas.core.exceptions;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaExistenteException;
import br.com.softplan.pessoas.feature.pessoa.exceptions.PessoaNaoEncontradaException;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private <T extends Exception> ResponseEntity<ErrorDetails> handle(T t, WebRequest request, HttpStatus status) {
    final ErrorDetails errorDetails = new ErrorDetails(new Date(), t.getMessage(), request.getDescription(false));

    return new ResponseEntity<>(errorDetails, status);
  }

  @ExceptionHandler({IllegalArgumentException.class})
  public final ResponseEntity<ErrorDetails> handleCampoObrigatoriOException(final IllegalArgumentException ex, final WebRequest request) {
    return handle(ex, request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(IllegalAccessException.class)
  public final ResponseEntity<ErrorDetails> handleIllegalAccessException(final IllegalAccessException ex, final WebRequest request) {
    return handle(ex, request, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
    List<String> messages = ex.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

    final ErrorDetails errorDetails = new ErrorDetails(new Date(), messages, request.getDescription(false));

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(NegocioException.class)
  public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
    return handle(ex, request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<?> handleValidationException(ValidationException ex, WebRequest request) {
    return handle(ex, request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PessoaExistenteException.class)
  public ResponseEntity<?> handlePessoaExistenteException(PessoaExistenteException ex, WebRequest request) {
    return handle(ex, request, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(PessoaNaoEncontradaException.class)
  public ResponseEntity<?> handlePessoaNaoEncontradaException(PessoaNaoEncontradaException ex, WebRequest request) {
    return handle(ex, request, HttpStatus.BAD_REQUEST);
  }

  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));

    return handleExceptionInternal(ex, errorDetails, headers, HttpStatus.BAD_REQUEST, request);
  }
}
