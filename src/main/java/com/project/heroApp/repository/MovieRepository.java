package com.project.heroApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.heroApp.model.Movie;

/**
 * Spring Data  repository for the Movie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
}
