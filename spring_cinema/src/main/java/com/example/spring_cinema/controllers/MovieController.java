package com.example.spring_cinema.controllers;

import com.example.spring_cinema.models.Movie;
import com.example.spring_cinema.models.Reply;
import com.example.spring_cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/movies")
public class MovieController {

    @Autowired
    MovieService movieService;
    @PostMapping
    public ResponseEntity<Reply> newMovie(@RequestBody Movie movie){
        Reply reply = movieService.addMovie(movie);
        return new ResponseEntity<>(reply, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/remove/{id}")
    public ResponseEntity<Reply> newMovie(@PathVariable long id){
        Reply reply = movieService.removeMovie(id);
        return new ResponseEntity<>(reply, HttpStatus.GONE);
    }

    @PatchMapping(value = "/duration/{duration}")
    public ResponseEntity<Reply> findMovieLessThanDuration(@PathVariable int duration){
        Reply reply = movieService.findMovieLessThanDuration(duration);
        return new ResponseEntity<>(reply, HttpStatus.ACCEPTED);
    }

    @PatchMapping(value = "/update/{id}")
    public ResponseEntity<Reply> updateMovie(@RequestBody Movie movie, @PathVariable long id){
        Reply reply = movieService.updateMovie(id,movie);
        return new ResponseEntity<>(reply, HttpStatus.ACCEPTED);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Reply> getMovies(@PathVariable long id){
        Reply reply;

        //may exist may not
        Optional<Movie> movie = movieService.getMovieById(id);

        if (movie.isEmpty()){
            reply = new Reply("ID is not in database");
        }else{
            reply = new Reply("ID found");
        }
        return new ResponseEntity<>(reply, HttpStatus.CONTINUE);
    }

}
