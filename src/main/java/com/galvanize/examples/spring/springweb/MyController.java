package com.galvanize.examples.spring.springweb;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/movies")
public class MyController {
  
  private int count = 0;

  private List<Movie> movies = new ArrayList<>();

  {
    this.movies.add(new Movie(1, "Lion King"));
    this.movies.add(new Movie(2, "Daughters of the Dust"));
    this.movies.add(new Movie(3, "Quilombo"));
  }


  private Movie getMovie(final Integer id) {
    return movies.stream().filter(movie -> movie.getId() == id).findFirst().orElse(null);
  }

  @GetMapping
  public List<Movie> getMovies() {
    return movies;
  }

  @GetMapping("/{id}")
  public Movie getMovieById(@PathVariable final Integer id) {
    return getMovie(id);
  }

  @PostMapping
  public Movie addMovie(@RequestBody final Movie movie) {
    count++;

    movie.setId(count);
    movies.add(movie);

    return movie;
  }

  @PutMapping("/{id}")
  public Movie updateMovie(@PathVariable final Integer id, @RequestBody final Movie movie) {
    final Movie movieToUpdate = getMovie(id);

    if (movieToUpdate == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    movieToUpdate.setTitle(movie.getTitle());
    return movieToUpdate;
  }

  @DeleteMapping("/{id}")
  public void deleteMovie(@PathVariable final Integer id) {
    final Movie movieToDelete = new Movie(id, "");
    movies.remove(movieToDelete);
  }
}