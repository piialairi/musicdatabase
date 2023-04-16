package hhsof3as3.musicdatabase;

import java.util.List;
import org.springframework.context.support.ResourceBundleMessageSource;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hhsof3as3.musicdatabase.domain.Playlist;
import hhsof3as3.musicdatabase.domain.PlaylistRepository;
import hhsof3as3.musicdatabase.domain.Category;
import hhsof3as3.musicdatabase.domain.CategoryRepository;
import hhsof3as3.musicdatabase.domain.Music;
import hhsof3as3.musicdatabase.domain.MusicRepository;
import hhsof3as3.musicdatabase.domain.User;
import hhsof3as3.musicdatabase.domain.UserRepository;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@SpringBootApplication
public class MusicdatabaseApplication implements WebMvcConfigurer {
	private static final Logger log = LoggerFactory.getLogger(MusicdatabaseApplication.class);
	
	//private final LocaleChangeInterceptor localeChangeInterceptor;
	
	/*public MusicdatabaseApplication(LocaleChangeInterceptor localeChangeInterceptor) {
	   this.localeChangeInterceptor = localeChangeInterceptor;
	 }*/

	/*@Override
	  public void addInterceptors(InterceptorRegistry interceptorRegistry) {
	    interceptorRegistry.addInterceptor(localeChangeInterceptor);
	  }
	public static void main(String[] args) {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	    messageSource.setBasenames("lang/messages");
	    messageSource.setDefaultEncoding("UTF-8");
	   
		SpringApplication.run(MusicdatabaseApplication.class, args);
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(MusicdatabaseApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoData(PlaylistRepository playlistRepository, MusicRepository musicRepository, CategoryRepository categoryRepository, UserRepository userRepository
			) {
		return (args) -> {
			
			
			log.info("save some sample categories");
			Category category1 = new Category("Relaxing");
			categoryRepository.save(category1);
			Category category2 = new Category("Hardcore ");
			categoryRepository.save(category2);
			Category category3 = new Category("Metal");
			categoryRepository.save(category3);
			Category category4 = new Category("Alternative Metal");
			categoryRepository.save(category4);
			
			playlistRepository.save(new Playlist("Pilvipeiton alla", "Iina Kangasharju", 2011, 4.02, category1));
			playlistRepository.save(new Playlist("Sininen uni", "Tapio Rautavaara", 1983, 3.31, category1));
			playlistRepository.save(new Playlist("Kultasiipinen", "Eeppi Ursin", 2009, 4.02, category1));
			playlistRepository.save(new Playlist("Suojelusenkeli", "Maija Hapuoja", 1983, 1.55, category1));
			playlistRepository.save(new Playlist("Kuinka ihmeessä nukkuukan hauki", "Essi Wuorela", 2009, 2.53, category1));
			playlistRepository.save(new Playlist("Tiitiäisen tuutulaulu", "Ykä, Matti, Liisa", 1983, 2.14, category1));
			playlistRepository.save(new Playlist("Lapin äidin kehtolaulu", "Maarit Hurmerinta, Johanna Iivanainen", 2012, 4.40, category1));
			playlistRepository.save(new Playlist("Mad World", "Sergei Baronin", 2017, 3.15, category1));
			playlistRepository.save(new Playlist("Can you feel the love tonight", "Rita May", 2017, 4.39, category1));
			playlistRepository.save(new Playlist("Take Me To Church", "Tomas Lee", 2017, 3.45, category1));
			playlistRepository.save(new Playlist("Yesterday", "Brandon Mills", 2017, 3.10, category1));
			
			musicRepository.save(new Music("Battle Ready", "Otep", 2002, 4.21, category4));
			musicRepository.save(new Music("Happy?", "Mudvayne", 2015, 3.36, category3));
			musicRepository.save(new Music("Nerve", "Soilwork", 2005, 3.38, category3));
			musicRepository.save(new Music("Pet", "A Perfect Circle", 2003, 4.34, category4));
			musicRepository.save(new Music("The Devil And I", "Slipknot", 201, 5.42, category3));
			musicRepository.save(new Music("Mayhem", "Halestorm", 2015, 3.36, category3));
			musicRepository.save(new Music("Drained", "In Flames", 2016, 4.06, category3));
			musicRepository.save(new Music("Dead Eyes See No Future", "Arch Enemy", 2003, 4.14, category3));
			musicRepository.save(new Music("Headup", "Deftones", 1997, 6.12, category4));
			musicRepository.save(new Music("Criminally Insane", "Angerfist", 2015, 4.44, category2));
			musicRepository.save(new Music("The Many-Faced God", "D-Ceptor", 2023, 4.13, category2));
			/*Music music1 = new Music("Laulu", 2015, 3.45, category1);
			Music music2 = new Music("Laulu2", 2000	, 2.20, category1);
			musicRepository.save(music1); // SQL-insert
			musicRepository.save(music2); */
			
			//Create users: admin/admin user/user
			User user1 = new User("user", "$2a$10$u9qCzUi16TtwlE.TCpQ/qutHt.6Wd8AG0V1vcTfeoafUxcQeRThcm", "USER");
			User user2 = new User("admin", "$2a$10$EubGXsWBrZiq3UaTnts0m.Mep3l63w2P9wZQ74gh2D8VdREH/9Kge", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			//List<Music> musics = (List<Music>) musicRepository.findAll();
			log.info("save some sample songs");
			for (Music music : musicRepository.findAll());
			
			log.info("fetch all music");
			for (Music music : musicRepository.findAll()) {
				log.info(music.toString());
			}
	
		
		};
	}
}
