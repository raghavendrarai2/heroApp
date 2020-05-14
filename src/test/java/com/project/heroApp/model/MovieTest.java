package com.project.heroApp.model;

import org.junit.jupiter.api.Test;

import com.project.heroApp.util.TestUtil;

import static org.assertj.core.api.Assertions.assertThat;

public class MovieTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Movie.class);
        Movie movie1 = new Movie();
        movie1.setId(1L);
        Movie movie2 = new Movie();
        movie2.setId(movie1.getId());
        assertThat(movie1).isEqualTo(movie2);
        movie2.setId(2L);
        assertThat(movie1).isNotEqualTo(movie2);
        movie1.setId(null);
        assertThat(movie1).isNotEqualTo(movie2);
    }
}
