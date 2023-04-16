package hhsof3as3.musicdatabase.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Category {
	@Id // Primary key -määritys
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	private Long categoryid;
	@NotNull
	@Size(max=20)
	private String name;
	
	// Category 1--* music
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	@JsonIgnoreProperties("category")
	private List<Music> musics;
	
public Category() {}
	
	public Category(String name) {
		super();
		this.name = name;
	}

	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// tänne ei kirjan attribuutteja ikuisen luupin välttämiseksi tietokantahaussa!
		return "Category [categoryid=" + categoryid + ", name=" + name + "]";
	}
	

}
