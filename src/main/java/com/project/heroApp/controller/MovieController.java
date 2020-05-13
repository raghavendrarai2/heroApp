package com.project.heroApp.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.heroApp.Exception.BadRequestAlertException;
import com.project.heroApp.Exception.MovieNotFoundException;
import com.project.heroApp.dto.MovieDTO;
import com.project.heroApp.service.MovieService;

/**
 * REST controller for managing {@link com.project.heroApp.model.Movie}.
 */
@RestController
@RequestMapping("/api")
public class MovieController {

	private final Logger log = LoggerFactory.getLogger(MovieController.class);

	private final MovieService movieService;

	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}

	/**
	 * {@code POST  /movies} : Create a new movie.
	 *
	 * @param movieDTO the movieDTO to create.
	 * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with
	 *         body the new movieDTO, or with status {@code 400 (Bad Request)} if
	 *         the movie has already an ID.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PostMapping("/movie")
	public ResponseEntity<MovieDTO> createMovie(@Valid @RequestBody MovieDTO movieDTO) throws URISyntaxException {
		log.debug("REST request to save Movie : {}", movieDTO);
		if (movieDTO.getId() != null) {
    	throw new BadRequestAlertException("A new movie cannot already have an ID", movieDTO.getId());
		}
		MovieDTO result = movieService.save(movieDTO);
		return ResponseEntity.ok().body(result);
	}

	/**
	 * {@code PUT  /movies} : Updates an existing movie.
	 *
	 * @param movieDTO the movieDTO to update.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the updated movieDTO, or with status {@code 400 (Bad Request)} if the
	 *         movieDTO is not valid, or with status
	 *         {@code 500 (Internal Server Error)} if the movieDTO couldn't be
	 *         updated.
	 * @throws URISyntaxException if the Location URI syntax is incorrect.
	 */
	@PutMapping("/movie")
	public ResponseEntity<MovieDTO> updateMovie(@Valid @RequestBody MovieDTO movieDTO) throws URISyntaxException {
		log.debug("REST request to update Movie : {}", movieDTO);
		if (movieDTO.getId() == null) {
			throw new BadRequestAlertException("Movie could not be found with id: ",null);
		}
		MovieDTO result = movieService.update(movieDTO);
		return ResponseEntity.ok().body(result);
	}

	/**
	 * {@code GET  /movies} : get all the movies.
	 *
	 * @param pageable the pagination information.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list
	 *         of movies in body.
	 */
	@GetMapping("/movies")
	public ResponseEntity<List<MovieDTO>> getAllMovies() {
		log.debug("REST request to get a page of Movies");
		List<MovieDTO> movies = movieService.findAll();
		return ResponseEntity.ok().body(movies);
	}

	/**
	 * {@code GET  /movies/:id} : get the "id" movie.
	 *
	 * @param id the id of the movieDTO to retrieve.
	 * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body
	 *         the movieDTO, or with status {@code 404 (Not Found)}.
	 */
	@GetMapping("/movie/{id}")
	public ResponseEntity<Optional<MovieDTO>> getMovie(@PathVariable Long id) {
		log.debug("REST request to get Movie : {}", id);
		Optional<MovieDTO> movieDTO = movieService.findOne(id);
		if(movieDTO.isEmpty())
			throw new MovieNotFoundException(id);
		return ResponseEntity.ok().body(movieDTO);
	}

	/**
	 * {@code DELETE  /movies/:id} : delete the "id" movie.
	 *
	 * @param id the id of the movieDTO to delete.
	 * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
	 */
	@DeleteMapping("/movie/{id}")
	public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
		log.debug("REST request to delete Movie : {}", id);
		movieService.delete(id);
		return ResponseEntity.ok().body("Movie delted successfully.");
	}
}
