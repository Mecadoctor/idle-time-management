package me.momarija.bioui.bootstrap;

import me.momarija.bioui.repos.ChantierRepo;
import me.momarija.bioui.repos.DonneeRepo;
import me.momarija.bioui.repos.EnginRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootstrapTest {

	@Autowired
	private ChantierRepo chantierRepo;

	@Autowired
	private EnginRepo enginRepo;

	@Autowired
	private DonneeRepo donneeRepo;

	@Test
	public void dataInsertTest(){
		assertEquals(1,chantierRepo.findAll().size());
		assertEquals(1,enginRepo.findAll().size());
		assertEquals(100,donneeRepo.findAll().size());
	}
}
