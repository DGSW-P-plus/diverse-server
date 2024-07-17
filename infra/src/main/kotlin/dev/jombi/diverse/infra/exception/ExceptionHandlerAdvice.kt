package dev.jombi.diverse.infra.exception

import dev.jombi.diverse.common.exception.CustomException
import dev.jombi.diverse.common.exception.GlobalExceptionDetail
import dev.jombi.diverse.common.exception.response.ResponseError
import dev.jombi.diverse.core.auth.exception.AuthExceptionDetails
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.validation.FieldError
import org.springframework.web.HttpMediaTypeNotSupportedException
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException
import org.springframework.web.servlet.resource.NoResourceFoundException


@RestControllerAdvice
class ExceptionHandlerAdvice {
    private val log = LoggerFactory.getLogger(ExceptionHandlerAdvice::class.java)

    @ExceptionHandler(Exception::class)
    fun exception(e: Exception): ResponseEntity<ResponseError> {
        log.error("Error while processing request: ", e)
        return ResponseError.of(GlobalExceptionDetail.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(CustomException::class)
    fun customException(e: CustomException) = ResponseError.of(e.detail, *e.formats)

    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun missingParameter(e: MissingServletRequestParameterException) =
        ResponseError.of(GlobalExceptionDetail.PARAMETER_NOT_MATCH, e.parameterName, e.parameterType)

    @ExceptionHandler(NoResourceFoundException::class)
    fun noResourceFound(e: NoResourceFoundException) =
        ResponseError.of(GlobalExceptionDetail.RESOURCE_NOT_FOUND, e.resourcePath)

    @ExceptionHandler(HttpRequestMethodNotSupportedException::class)
    fun httpRequestMethodNotSupportedException(e: HttpRequestMethodNotSupportedException) =
        ResponseError.of(
            GlobalExceptionDetail.METHOD_NOT_SUPPORTED,
            e.method,
            e.supportedMethods?.joinToString("', '") ?: "N/A"
        )

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessageNotReadableException(e: HttpMessageNotReadableException) =
        ResponseError.of(GlobalExceptionDetail.UNPROCESSABLE_BODY)

    @ExceptionHandler(HttpMediaTypeNotSupportedException::class)
    fun httpMediaTypeNotSupportedException(e: HttpMediaTypeNotSupportedException) =
        ResponseError.of(
            GlobalExceptionDetail.UNSUPPORTED_MEDIA_TYPE,
            e.contentType,
            e.supportedMediaTypes.takeIf { it.isNotEmpty() }?.joinToString("', '") ?: "N/A"
        )

    @ExceptionHandler(NoHandlerFoundException::class)
    fun noHandlerFoundException(e: NoHandlerFoundException) =
        ResponseError.of(
            GlobalExceptionDetail.NO_HANDLER_FOUND,
            e.requestURL
        )

    @ExceptionHandler
    fun badCredentialsException(e: BadCredentialsException) =
        ResponseError.of(AuthExceptionDetails.BAD_CREDENTIALS)

    @ExceptionHandler
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException) =
        ResponseError.of(
            GlobalExceptionDetail.INVALID_PARAMETER,
            e.bindingResult.allErrors.joinToString(", ") { "in field '${(it as FieldError).field}': ${it.defaultMessage}" }
        )
}
