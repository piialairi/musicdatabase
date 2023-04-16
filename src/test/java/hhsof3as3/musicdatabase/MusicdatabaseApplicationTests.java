package hhsof3as3.musicdatabase;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import hhsof3as3.musicdatabase.webController.MusicController;

//@RunWith(SpringRunner.class) // JUnit 4
@ExtendWith(SpringExtension.class) //JUnit 5(Jupiter
@SpringBootTest
class MusicdatabaseApplicationTests {
	@Autowired
	private MusicController musicController;

	@Test
	void contextLoads() {
		assertThat(musicController).isNotNull();
	}

}
