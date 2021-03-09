package com.knowledge.exception;

/**
 * 错误编码
 */
public interface ErrorCode {
    int INTERNAL_SERVER_ERROR = 500;
    int NOT_NULL = 10001;
    int REQUEST_TIMEOUT = 10048;
    int DB_RECORD_EXISTS = 10049;
}
