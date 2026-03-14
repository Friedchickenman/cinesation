package com.cinesation.cinesation.exception;

// 에러가 났을 때 프론트에게 돌려줄 깔끔한 포장지입니다.
public record ErrorResponse(
        int status,
        String message
) {
}