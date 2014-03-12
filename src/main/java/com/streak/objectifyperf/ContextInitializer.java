package com.streak.objectifyperf;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.ObjectifyService;

public class ContextInitializer implements ServletContextListener {
  @Override
  public void contextDestroyed(ServletContextEvent event) {
  }

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ObjectifyService.register(Case.class);
  }
}
