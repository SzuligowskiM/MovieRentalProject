package com.example.demo.services;

import com.example.demo.models.Movie;
import com.example.demo.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Optional<Movie> findById(Long Id){
        return movieRepository.findById(Id);
    }

    public List<Movie> find(){
        return movieRepository.findAll();
    }
    public List<Movie> findByCategory(String category){return movieRepository.findByCategory(category);}
    public List<Movie> findByTitle(String title){return movieRepository.findByTitle(title);}
    @Transactional
    public void save(Movie movie){
        movieRepository.save(movie);
    }

    @Transactional
    public void update(Long Id,Movie movie) throws Exception{
        Optional<Movie> foundMovie = movieRepository.findById(Id);
        if(foundMovie.isPresent()){
            foundMovie.get().setTitle(movie.getTitle());
            foundMovie.get().setImage(movie.getImage());
            foundMovie.get().setCategory(movie.getCategory());
            foundMovie.get().setPrice(movie.getPrice());
            movieRepository.save(foundMovie.get());
        }
        else throw new Exception("MOVIE NOT FOUND");
    }

    @Transactional
    public void delete(Long Id) throws Exception{
        Optional<Movie> foundMovie = movieRepository.findById(Id);
        if(foundMovie.isPresent()){
            movieRepository.delete(foundMovie.get());
        }
        else throw new Exception("MOVIE NOT FOUND");
    }
}
