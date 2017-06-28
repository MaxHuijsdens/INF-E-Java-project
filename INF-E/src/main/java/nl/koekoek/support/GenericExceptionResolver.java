/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.support;

import java.lang.annotation.Annotation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

public class GenericExceptionResolver implements HandlerExceptionResolver, Ordered {

    private static final Logger LOG = LoggerFactory.getLogger(GenericExceptionResolver.class);

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LOG.error("Handling request, for [" + handler + "], resulted in the following exception.", ex);

        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());

        if (hasResponseBody(handler)) {
            return new ModelAndView(new MappingJackson2JsonView(), "error", ex.getMessage());
        } else {
            return new ModelAndView("error", "error", ex.getMessage());
        }
    }

    private boolean hasResponseBody(Object handler) {
        return getAnnotation(handler, ResponseBody.class) != null;
    }

    private static <T extends Annotation> T getAnnotation(Object handler, Class<T> annotationClass) {
        T annotation = null;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethod().getAnnotation(annotationClass);
        }
        return annotation;
    }

}
