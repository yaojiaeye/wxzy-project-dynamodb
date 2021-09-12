
package com.wxzy.aws.dynamodb.config;

import com.wyze.common.exception.GeneralException;
import com.wyze.common.response.ResultCode;
import com.wyze.common.response.ResultModel;
import com.wyze.common.response.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    private static final Map<Integer, HttpStatus> CUSTOM_CODE_HTTP_STATUS_MAP = new HashMap<>(16);

    {
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.ServerInnerException.getCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.UploadFailed.getCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.CacheAccessError.getCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.DatabaseAccessError_MYSQL.getCode(),
            HttpStatus.INTERNAL_SERVER_ERROR);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.DatabaseAccessError_DynamoDB.getCode(),
            HttpStatus.INTERNAL_SERVER_ERROR);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.DatabaseAccessError_ES.getCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.ParameterError.getCode(), HttpStatus.BAD_REQUEST);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.ParameterNotCorrect.getCode(), HttpStatus.PRECONDITION_FAILED);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.HttpMethodError.getCode(), HttpStatus.METHOD_NOT_ALLOWED);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.HttpContentTypeError.getCode(), HttpStatus.NOT_ACCEPTABLE);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.SignatureError.getCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.AppInvalid.getCode(), HttpStatus.FORBIDDEN);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.RequestTimeExpired.getCode(), HttpStatus.REQUEST_TIMEOUT);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.NoPremissionAccessApi.getCode(), HttpStatus.UNAUTHORIZED);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.AccessTokenError.getCode(), HttpStatus.UNAUTHORIZED);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.RefreshTokenError.getCode(), HttpStatus.UNAUTHORIZED);
        CUSTOM_CODE_HTTP_STATUS_MAP.put(ResultCode.UnauthorizedOperation.getCode(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * handle unknown exception.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultModel handleException(final Exception ex) {
        log.error(ex.toString(), ex);
        if (org.springframework.web.bind.MissingServletRequestParameterException.class.getName()
            .equals(ex.getClass().getName())
            || org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class.getName()
                .equals(ex.getClass().getName())) {
            return ResultUtil.error(ResultCode.ParameterError, ex.getMessage());
        }
        return ResultUtil.error(ResultCode.ServerInnerException);
    }

    /**
     * custome code.
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(GeneralException.class)
    public ResponseEntity handleGeneralException(final GeneralException ex) {
        final Integer code = ex.getCode();
        final HttpStatus httpStatus;
        httpStatus = CUSTOM_CODE_HTTP_STATUS_MAP.getOrDefault(code, HttpStatus.OK);
        return new ResponseEntity<>(ResultUtil.error(code, ex.getMessage()), httpStatus);
    }

    /**
     * handleMethodNotSupportedException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResultUtil.error(ResultCode.HttpMethodError), HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * handleMessageNotReadableException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleMessageNotReadableException(final HttpMessageNotReadableException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResultUtil.error(ResultCode.HttpContentTypeError), HttpStatus.NOT_ACCEPTABLE);
    }

    /**
     * handleNoHandlerException
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity handleNoHandlerException(final NoHandlerFoundException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(ResultUtil.error(ResultCode.WrongURL), HttpStatus.NOT_FOUND);
    }
}
