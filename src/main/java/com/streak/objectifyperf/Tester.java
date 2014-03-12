package com.streak.objectifyperf;

import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.common.io.ByteStreams;

public class Tester {
  public static void datastoreTest(PrintWriter pr) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query = new Query("Case");
    PreparedQuery pq = datastore.prepare(query);
    for (Entity bob : pq.asIterable()) {
      pr.println("Hello, world from case #" + bob.getKey().getId());
      pr.println(" Favorite number: " + bob.getProperty("favoriteNumber"));
      List<String> notes = new ArrayList<>();
      Object o = bob.getProperty("notes");
      if (o != null)
        notes.add(o.toString());
      pr.println(" Notes: " + notes);
    }
  }
  
  public static void objectifyTest(PrintWriter pr) {
    for (Case bob : ofy().cache(false).load().type(Case.class)) {
      pr.println("Hello, world from case #" + bob.getCaseId());
      pr.println(" Favorite number: " + bob.getFavoriteNumber());
      for (String note : bob.getNotes()) {
        pr.println(" Note: " + note);
        break;
      }
    }
  }
  
  public static void doTests(PrintWriter pr) {
    doTests(pr, true);
  }
  
  public static void doTests(PrintWriter pr, boolean quiet) {
    final int WARMUP_ROUNDS = 5;
    PrintWriter jobPr = pr;
    if (quiet)
      jobPr = new PrintWriter(ByteStreams.nullOutputStream());
    
    double datastoreTime, objectifyTime;
    
    pr.println("Warming up...");
    for (int i=0; i<WARMUP_ROUNDS; i++) {
      datastoreTest(jobPr);
    }
    {
      pr.println("Running datastore test...");
      long t0 = System.nanoTime();
      datastoreTest(jobPr);
      long t1 = System.nanoTime();
      datastoreTime = (t1 - t0) / 1e9;
      pr.println("Time elapsed: " + datastoreTime + "s");
    }
    pr.println("Warming up...");
    for (int i=0; i<WARMUP_ROUNDS; i++) {
      objectifyTest(jobPr);
    }
    {
      ofy().clear();
      pr.println("Running objectify test...");
      long t0 = System.nanoTime();
      objectifyTest(jobPr);
      long t1 = System.nanoTime();
      objectifyTime = (t1 - t0) / 1e9;
      pr.println("Time elapsed: " + objectifyTime + "s");
      ofy().clear();
    }
    pr.printf("Objectify / datastore time: %.2f%%\n",(objectifyTime/datastoreTime)*100);
    pr.flush();
  }
}
