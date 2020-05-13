package com.project.heroApp.service;

import java.util.List;
import java.util.Optional;

import com.project.heroApp.dto.MovieDTO;

/**
 * Service Interface for managing {@link com.project.heroApp.model.Movie}.
 */
public interface MovieService {

    /**
     * Save a movie.
     *
     * @param movieDTO the entity to save.
     * @return the persisted entity.
     */
    MovieDTO save(MovieDTO movieDTO);

    /**
     * Get all the movies.
     *
     * @return the list of entities.
     */
    List<MovieDTO> findAll();

    /**
     * Get the "id" movie.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<MovieDTO> findOne(Long id);

    /**
     * Delete the "id" movie.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);

    /**
     * Update a movie.
     *
     * @param movieDTO the entity to save.
     * @return the persisted entity.
     */
	MovieDTO update(MovieDTO movieDTO);
}
