package ni.jug.demo.greeting.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LangHeaderCheckerFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(LangHeaderCheckerFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        LOG.info("Inspecting the lang header: {}", httpRequest.getHeader("Accept-Language"));

        String lang = ServletRequestSupport.lang(httpRequest);
        String greetingLangHeader = ServletRequestSupport.customHeader(httpRequest, "lang");
        httpResponse.addHeader(greetingLangHeader, lang);

        chain.doFilter(request, response);

        LOG.info("After inspecting the lang header");
    }
}
