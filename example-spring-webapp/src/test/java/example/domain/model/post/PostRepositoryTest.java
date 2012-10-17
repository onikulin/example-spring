package example.domain.model.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import example.TestGroups;

@Test(groups = { TestGroups.DB })
@ContextConfiguration(locations = "classpath:/META-INF/spring/testContext-infrastructure-jpa.xml")
@Profile("test")
public class PostRepositoryTest extends
		AbstractTransactionalTestNGSpringContextTests {

	@Autowired
	Post postRepository;

	@BeforeMethod
	public void setUp() {
	}

}