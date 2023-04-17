package hhsof3as3.musicdatabase.webController;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hhsof3as3.musicdatabase.domain.PlaylistRepository;
import hhsof3as3.musicdatabase.domain.User;
import hhsof3as3.musicdatabase.domain.UserRepository;
import jakarta.validation.Valid;
import hhsof3as3.musicdatabase.MusicdatabaseApplication;
import hhsof3as3.musicdatabase.domain.CategoryRepository;
import hhsof3as3.musicdatabase.domain.Music;
import hhsof3as3.musicdatabase.domain.MusicRepository;
import hhsof3as3.musicdatabase.domain.Playlist;


@Controller
public class MusicController {
	private static final Logger log = LoggerFactory.getLogger(MusicdatabaseApplication.class);
	@Autowired
	private MusicRepository musicRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PlaylistRepository playlistRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/index") // tee tästä joku aloitussivu linkkeineen
	public String Musicpage() {
		return "musicpage";
	}
	
	//musiikkilistaus
	@RequestMapping(value = "/musiclist", method = RequestMethod.GET)
	public String musicList(Model model) {
		List<Music> musics = (List<Music>) musicRepository.findAll(); //haetaan tietokannasta
		model.addAttribute("musics", musics); // välitetään musiikkilista templatelle model-olion avulla
		return "musiclist"; //.html
	}
	
	//musiikkilistaus 2
	@RequestMapping(value = "/playlist", method = RequestMethod.GET)
	public String playlist(Model model) {
		List<Playlist> playlist = (List<Playlist>) playlistRepository.findAll(); //haetaan tietokannasta
		model.addAttribute("playlists", playlist); // välitetään musiikkilista templatelle model-olion avulla
		return "playlist"; //.html
	}
	
	// REST-metodi get all songs
	@GetMapping("/musics")
	public @ResponseBody List<Music> getMusics(){
		return (List<Music>) musicRepository.findAll();
	}
	
	// REST-metodi get all songs from playlist
		@GetMapping("/playlists")
		public @ResponseBody List<Playlist> getPlaylists(){
			return (List<Playlist>) playlistRepository.findAll();
		}
	
	// REST-metodi, get song by id
	@GetMapping(value="/musics/{id}")
	public @ResponseBody Optional<Music> findMusicRest (@PathVariable("id") Long musicId){
		return musicRepository.findById(musicId);
	}
	
	// REST-metodi, get song by id from playlist
	@GetMapping(value="/playlists/{id}")
	public @ResponseBody Optional<Playlist> findMusicRestPlaylist (@PathVariable("id") Long playlistId){
		return playlistRepository.findById(playlistId);
		}
	
	// REST-metodi, add new song
	@PostMapping("/musics")
	public @ResponseBody Music addNewMusic(@RequestBody Music music) {
		return musicRepository.save(music);
	}
	
	// REST-metodi, add new song to playlist
		@PostMapping("/playlists")
		public @ResponseBody Playlist addNewMusicToPlaylis(@RequestBody Playlist playlist) {
			return playlistRepository.save(playlist);
		}
	
	// REST-metodi, save new song 
   /* @RequestMapping(value="/musics", method = RequestMethod.POST)
    public @ResponseBody Music saveMusicRest(@RequestBody Music music) {	
    	return musicRepository.save(music);
    }*/
	
	// lisäys
	@RequestMapping(value="/addsong")
	public String addSong(Model model) {
		model.addAttribute("music", new Music());
		model.addAttribute("categorys", categoryRepository.findAll());
		//addAttribute("musics", musicRepository.findAll());
		return "addsong";
	}	

	// lisäys playlistiin
	@RequestMapping(value="/addsongtoplaylist")
	public String addSongToPlaylist(Model model) {
		model.addAttribute("playlist", new Playlist());
		model.addAttribute("categorys", categoryRepository.findAll());
		//model.addAttribute("playlists", playlistRepository.findAll());
		return "addsongtoplaylist";
	}
	
	// tallenna
	/*@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveSong(Music music) {
		musicRepository.save(music);
		return "redirect:musiclist";
		
	}*/
	// tallenna musiclistiin
			@RequestMapping(value="/save", method=RequestMethod.POST)
			public String saveSongToMusiclist(@Valid @ModelAttribute ("music") Music music, BindingResult bindingResult, Model model) {
				if (bindingResult.hasErrors()) { 
					log.info("error happened" + music);
					List<Music> musics = (List<Music>) musicRepository.findAll(); 
					model.addAttribute("musics", music); 
					return "erroradding";  
				} else {
				musicRepository.save(music);
				return "redirect:musiclist";
				}
			}
	
	// tallenna playlistiin
	/*@RequestMapping(value="/saveplaylist", method=RequestMethod.POST)
	public String saveSongToPlaylist(Playlist playlist) {
		playlistRepository.save(playlist);
		return "redirect:playlist";
		
	}*/
	
	// tallenna playlistiin
		@RequestMapping(value="/saveplaylist", method=RequestMethod.POST)
		public String saveSongToPlaylist(@Valid @ModelAttribute ("music") Playlist playlist, BindingResult bindingResult, Model model) {
			if (bindingResult.hasErrors()) { 
				log.info("error happened" + playlist);
				List<Playlist> playlists = (List<Playlist>) playlistRepository.findAll(); 
				model.addAttribute("playlists", playlists); 
				return "erroradding2";  
			} else {
			playlistRepository.save(playlist);
			return "redirect:playlist";
			}
		}
	
	// muokkaus
	@RequestMapping(value="edit/{id}")
	public String editSong(@PathVariable("id")Long musicId, Model model) {
		model.addAttribute("music", musicRepository.findById(musicId));
		model.addAttribute("categorys", categoryRepository.findAll());
		//model.addAttribute("playlists", playlistRepository.findAll());
		return "editmusic";
	}
	
	// muokkaus playlistiin
		@RequestMapping(value="editplaylist/{id}")
		//@PreAuthorize("hasAuthority('USER')")
		public String editSongInPlaylist(@PathVariable("id")Long playlistId, Model model) {
			model.addAttribute("playlist", playlistRepository.findById(playlistId));
			model.addAttribute("categorys", categoryRepository.findAll());
			//model.addAttribute("playlists", playlistRepository.findAll());
			return "editsonginplaylist";
		}
	
	// poisto
	@RequestMapping(value="delete/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteSong(@PathVariable("id") Long musicId, Model model) {
		musicRepository.deleteById(musicId);
		return "redirect:../musiclist";
		
	}
	
	// poisto playlististä
	@RequestMapping(value="deleteplaylist/{id}")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteSongInPlaylist(@PathVariable("id") Long playlistId, Model model) {
		playlistRepository.deleteById(playlistId);
		return "redirect:../playlist";
		
	}
	/*@RequestMapping(value="/login")
		public String login() {
			return "login";
		} */
}
