package assgn.stock.config;
/**
 * 
 *//*
package com.nerve24.gateway;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

*//**
 * @author Jogireddy Kotam
 *
 *//*
@Component
public class SessionListener implements HttpSessionListener {
    
    private static final Logger logger = LogManager.getLogger(SessionListener.class);
    
    @Value("${server.session-timeout}")
    private int maxInactiveIntervalInSeconds;

    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        logger.info("Session started for context path" + sessionEvent.getSession().getServletContext().getContextPath());        
        //sessionEvent.getSession().setMaxInactiveInterval(maxInactiveIntervalInSeconds);        
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent sessionEvent) {
        logger.info(sessionEvent.getSession().getLastAccessedTime());        
    }

}
*/