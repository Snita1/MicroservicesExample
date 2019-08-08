package com.movieinfo.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.movieinfo.model.Movie;

@RestController
@RequestMapping("/Movies")
public class MovieInfoController {
	
	@RequestMapping("/{movieId}")
	public Movie getCatalog(@PathVariable("movieId") String movieId){
		Movie movie = new Movie(movieId,"Test","Love Story");
		return movie;
		
	}
	
	

}
