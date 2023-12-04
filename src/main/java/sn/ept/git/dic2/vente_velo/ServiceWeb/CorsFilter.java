package sn.ept.git.dic2.vente_velo.ServiceWeb;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/api/*") // Adjust the URL pattern as needed
public class CorsFilter implements Filter{
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code, if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Cast ServletResponse to HttpServletResponse
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // CORS filtering logic here
        httpResponse.setHeader("Access-Control-Allow-Origin", "*"); //http://localhost:4200
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        // Continue the filter chain
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup code, if needed
    }
}
