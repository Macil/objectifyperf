package com.streak.objectifyperf;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;

public class ContextInitializer implements ServletContextListener {
  @Override
  public void contextDestroyed(ServletContextEvent event) {
  }

  @Override
  public void contextInitialized(ServletContextEvent event) {
    ObjectifyService.register(Case.class);
    
    // Populate datastore on first run
    final int TARGET_COUNT = 100000;
    List<Key<Case>> keys = ofy().cache(false).load().type(Case.class).keys().list();
    if (keys.size() == TARGET_COUNT) {
      System.out.println("Datastore already populated with "+TARGET_COUNT+" entries.");
    } else {
      System.out.println("Populating datastore...");
      ofy().delete().keys(keys).now();
      
      Random r = new Random();
      
      List<Case> cases = new ArrayList<>();
      for (int i=0; i<TARGET_COUNT; i++) {
        Case c = new Case();
        c.setFavoriteNumber(r.nextInt(100));
        c.setUserId(r.nextInt(2000));
        c.setName("bob"+r.nextInt());
        final int notecount = r.nextInt(40);
        List<String> notes = c.getNotes();
        for (int j=0; j<notecount; j++) {
          if (r.nextBoolean())
            notes.add("I'm making a note right here");
          else
            notes.add("more note");
        }
        cases.add(c);
        if (i%1000 == 0) {
          ofy().cache(false).save().entities(cases).now();
          ofy().clear();
          cases.clear();
        }
      }
      ofy().cache(false).save().entities(cases).now();
      ofy().clear();
      System.out.println("Populated "+TARGET_COUNT+" entries into the datastore.");
    }
  }
}
