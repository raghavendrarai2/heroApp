package com.project.heroApp.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A Movie.
 */
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "title", length = 20, nullable = false, unique = true)
    private String title;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "category", length = 20, nullable = false)
    private String category;

    @NotNull
    @DecimalMin(value = "0.5")
    @DecimalMax(value = "5")
    @Column(name = "star_rating", nullable = false)
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

    public Movie title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public Movie category(String category) {
        this.category = category;
        return this;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Float getStarRating() {
        return starRating;
    }

    public Movie starRating(Float starRating) {
        this.starRating = starRating;
        return this;
    }

    public void setStarRating(Float starRating) {
        this.starRating = starRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Movie)) {
            return false;
        }
        return id != null && id.equals(((Movie) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Movie{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", category='" + getCategory() + "'" +
            ", starRating=" + getStarRating() +
            "}";
    }
}
