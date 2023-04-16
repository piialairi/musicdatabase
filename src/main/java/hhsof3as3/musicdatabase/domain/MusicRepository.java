package hhsof3as3.musicdatabase.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MusicRepository extends CrudRepository<Music, Long> {
	// peritään findAll(), findById(), save(), deleteById()
	List<Music> findById(String musicId);

	List<Music> findByTitle(String title);
	
	List<Music> findByArtist (String artist);
	
}
