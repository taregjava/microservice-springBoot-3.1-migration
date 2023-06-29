package com.halfacode.jwt;

import com.halfacode.jwt.dto.RequestWrapperDTO;
import com.halfacode.jwt.dto.StatusResponse;
import com.halfacode.jwt.excption.DuplicateRecordException;
import com.halfacode.jwt.excption.Error;
import com.halfacode.jwt.excption.RecordNotFoundException;
import com.halfacode.jwt.excption.UserUncheckedBusinessException;
import com.halfacode.jwt.utils.UserUtils;
import io.micrometer.common.util.StringUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestControllerAdvice
public class UserErrorExceptionError extends ResponseEntityExceptionHandler {

        private static Logger log = LoggerFactory.getLogger(UserErrorExceptionError.class);

   /* @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // check return type is ApiResponse
        return returnType.getParameterType().isAssignableFrom(StatusResponse.class);
    }

    @Override
    public StatusResponse beforeBodyWrite(StatusResponse body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body != null) {
            // change something if you want
            // set the response status code
            response.setStatusCode(HttpStatus.valueOf(body.getStatus()));
        }
        return body;
    }
        */
        @ExceptionHandler(UserUncheckedBusinessException.class)
        public ResponseEntity<RequestWrapperDTO> handleMasterUncheckedBusinessException(
                UserUncheckedBusinessException ex) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setTimeStamp(LocalDateTime.now());

            HttpStatus code = getHttpStatus(ex.getCode());
            reqWDTO.setStatus(Stream.of(new StatusResponse(UserUtils.SUCCESS_CODE, UserUtils.SUCCESS_CODE_DESC))
                    .collect(Collectors.toList()));

