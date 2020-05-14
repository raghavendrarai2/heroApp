package com.project.heroApp.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.project.heroApp.HeroAppApplication;
import com.project.heroApp.dto.MovieDTO;
import com.project.heroApp.mapper.MovieMapper;
import com.project.heroApp.model.Movie;
import com.project.heroApp.repository.MovieRepository;
import com.project.heroApp.util.TestUtil;

/**
 * Integration tests for the {@link MovieResource} REST controller.
 */
@SpringBootTest(classes = HeroAppApplication.class)

@AutoConfigureMockMvc
@WithMockUser
public class MovieControllerTest {

	private static final String DEFAULT_TITLE = "AAAAAAAAAA";
	private static final String UPDATED_TITLE = "BBBBBBBBBB";

	private static final String DEFAULT_CATEGORY = "AAAAAAAAAA";
	private static final String UPDATED_CATEGORY = "BBBBBBBBBB";

	private static final Float DEFAULT_STAR_RATING = 0.5F;
	private static final Float UPDATED_STAR_RATING = 1F;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private MovieMapper movieMapper;

//	@Autowired
//	private MovieService movieService;

	@Autowired
	private EntityManager em;

	@Autowired
	private MockMvc restMovieMockMvc;

	private Movie movie;

	/**
	 * Create an entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it, if
	 * they test an entity which requires the current entity.
	 */
	public static Movie createEntity(EntityManager em) {
		Movie movie = new Movie().title(DEFAULT_TITLE).category(DEFAULT_CATEGORY).starRating(DEFAULT_STAR_RATING);
		return movie;
	}

	/**
	 * Create an updated entity for this test.
	 *
	 * This is a static method, as tests for other entities might also need it, if
	 * they test an entity which requires the current entity.
	 */
	public static Movie createUpdatedEntity(EntityManager em) {
		Movie movie = new Movie().title(UPDATED_TITLE).category(UPDATED_CATEGORY).starRating(UPDATED_STAR_RATING);
		return movie;
	}

	@BeforeEach
	public void initTest() {
		movie = createEntity(em);
	}

	@Test
	@Transactional
	public void createMovie() throws Exception {
		int databaseSizeBeforeCreate = movieRepository.findAll().size();

		// Create the Movie
		MovieDTO movieDTO = movieMapper.toDto(movie);
		restMovieMockMvc.perform(post("/api/movie").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(movieDTO))).andExpect(status().isOk());

