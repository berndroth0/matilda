package at.wrk.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.wrk.web.LagerstandortController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LagerstandortControllerTest {
	@Autowired
	private LagerstandortController lagerstandortController;

	//Simple test whether MainController isNotNull
	
	@Test
	public void contexLoads() throws Exception
	{
		assertThat(lagerstandortController).isNotNull();
	}
}