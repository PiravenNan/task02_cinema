package com.example.spring_cinema.controllers;

import com.example.spring_cinema.models.Movie;
import com.example.spring_cinema.models.Reply;
import com.example.spring_cinema.repositories.MovieRepository;
import com.example.spring_cinema.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<Movie> getMovies(@PathVariable long id){

        //may exist may not
        Optional<Movie> movie = movieService.getMovieById(id);

        if (movie.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);//reply = new Reply("ID is not in database");
        }else{
            return new ResponseEntity<>(movie.get(), HttpStatus.OK); //reply = new Reply("ID found");
        }

    }
    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(),HttpStatus.OK);
    }

}
