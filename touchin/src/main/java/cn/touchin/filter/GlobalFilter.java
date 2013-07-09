/*
 * cn.touchin.filter.GlobalFilter.java
 * Mar 3, 2012 
 */
package cn.touchin.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.log.Logs;

/**
 * Mar 3, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class GlobalFilter implements Filter {

    private final Properties variables = new Properties();

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        if (!variables.isEmpty()) {
            for (Entry<Object, Object> e : variables.entrySet()) {
                req.setAttribute(e.getKey().toString(), e.getValue());
            }
        }
        chain.doFilter(req, resp);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig config) throws ServletException {
        Enumeration<String> enums = config.getInitParameterNames();
        while (enums.hasMoreElements()) {
            String file = config.getInitParameter(enums.nextElement());
            if (!Strings.isBlank(file)) {
                try {
                    variables.load(Files.findFileAsStream(file));
                } catch (IOException e) {
                    Logs.getLog(GlobalFilter.class).error("init GlobalFilter error", e);
                }
            }
        }
    }

}
