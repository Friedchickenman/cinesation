package com.cinesation.cinesation.exception;

import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Hidden
// 💡 @RestControllerAdvice: "서버 전체에서 발생하는 에러는 다 내가 잡아서 처리할게!" (전담 콜센터)
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. 없는 방을 찾을 때 (IllegalArgumentException) -> 404 Not Found로 변환
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // 2. 닫힌 방에 댓글을 달려고 할 때 (IllegalStateException) -> 400 Bad Request로 변환
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException e) {
        ErrorResponse response = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}