package hhsof3as3.musicdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hhsof3as3.musicdatabase.domain.Category;
import hhsof3as3.musicdatabase.domain.CategoryRepository;
import hhsof3as3.musicdatabase.domain.Music;
import hhsof3as3.musicdatabase.domain.MusicRepository;

@ExtendWith(SpringExtension.class) // JUnit 5/Jupiter
@DataJpaTest
public class MusicRepositoryTest {
	@Autowired
	private MusicRepository musicRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
    private TestEntityManager em;
	
	@Test // testataan MusicRepositoryn findByTitle()- metodin toimivuutta
	public void findByTitleShouldReturnTitle() {
		List<Music> musics = musicRepository.findByTitle("Song");
		
		assertThat(musics).hasSize(1);
		assertThat(musics.get(0).getArtist()).isEqualTo("Artist");
	}
	
	@Test // testataan MusicRepositoryn save-metodia
	public void createNewMusic()	{
		Music music = new Music();
		musicRepository.save(music);
		assertThat(music.getId()).isNotNull();
	}
	
	/*@Test // testataan MusicRepositoryn delete-metodia
	@Rollback(false)
	public void deleteMusic() {
		Music music = musicRepository.findById(Long.valueOf(1));
		musicRepository.delete(music);
		Optional<Music> deleteMusic = musicRepository.findById(Long.valueOf(1));
		assertThat(deleteMusic).isEmpty();
	}*/
	
	@Test // testataan MusicRepositoryn poisto-metodia
	public void DeleteMusicTest() {
		Music music = new Music("Song title", "Artist name", 2000, 2, null);
		final Long id = em.persistAndGetId(music, Long.class);
        musicRepository.deleteById(id);
        em.flush();
        Music after = em.find(Music.class, id);
		musicRepository.deleteById(music.getId());
		assertThat(after).isNull();
	}
	
	@Test // testataan CategoryRepository findByName-metodia
	public void findByNameShouldReturnName() {
		List<Category> categorys = categoryRepository.findByName("Metal");
		
		assertThat(categorys).hasSize(1);
		assertThat(categorys.get(0).getName()).isEqualTo("Metal");
		}
	
	@Test // testataan CategoryRepository save-metodia
	public void createNewCategory() {
		Category category = new Category();
		categoryRepository.save(category); // sql-insert
		assertThat(category.getCategoryid()).isNotNull();
	}
	
	@Test // testataan CategoryRepon poisto-metodia
	public void DeleteCategoryTest() {
		Category category = new Category("newCategory");
		final Long id = em.persistAndGetId(category, Long.class);
        categoryRepository.deleteById(id);
        em.flush();
        Category after = em.find(Category.class, id);
		categoryRepository.deleteById(category.getCategoryid());
		assertThat(after).isNull();
	}
}
