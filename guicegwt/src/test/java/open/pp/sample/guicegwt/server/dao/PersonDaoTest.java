package open.pp.sample.guicegwt.server.dao;

import java.util.List;

import javax.persistence.EntityManager;

import junit.framework.Assert;
import open.pp.sample.guicegwt.server.entity.Person;
import open.pp.sample.guicegwt.server.injector.GuiceTestRunner;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;
import open.pp.sample.guicegwt.server.injector.TestUserPersistModule;
import open.pp.sample.guicegwt.server.injector.UserPersistService;
import open.pp.sample.guicegwt.server.injector.WithModules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Injector;

@RunWith(GuiceTestRunner.class)
@WithModules({ TestUserPersistModule.class })
public class PersonDaoTest {
	@Inject
	protected Injector injector;

	@Inject
	@UserPersistService
	protected PersistenceLifeCycleManager manager;

	public EntityManager getEntityManager() {
		return manager.getEntityManager();
	}

	@Before
	public void before() {
		manager.beginUnitOfWork();
	}

	@After
	public void after() {
		manager.endUnitOfWork();
	}

	@Test
	public void saveTest() {
		Person p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailId("pandurang@gmail.com");
		p.setTitle("Mr");
		p.setId(java.util.UUID.randomUUID().toString());
		PersonDao pd = injector.getInstance(PersonDao.class);
		pd.savePerson(p);

		Person actual = pd.getPersonById(p.getId());
		Assert.assertEquals(p, actual);
	}

	@Test
	public void getAll() {
		Person p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailId("pandurang@gmail.com");
		p.setTitle("Mr");
		p.setId(java.util.UUID.randomUUID().toString());
		PersonDao pd = injector.getInstance(PersonDao.class);
		pd.savePerson(p);

		List<Person> all = pd.getAllPersons();
		Assert.assertNotNull(all);
		Assert.assertTrue(all.size() >= 1);
	}

	@Test
	public void getPersonByEmailIdTest() {
		Person p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailId(java.util.UUID.randomUUID().toString() + "@gmail.com");
		p.setTitle("Mr");
		p.setId(java.util.UUID.randomUUID().toString());
		PersonDao pd = injector.getInstance(PersonDao.class);
		pd.savePerson(p);

		Person actual = pd.getPersonByEmailId(p.getEmailId());
		Assert.assertEquals(p, actual);
	}

	@Test
	public void removeTest() {
		Person p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailId("pandurang@gmail.com");
		p.setTitle("Mr");
		p.setId(java.util.UUID.randomUUID().toString());
		PersonDao pd = injector.getInstance(PersonDao.class);
		pd.savePerson(p);

		Person actual = pd.getPersonById(p.getId());
		Assert.assertEquals(p, actual);
		pd.removePerson(p.getId());

		actual = pd.getPersonById(p.getId());
		Assert.assertNull(actual);
	}
}
