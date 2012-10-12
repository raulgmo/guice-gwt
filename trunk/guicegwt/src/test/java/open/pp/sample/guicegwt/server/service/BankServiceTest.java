/**
 * 
 */
package open.pp.sample.guicegwt.server.service;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import open.pp.sample.guicegwt.server.entity.Address;
import open.pp.sample.guicegwt.server.entity.Person;
import open.pp.sample.guicegwt.server.injector.AddressPersistService;
import open.pp.sample.guicegwt.server.injector.GuiceTestRunner;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;
import open.pp.sample.guicegwt.server.injector.TestAddressPersistModule;
import open.pp.sample.guicegwt.server.injector.TestUserPersistModule;
import open.pp.sample.guicegwt.server.injector.UserPersistService;
import open.pp.sample.guicegwt.server.injector.WithModules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * @author pandurang
 * 
 */
@RunWith(GuiceTestRunner.class)
@WithModules({ TestAddressPersistModule.class, TestUserPersistModule.class })
public class BankServiceTest {
	@Inject
	protected Injector injector;

	@Inject
	@AddressPersistService
	protected PersistenceLifeCycleManager accountManager;
	@Inject
	@UserPersistService
	protected PersistenceLifeCycleManager userManager;

	@Before
	public void before() {
		userManager.beginUnitOfWork();
		accountManager.beginUnitOfWork();
	}

	@After
	public void after() {
		accountManager.endUnitOfWork();
		userManager.endUnitOfWork();
	}

	@Test
	public void registerPerson() {
		Person p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailId("pandurang@gmail.com");
		p.setTitle("Mr");
		PersonService bs = injector.getInstance(PersonService.class);
		bs.registerPerson(p);
	}

	@Test
	public void addAddressTest() {
		Person p = new Person();
		p.setFname("Pandurang");
		p.setLname("Patil");
		p.setEmailId("pandurang@gmail.com");
		p.setTitle("Mr");
		PersonService bs = injector.getInstance(PersonService.class);
		String personId = bs.registerPerson(p);
		Assert.assertNotNull(personId);
		Address add = new Address();
		add.setCity("Kolhapur");
		List<Address> adds = new ArrayList<Address>();
		boolean result = bs.saveAddresss(personId, adds);
		Assert.assertTrue(result);
	}

}
