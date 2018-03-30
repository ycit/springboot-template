package com.vastio.basic;

/**
 * 自定义运行时异常
 *
 * @author xlch
 * @Date 2018-03-09 18:14
 */
public class VastioException extends RuntimeException {

    public VastioException() {
        super();
    }

    public VastioException(String message) {
        super(message);
    }

    public VastioException(Throwable cause) {
        super(cause);
    }

    public VastioException(String message, Throwable cause) {
        super(message, cause);
    }
}
