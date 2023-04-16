package hhsof3as3.musicdatabase.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Music {
	@Id // Primary key -määritys
	@GeneratedValue(strategy = GenerationType.AUTO) // automaattinen id-arvo
	@NotNull
	private Long id;
	@NotNull
	private String title;
	private String artist;
	private int release_year;
	private double length;
	
	// Music *--1 Category
	@ManyToOne
	@JsonIgnoreProperties(value="musics")
	@JoinColumn(name="categoryid") // foreign key -määritys
	private Category category;
	
	// Music *--1 Playlist
	@ManyToOne
	@JsonIgnoreProperties(value="musics")
	@JoinColumn(name="playlistId") // foreign key -määritys
	private Playlist playlist;
	
	public Music() {
		super();
		this.id = null;
		this.title = null;
		this.artist = null;
		this.release_year = 0;
		this.length = 0;
	}

	public Music(String title, String artist, int release_year, double length, Category category) {
		super();
		this.title = title;
		this.artist = artist;
		this.release_year = release_year;
		this.length = length;
		this.category = category;
	}
	
	public Music(Long id, String title, String artist, int release_year, double length, Category category) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.release_year = release_year;
		this.length = length;
		this.category = category;
	}

	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getArtist() {
		return artist;
	}
	public int getRelease_year() {
		return release_year;
	}
	public double getLength() {
		return length;
	}
	public Category getCategory() {
		return category;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}	
	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}
	
	public void setLength(double length) {
		this.length = length;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public String toString() {
		return "Music [id=" + id + ", title=" + title + ", artist=" + artist + ", release_year=" + release_year + ", length=" + length + "]";
	}
	
	
	

}
