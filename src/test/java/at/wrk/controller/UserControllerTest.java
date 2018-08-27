package at.wrk.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import at.wrk.repository.DummyUserRepository;
import at.wrk.web.UserController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { UserController.class, DummyUserRepository.class })
@EnableConfigurationProperties
@ActiveProfiles("test")
public class UserControllerTest {

	@Before
	public void setUp() throws Exception {
	}

    @Autowired
    private UserController user;
    
    @Test
    public void loginTest() throws Exception {
    	//user.setUserRepository(new DummyUserRepository());
    	user.deleteUser(1);
    }
}