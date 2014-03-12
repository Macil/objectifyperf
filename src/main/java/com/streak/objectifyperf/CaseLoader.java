package com.streak.objectifyperf;

import java.util.List;

import com.google.appengine.api.datastore.Entity;
import com.googlecode.objectify.impl.translate.EntityLoader;

public class CaseLoader implements EntityLoader<Case> {
  @SuppressWarnings("unchecked")
  @Override
  public Case load(Entity en) {
    Case c = new Case(en.getKey().getId());
    c.setUserId(((Long)en.getProperty("userId")).intValue());
    c.setFavoriteNumber(((Long)en.getProperty("favoriteNumber")).intValue());
    c.setMoreNumber(((Long)en.getProperty("moreNumber")).intValue());
    c.setName((String)en.getProperty("name"));
    c.setNotes((List<String>)en.getProperty("notes"));
    
    return c;
  }
}
