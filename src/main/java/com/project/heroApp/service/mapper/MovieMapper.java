package com.project.heroApp.service.mapper;

import org.mapstruct.Mapper;

import com.project.heroApp.dto.MovieDTO;
import com.project.heroApp.model.Movie;

/**
 * Mapper for the entity {@link Movie} and its DTO {@link MovieDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MovieMapper extends EntityMapper<MovieDTO, Movie> {

	default Movie fromId(Long id) {
		if (id == null) {
			return null;
		}
		Movie movie = new Movie();
		movie.setId(id);
		return movie;
	}
}
