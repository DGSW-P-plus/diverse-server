package dev.jombi.diverse.infra.security.jwt.filter

import com.fasterxml.jackson.databind.ObjectMapper
import dev.jombi.diverse.common.exception.CustomException
import dev.jombi.diverse.common.exception.ExceptionDetail
import dev.jombi.diverse.common.exception.GlobalExceptionDetail
import dev.jombi.diverse.common.exception.response.ResponseError
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtExceptionFilter(private val mapper: ObjectMapper) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain
    ) {
        try {
            chain.doFilter(request, response)
        } catch (e: CustomException) {
            response.writeJson(e.detail, *e.formats)
        } catch (e: Exception) {
            e.printStackTrace()
            response.writeJson(GlobalExceptionDetail.INTERNAL_SERVER_ERROR)
        }
    }

    private fun HttpServletResponse.writeJson(detail: ExceptionDetail, vararg formats: Any?) {
        status = detail.status.value()
        val body = mapper.writeValueAsBytes(
            ResponseError(
                detail.code,
                detail.status.value(),
                detail.message.format(*formats)
            )
        )
        return outputStream.use {
            it.write(body)
            it.flush()
        }
    }
}
