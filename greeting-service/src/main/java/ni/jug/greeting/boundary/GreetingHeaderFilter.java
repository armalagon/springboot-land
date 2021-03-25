package ni.jug.greeting.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class GreetingHeaderFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(GreetingHeaderFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOG.info("Before adding the Greeting header to the response");

        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.addHeader("X-Greeting", "Greeting Service");

        chain.doFilter(request, response);

        LOG.info("Greeting header added");
    }
}