		// Validate the Movie in the database
		List<Movie> movieList = movieRepository.findAll();
		assertThat(movieList).hasSize(databaseSizeBeforeCreate + 1);
		Movie testMovie = movieList.get(movieList.size() - 1);
		assertThat(testMovie.getTitle()).isEqualTo(DEFAULT_TITLE);
		assertThat(testMovie.getCategory()).isEqualTo(DEFAULT_CATEGORY);
		assertThat(testMovie.getStarRating()).isEqualTo(DEFAULT_STAR_RATING);
	}

	@Test
	@Transactional
	public void createMovieWithExistingId() throws Exception {
		int databaseSizeBeforeCreate = movieRepository.findAll().size();

		// Create the Movie with an existing ID
		movie.setId(1L);
		MovieDTO movieDTO = movieMapper.toDto(movie);

		// An entity with an existing ID cannot be created, so this API call must fail
		restMovieMockMvc.perform(post("/api/movie").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(movieDTO))).andExpect(status().isBadRequest());

		// Validate the Movie in the database
		List<Movie> movieList = movieRepository.findAll();
		assertThat(movieList).hasSize(databaseSizeBeforeCreate);
	}

	@Test
	@Transactional
	public void checkTitleIsRequired() throws Exception {
		int databaseSizeBeforeTest = movieRepository.findAll().size();
		// set the field null
		movie.setTitle(null);

		// Create the Movie, which fails.
		MovieDTO movieDTO = movieMapper.toDto(movie);

		restMovieMockMvc.perform(post("/api/movie").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(movieDTO))).andExpect(status().isBadRequest());

		List<Movie> movieList = movieRepository.findAll();
		assertThat(movieList).hasSize(databaseSizeBeforeTest);
	}

	@Test
	@Transactional
	public void checkCategoryIsRequired() throws Exception {
		int databaseSizeBeforeTest = movieRepository.findAll().size();
		// set the field null
		movie.setCategory(null);

		// Create the Movie, which fails.
		MovieDTO movieDTO = movieMapper.toDto(movie);

		restMovieMockMvc.perform(post("/api/movie").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(movieDTO))).andExpect(status().isBadRequest());

		List<Movie> movieList = movieRepository.findAll();
		assertThat(movieList).hasSize(databaseSizeBeforeTest);
	}

	@Test
	@Transactional
	public void checkStarRatingIsRequired() throws Exception {
		int databaseSizeBeforeTest = movieRepository.findAll().size();
		// set the field null
		movie.setStarRating(null);

		// Create the Movie, which fails.
		MovieDTO movieDTO = movieMapper.toDto(movie);

		restMovieMockMvc.perform(post("/api/movie").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(movieDTO))).andExpect(status().isBadRequest());

		List<Movie> movieList = movieRepository.findAll();
		assertThat(movieList).hasSize(databaseSizeBeforeTest);
	}

	@Test
	@Transactional
	public void getAllMovies() throws Exception {
		// Initialize the database
		movieRepository.saveAndFlush(movie);

		// Get all the movieList
		restMovieMockMvc.perform(get("/api/movies")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.[*].id").value(hasItem(movie.getId().intValue())))
				.andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE)))
				.andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY)))
				.andExpect(jsonPath("$.[*].starRating").value(hasItem(DEFAULT_STAR_RATING.doubleValue())));
	}

	@Test
	@Transactional
	public void getMovie() throws Exception {
		// Initialize the database
		movieRepository.saveAndFlush(movie);

		// Get the movie
		restMovieMockMvc.perform(get("/api/movie/{id}", movie.getId())).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(jsonPath("$.id").value(movie.getId().intValue()))
				.andExpect(jsonPath("$.title").value(DEFAULT_TITLE))
				.andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY))
				.andExpect(jsonPath("$.starRating").value(DEFAULT_STAR_RATING.doubleValue()));
	}

	@Test
	@Transactional
	public void getNonExistingMovie() throws Exception {
		// Get the movie
		restMovieMockMvc.perform(get("/api/movie/{id}", Long.MAX_VALUE)).andExpect(status().isNotFound());
	}

	@Test
	@Transactional
	public void updateMovie() throws Exception {
		// Initialize the database
		movieRepository.saveAndFlush(movie);

		int databaseSizeBeforeUpdate = movieRepository.findAll().size();

		// Update the movie
		Movie updatedMovie = movieRepository.findById(movie.getId()).get();
		// Disconnect from session so that the updates on updatedMovie are not directly
		// saved in db
		em.detach(updatedMovie);
		updatedMovie.title(UPDATED_TITLE).category(UPDATED_CATEGORY).starRating(UPDATED_STAR_RATING);
		MovieDTO movieDTO = movieMapper.toDto(updatedMovie);

		restMovieMockMvc.perform(put("/api/movie").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(movieDTO))).andExpect(status().isOk());

		// Validate the Movie in the database
		List<Movie> movieList = movieRepository.findAll();
		assertThat(movieList).hasSize(databaseSizeBeforeUpdate);
		Movie testMovie = movieList.get(movieList.size() - 1);
		assertThat(testMovie.getTitle()).isEqualTo(UPDATED_TITLE);
		assertThat(testMovie.getCategory()).isEqualTo(UPDATED_CATEGORY);
		assertThat(testMovie.getStarRating()).isEqualTo(UPDATED_STAR_RATING);
	}

	@Test
	@Transactional
	public void updateNonExistingMovie() throws Exception {
		int databaseSizeBeforeUpdate = movieRepository.findAll().size();

		// Create the Movie
		MovieDTO movieDTO = movieMapper.toDto(movie);

		// If the entity doesn't have an ID, it will throw BadRequestAlertException
		restMovieMockMvc.perform(put("/api/movie").contentType(MediaType.APPLICATION_JSON)
				.content(TestUtil.convertObjectToJsonBytes(movieDTO))).andExpect(status().isBadRequest());

		// Validate the Movie in the database
		List<Movie> movieList = movieRepository.findAll();
		assertThat(movieList).hasSize(databaseSizeBeforeUpdate);
	}

	@Test
	@Transactional
	public void deleteMovie() throws Exception {
		// Initialize the database
		movieRepository.saveAndFlush(movie);

		int databaseSizeBeforeDelete = movieRepository.findAll().size();

		// Delete the movie
		restMovieMockMvc.perform(delete("/api/movie/{id}", movie.getId()));

		// Validate the database contains one less item
		List<Movie> movieList = movieRepository.findAll();
		assertThat(movieList).hasSize(databaseSizeBeforeDelete - 1);
	}
}
