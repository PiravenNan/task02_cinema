package com.example.spring_cinema.services;


import com.example.spring_cinema.models.Movie;
import com.example.spring_cinema.models.Reply;
import com.example.spring_cinema.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
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

    public Reply findMovieLessThanDuration(int maxDuration){
        Iterable<Movie> Movies = movieRepository.findAll();
        String results = "Movies shorter than " + maxDuration + " minutes: ";

        for (Movie movie : Movies){
            if (movie.getDuration()<maxDuration){
                results += movie.getTitle() + " ";
            }
        }

        return new Reply(results);
    }


    public Optional<Movie> getMovieById(long id){
        return movieRepository.findById(id);
    }
}