            return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
        }

        @ExceptionHandler(RecordNotFoundException.class)
        public ResponseEntity<RequestWrapperDTO> handleCountryNotFoundException(RecordNotFoundException ex) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setTimeStamp(LocalDateTime.now());
            reqWDTO.setStatus(Stream.of(new StatusResponse(UserUtils.SUCCESS_CODE, UserUtils.SUCCESS_CODE_DESC))
                    .collect(Collectors.toList()));

            return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
        }

        @ExceptionHandler(DuplicateRecordException.class)
        public ResponseEntity<RequestWrapperDTO> handleDuplicateCountryException(DuplicateRecordException ex) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setTimeStamp(LocalDateTime.now());
            reqWDTO.setStatus(Stream.of(new StatusResponse(UserUtils.SUCCESS_CODE, UserUtils.SUCCESS_CODE_DESC))
                    .collect(Collectors.toList()));

            return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
        }

       /* @ExceptionHandler(NoRecordsFoundException.class)
        public ResponseEntity<RequestWrapperDTO> handleNoRecordFoundException(NoRecordsFoundException ex) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setTimeStamp(LocalDateTime.now());
            reqWDTO.setStatus(Stream.of(new StatusResponse(MasterConstants.SUCCESS_CODE, MasterConstants.SUCCESS_CODE_DESC))
                    .collect(Collectors.toList()));

            return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
        }

        @ExceptionHandler(MasterCheckedBusinessException.class)
        public ResponseEntity<RequestWrapperDTO> handleMasterCheckedBusinessException(MasterCheckedBusinessException ex) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors = Stream.of(new StatusResponse(ex.getCode(), ex.getMessage()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setTimeStamp(LocalDateTime.now());
            reqWDTO.setStatus(Stream.of(new StatusResponse(MasterConstants.SUCCESS_CODE, MasterConstants.SUCCESS_CODE_DESC))
                    .collect(Collectors.toList()));

            if (log.isErrorEnabled())
                log.error(ex.getMessage(), ex);

            return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
        }*/

        @ExceptionHandler(SQLException.class)
        public ResponseEntity<RequestWrapperDTO> handleSQLException(SQLException ex) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors = Stream
                    .of(new StatusResponse(Error.ERROR_GENERAL_MSG.getCode(), Error.ERROR_GENERAL_MSG.getDescription()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setStatus(Stream.of(new StatusResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                    HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())).collect(Collectors.toList()));
            reqWDTO.setTimeStamp(LocalDateTime.now());

            if (log.isErrorEnabled())
                log.error(ex.getMessage(), ex.getCause());

            return new ResponseEntity<>(reqWDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /*@ExceptionHandler(ConstraintValidationException.class)
        public ResponseEntity<RequestWrapperDTO> handleConstraintValidationException(ConstraintValidationException ex) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();

            List<StatusResponse> errors = Stream
                    .of(new StatusResponse(ex.getCode(), ex.getMessage()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setStatus(Stream.of(new StatusResponse(MasterConstants.SUCCESS_CODE, MasterConstants.SUCCESS_CODE_DESC))
                    .collect(Collectors.toList()));
            reqWDTO.setTimeStamp(LocalDateTime.now());

            if (log.isErrorEnabled())
                log.error(ex.getMessage(), ex.getCause());

            return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
        }*/
        /*
         * @ExceptionHandler(ConstraintViolationException.class) public
         * ResponseEntity<RequestWrapperDTO>
         * handleConstraintViolationException(ConstraintViolationException ex) {
         * RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
         *
         * List<StatusResponse> errors = Stream .of(new
         * StatusResponse(Error.ERROR_GENERAL_MSG.getCode(),
         * Error.ERROR_GENERAL_MSG.getDescription())) .collect(Collectors.toList());
         * reqWDTO.setErrors(errors); reqWDTO.setStatus(Stream.of(new
         * StatusResponse(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
         * HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())).collect(Collectors.
         * toList())); reqWDTO.setTimeStamp(LocalDateTime.now());
         *
         * if (log.isErrorEnabled()) log.error(ex.getMessage(), ex.getCause());
         *
         * return new ResponseEntity<>(reqWDTO, HttpStatus.INTERNAL_SERVER_ERROR); }
         */

     /*   @Override
        public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors = Stream
                    .of(new StatusResponse(Error.ERROR_GENERAL_MSG.getCode(), Error.ERROR_GENERAL_MSG.getDescription()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setStatus(Stream.of(new StatusResponse(MasterConstants.SUCCESS_CODE, MasterConstants.SUCCESS_CODE_DESC)).collect(Collectors.toList()));
            reqWDTO.setTimeStamp(LocalDateTime.now());

            if (log.isErrorEnabled())
                log.error(ex.getMessage(), ex.getCause());

            return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
        }
*/
      /*  @Override
        public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
                                                                   HttpStatus status, WebRequest request) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors;

            if (CollectionUtils.isNotEmpty(ex.getBindingResult().getFieldErrors())) {
                errors = Stream
                        .of(new StatusResponse(Error.ERROR_INVALID_REQ.getCode(),
                                StringUtils.trim(ex.getBindingResult().getFieldErrors().stream()
                                        .map(e -> e.getDefaultMessage().concat(" ")).collect(Collectors.joining()))))
                        .collect(Collectors.toList());
            } else {
                errors = Stream
                        .of(new StatusResponse(Error.ERROR_GENERAL_MSG.getCode(), Error.ERROR_GENERAL_MSG.getDescription()))
                        .collect(Collectors.toList());
            }
            reqWDTO.setErrors(errors);
            reqWDTO.setStatus(Stream.of(new StatusResponse(UserUtils.SUCCESS_CODE, UserUtils.SUCCESS_CODE_DESC))
                    .collect(Collectors.toList()));
            reqWDTO.setTimeStamp(LocalDateTime.now());

            if (log.isErrorEnabled())
                log.error(ex.getMessage(), ex.getCause());

            return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
        }*/

        /*@Override
        public ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors = Stream
                    .of(new StatusResponse(Error.ERROR_GENERAL_MSG.getCode(), Error.ERROR_GENERAL_MSG.getDescription()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setStatus(Stream.of(new StatusResponse(UserUtils.SUCCESS_CODE, UserUtils.SUCCESS_CODE_DESC))
                    .collect(Collectors.toList()));
            reqWDTO.setTimeStamp(LocalDateTime.now());

            if (log.isErrorEnabled())
                log.error(ex.getMessage(), ex.getCause());

            return new ResponseEntity<>(reqWDTO, HttpStatus.OK);
        }

        @Override
        public ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {
            RequestWrapperDTO reqWDTO = new RequestWrapperDTO();
            List<StatusResponse> errors = Stream
                    .of(new StatusResponse(Error.ERROR_GENERAL_MSG.getCode(), Error.ERROR_GENERAL_MSG.getDescription()))
                    .collect(Collectors.toList());
            reqWDTO.setErrors(errors);
            reqWDTO.setStatus(Stream.of(new StatusResponse(UserUtils.SUCCESS_CODE, UserUtils.SUCCESS_CODE_DESC))
                    .collect(Collectors.toList()));
            reqWDTO.setTimeStamp(LocalDateTime.now());

            if (log.isErrorEnabled())
                log.error(ex.getMessage(), ex.getCause());

            return new ResponseEntity<>(reqWDTO, new HttpHeaders(), HttpStatus.OK);
        }*/

        private HttpStatus getHttpStatus(String code) {
            if (code.equals(UserUtils.CREATE_ERROR_CODE) || code.equals(UserUtils.DELETE_ERROR_CODE)
                    || code.equals(UserUtils.INVALID_REQUEST_CODE)) {
                return HttpStatus.BAD_REQUEST;
            } else {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }
        }
    }


