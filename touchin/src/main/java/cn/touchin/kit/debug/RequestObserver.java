/*
 * cn.touchin.kit.debug.RequestObserver.java
 * Sep 7, 2012 
 */
package cn.touchin.kit.debug;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.opensymphony.xwork2.ognl.OgnlValueStack;

/**
 * Sep 7, 2012
 * 
 * find in http://blog.csdn.net/alexandertech/article/details/6428366
 * 
 * Copyright@(Author: Alex Nie)
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class RequestObserver {
    private HttpServletRequest request;

    public RequestObserver(HttpServletRequest request) {
        this.request = request;
    }

    public RequestObserver observe() {
        String name, pvalue;
        Object avalue;
        Enumeration<String> enumer;

        System.out.println("/***************** Request Observer **************/");

        // observe Request Header
        enumer = request.getHeaderNames();
        System.out.println("Request Header:");
        while (enumer.hasMoreElements()) {
            name = enumer.nextElement();
            pvalue = request.getHeader(name);
            System.out.println("  " + name + " ---- " + pvalue);
        }
        enumer = request.getParameterNames();

        // observe Request Parameters
        System.out.println("Request Parameters:");
        while (enumer.hasMoreElements()) {
            name = enumer.nextElement();
            pvalue = request.getParameter(name);
            System.out.println("  " + name + " ---- " + pvalue);
        }
        enumer = request.getAttributeNames();

        // observe Request Attributes
        System.out.println("Request Attributes:");
        while (enumer.hasMoreElements()) {
            name = enumer.nextElement();
            avalue = request.getAttribute(name);
            System.out.println("  " + name + " ---- " + avalue);

            // observe OgnlValueStack bind by Struts 2
            if (avalue instanceof OgnlValueStack) {
                avalue = (OgnlValueStack) avalue;
                Map<String, Object> m = ((OgnlValueStack) avalue).getContext();
                System.out.println("  >> OgnlValueStack:");
                Iterator<String> it = m.keySet().iterator();
                Object key;
                while (it.hasNext()) {
                    key = it.next();
                    System.out.println("        " + key + " ---- " + m.get(key));
                }
            }
        }

        // observe Request Session
        HttpSession session = request.getSession(false);
        System.out.println("session: " + session);
        if (session != null) {
            System.out.println("  sessionId: " + session.getId());
            enumer = session.getAttributeNames();
            System.out.println("Session Attributes:");
            while (enumer.hasMoreElements()) {
                name = enumer.nextElement();
                avalue = session.getAttribute(name);
                System.out.println("  " + name + " ---- " + avalue);
            }
        }
        System.out.println("/***************** End of Request Observer **************/");
        
        return this;
    }
}
