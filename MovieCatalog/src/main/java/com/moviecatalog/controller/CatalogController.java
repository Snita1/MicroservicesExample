package com.moviecatalog.controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
//import org.springframework.web.reactive.function.client.WebClient;

import com.moviecatalog.model.CatalogItem;
import com.moviecatalog.model.Movie;
import com.moviecatalog.model.Rating;
import com.moviecatalog.model.UserRating;

@RestController
@RequestMapping("/Catalog")
public class CatalogController {
	@Autowired
	private RestTemplate restTemplate;
	/*@Autowired
	private WebClient.Builder webClientBuilder;
*/
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		UserRating ratings = restTemplate.getForObject("http://RatingDataServices/ratingsdata/users/" + userId,
				UserRating.class);
		// Arrays.asList(new Rating("123", 9), new Rating("456", 10));

		return ratings.getRatings().stream().map(rating -> {
			/*
			 * //using WebClient and asynchronous call Movie movie=webClientBuilder.build()
			 * .get() .uri("http://localhost:8081/Movies/" + rating.getMovieId())
			 * .retrieve() .bodyToMono(Movie.class) .block();
			 */
			Movie movie = restTemplate.getForObject("http://MovieInfo/Movies/" + rating.getMovieId(), Movie.class); // using
																															// resttemplate

			return new CatalogItem(movie.getMovieName(), movie.getMovieDesc(), rating.getRating());
		}).collect(Collectors.toList());

		// Collections.singletonList(new CatalogItem("Titanic", "Love Story", 4));

	}

}

/*
 * 
 * @RequestMapping("/{userId}") public List<CatalogItem>
 * getCatalog(@PathVariable("userId") String userId) { RestTemplate restTemplate
 * = new RestTemplate(); //If declare here multiple instance List<Rating>
 * ratings = Arrays.asList(new Rating("123", 9), new Rating("456", 10));
 * 
 * return ratings.stream().map(rating->{ Movie
 * movie=restTemplate.getForObject("http://localhost:8081/Movies/"+rating.
 * getMovieId(), Movie.class); return new CatalogItem(movie.getMovieName(),
 * "desc", rating.getRating()); }) .collect(Collectors.toList());
 * 
 * 
 * //Collections.singletonList(new CatalogItem("Titanic", "Love Story", 4));
 * 
 * }
 */
//Rest template autowired
//Adding Web builder for using multiple apis
