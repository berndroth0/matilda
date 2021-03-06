package at.wrk.controller;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import at.wrk.web.MitEinController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MitEinControllerTest {
	@Autowired
	private MitEinController mitEinController;

	//Simple test whether MitEinController isNotNull
	
	@Test
	public void contexLoads() throws Exception
	{
		assertThat(mitEinController).isNotNull();
	}
}
