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
        movieRepository.save(movie);
        return new Reply("Added movie");
    }

    public Reply removeMovie(long id){
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isEmpty()){
            return new Reply("ID not found");
        }else{
            Movie movie = optionalMovie.get();
            movieRepository.delete(movie);
            return new Reply("removed movie");
        }

    }

    public Reply updateMovie(long id, Movie movie){
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if (optionalMovie.isEmpty()){
            return new Reply("ID not found");
        }else{
            movie.setId(id);
            movieRepository.save(movie);
            return new Reply("updated movie");
        }

    }


    public Optional<Movie> getMovieById(long id){
        return movieRepository.findById(id);
    }
}
