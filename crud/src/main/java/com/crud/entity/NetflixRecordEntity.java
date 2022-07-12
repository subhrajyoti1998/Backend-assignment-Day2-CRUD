package com.crud.entity;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "netflix_data")
public class NetflixRecordEntity implements Cloneable {

	@Id
	@Column(name = "show_id")
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String showId;
	
	@Column(name = "show_type")
	private String showType;
	
	@Column(name = "title")
	private String title;

	@Column(name = "director")
	private String director;

	@Column(name = "cast_details")
	private String cast;

	@Column(name = "country")
	private String country;

	@Column(name = "date_added")
	private Date dateAdded;

	@Override
	public String toString() {
		return "NetflixRecordEntity [showId=" + showId + ", showType=" + showType + ", title=" + title + ", director="
				+ director + ", cast=" + cast + ", country=" + country + ", dateAdded=" + dateAdded + ", releaseYear="
				+ releaseYear + ", rating=" + rating + ", duration=" + duration + ", listedIn=" + listedIn
				+ ", description=" + description + "]";
	}

	@Column(name = "release_year")
	private String releaseYear;

	@Column(name = "rating")
	private String rating;

	@Column(name = "duration")
	private String duration;

	@Column(name = "listed_in")
	private String listedIn;

	@Column(name = "description")
	private String description;
	
	public NetflixRecordEntity() {
		
	}

	public NetflixRecordEntity(String showId, String showType, String title, String director, String cast,
			String country, Date dateAdded, String releaseYear, String rating, String duration, String listedIn,
			String description) {
		super();
		this.showId = showId;
		this.showType = showType;
		this.title = title;
		this.director = director;
		this.cast = cast;
		this.country = country;
		this.dateAdded = dateAdded;
		this.releaseYear = releaseYear;
		this.rating = rating;
		this.duration = duration;
		this.listedIn = listedIn;
		this.description = description;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getCast() {
		return cast;
	}

	public void setCast(String cast) {
		this.cast = cast;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public String getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(String releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getListedIn() {
		return listedIn;
	}

	public void setListedIn(String listedIn) {
		this.listedIn = listedIn;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cast, country, dateAdded, description, director, duration, listedIn, rating, releaseYear,
				showId, showType, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NetflixRecordEntity other = (NetflixRecordEntity) obj;
		return Objects.equals(cast, other.cast) && Objects.equals(country, other.country)
				&& Objects.equals(dateAdded, other.dateAdded) && Objects.equals(description, other.description)
				&& Objects.equals(director, other.director) && Objects.equals(duration, other.duration)
				&& Objects.equals(listedIn, other.listedIn) && Objects.equals(rating, other.rating)
				&& Objects.equals(releaseYear, other.releaseYear) && Objects.equals(showId, other.showId)
				&& Objects.equals(showType, other.showType) && Objects.equals(title, other.title);
	}

	@Override
	public NetflixRecordEntity clone() {
		return new NetflixRecordEntity(this.showId, this.showType, this.title, this.director,this.cast = cast,
										this.country, this.dateAdded, this.releaseYear, this.rating, this.duration,
										this.listedIn, this.description);
	}
	
}