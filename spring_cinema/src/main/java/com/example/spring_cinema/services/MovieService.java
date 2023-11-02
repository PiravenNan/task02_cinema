package com.example.spring_cinema.services;


import com.example.spring_cinema.models.Movie;
import com.example.spring_cinema.models.Reply;
import com.example.spring_cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    private Movie movie;

    public Reply addMovie(Movie movie){
        //Movie movie = new Movie(title,rating,duration);

        movieRepository.save(movie);

        return new Reply("Added movie");
    }

    public Optional<Movie> getMovieById(long id){
        return movieRepository.findById(id);
    }
}
