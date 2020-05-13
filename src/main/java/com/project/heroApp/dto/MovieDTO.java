package com.project.heroApp.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.project.heroApp.model.Movie} entity.
 */
public class MovieDTO implements Serializable {
    
    private Long id;

    @NotNull
    @Size(min = 1, max = 20)
    private String title;

    @NotNull
    @Size(min = 1, max = 20)
    private String category;

    @NotNull
    @DecimalMin(value = "0.5")
    @DecimalMax(value = "5")
    private Float starRating;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getStarRating() {
        return starRating;
    }

    public void setStarRating(Float starRating) {
        this.starRating = starRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MovieDTO movieDTO = (MovieDTO) o;
        if (movieDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), movieDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", category='" + getCategory() + "'" +
            ", starRating=" + getStarRating() +
            "}";
    }
}
