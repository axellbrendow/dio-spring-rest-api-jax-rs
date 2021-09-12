package br.com.axellbrendow.diospringrestapijaxrs.filter;

import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class FilterJava implements Filter {
    private Logger logger = LoggerFactory.getLogger(FilterJava.class);

    @Override
    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        logger.info("Arrived at the filter");

        var req = (HttpServletRequest) request;
        var map = Collections.list(req.getHeaderNames())
            .stream()
            .collect(Collectors.toMap(it -> it.toLowerCase(), req::getHeader));

        var auth = map.get("authorization");

        if (auth != null && auth.equals("strongpass")) {
            chain.doFilter(request, response);
        } else {
            var res = (HttpServletResponse) response;
            res.sendError(401);
        }

        logger.info("Filter ended");
    }
}
