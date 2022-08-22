package jwtappdemo.rest.common;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Created by CommonName123 on 26.01.2021
 */
@Component
public class ResponseEntityBuilder {

    public <T> ResponseEntity<T> buildOk(T body) {
        return new ResponseEntity<T>(body, HttpStatus.OK);
    }

    public ResponseEntity<ErrorDetail> buildError(String detail) {
        return buildError(detail, detail);
    }

    public ResponseEntity<ErrorDetail> buildError(String detail, String developerMessage, HttpStatus status) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(status.value());
        errorDetail.setTitle("Ошибка!");
        errorDetail.setDetail(detail);
        errorDetail.setDeveloperMessage(developerMessage);

        return new ResponseEntity<ErrorDetail>(errorDetail, status);
    }

    public ResponseEntity<ErrorDetail> buildError(String detail, String developerMessage) {
        return buildError(detail, developerMessage, INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> buildAuthError(String detail, String developerMessage) {
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimeStamp(new Date().getTime());
        errorDetail.setStatus(HttpStatus.UNAUTHORIZED.value());
        errorDetail.setTitle("Ошибка аутентификации/авторизации!");
        errorDetail.setDetail(detail);
        errorDetail.setDeveloperMessage(developerMessage);

        return new ResponseEntity<>(errorDetail, HttpStatus.UNAUTHORIZED);
    }

    public ResponseEntity<?> buildOk() {
        return new ResponseEntity(HttpStatus.OK);
    }
}
