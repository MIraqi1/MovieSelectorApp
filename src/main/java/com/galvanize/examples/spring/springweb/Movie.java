package com.galvanize.examples.spring.springweb;

public class Movie {
  private Integer id;
  private String title;

  public Movie(Integer id, String title) {
    this.id = id;
    this.title = title;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) { 
      return true; 
    } 

    if (!(o instanceof Movie)) { 
      return false; 
    }

    Movie m = (Movie) o;

    return this.id == m.getId();
  }
}