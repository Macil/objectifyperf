package com.streak.objectifyperf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity @Cache
public class Case {
  @Id private Long caseId;
  
  private int userId;
  private int favoriteNumber = 0;
  @Index private int moreNumber = 1;
  private Date creationTimestamp;
  private String name;
  private List<String> notes = new ArrayList<>();
  
  public Case() {
  }
  protected Case(Long caseId) {
    this.caseId = caseId;
  }
  
  public Long getCaseId() {
    return caseId;
  }
  public int getUserId() {
    return userId;
  }
  public void setUserId(int userId) {
    this.userId = userId;
  }
  public Date getCreationTimestamp() {
    return creationTimestamp;
  }
  public void setCreationTimestamp(Date creationTimestamp) {
    this.creationTimestamp = creationTimestamp;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public List<String> getNotes() {
    return notes;
  }
  public void setNotes(List<String> notes) {
    this.notes = notes;
  }
  public int getFavoriteNumber() {
    return favoriteNumber;
  }
  public void setFavoriteNumber(int favoriteNumber) {
    this.favoriteNumber = favoriteNumber;
  }
  public int getMoreNumber() {
    return moreNumber;
  }
  public void setMoreNumber(int moreNumber) {
    this.moreNumber = moreNumber;
  }
}
