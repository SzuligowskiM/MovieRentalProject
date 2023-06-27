package com.example.demo.controllers;

import com.example.demo.models.Movie;
import com.example.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/Movie")
public class MovieController {
    @Autowired
    private MovieService movieService;
    @GetMapping(value = "/{Id}")
    public ResponseEntity<Optional<Movie>> getMovieById(@PathVariable Long Id) {
        Optional<Movie> movie = movieService.findById(Id);
        if(movie.isPresent()) return ResponseEntity.ok(movie);
        return ResponseEntity.notFound().build();
    }

    @GetMapping()
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.find();
        if(movies.isEmpty()) ResponseEntity.noContent().build();
        return ResponseEntity.ok(movies);
    }
    @GetMapping(value = "category/{category}")
    public ResponseEntity<List<Movie>> getMoviesByCategory(@PathVariable String category) {
        List<Movie> movies = movieService.findByCategory(category);
        if(movies.isEmpty()) ResponseEntity.noContent().build();
        return ResponseEntity.ok(movies);
    }

    @GetMapping(value = "title/{title}")
    public ResponseEntity<List<Movie>> getMoviesByTitle(@PathVariable String title) {
        List<Movie> movies = movieService.findByTitle(title);
        if(movies.isEmpty()) ResponseEntity.noContent().build();
        return ResponseEntity.ok(movies);
    }

    @PostMapping()
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        movieService.save(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(movie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        try {
            movieService.update(id, movie);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        try {
            movieService.delete(id);
        }
        catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
