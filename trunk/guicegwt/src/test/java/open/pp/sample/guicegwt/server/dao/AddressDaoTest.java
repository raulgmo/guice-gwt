package open.pp.sample.guicegwt.server.dao;

import java.util.List;

import junit.framework.Assert;
import open.pp.sample.guicegwt.server.entity.Address;
import open.pp.sample.guicegwt.server.injector.AddressPersistService;
import open.pp.sample.guicegwt.server.injector.GuiceTestRunner;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;
import open.pp.sample.guicegwt.server.injector.TestAddressPersistModule;
import open.pp.sample.guicegwt.server.injector.WithModules;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.google.inject.Inject;
import com.google.inject.Injector;

@RunWith(GuiceTestRunner.class)
@WithModules({ TestAddressPersistModule.class })
public class AddressDaoTest {
	@Inject
	protected Injector						injector;

	@Inject
	@AddressPersistService
	protected PersistenceLifeCycleManager	manager;

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
		Address a = new Address();
		a.setPersonId(java.util.UUID.randomUUID().toString());
		a.setId(java.util.UUID.randomUUID().toString());
		a.setCity("Pune");
		AddressDao ad = injector.getInstance(AddressDao.class);
		ad.saveAddress(a);

		Address actual = ad.getAddressById(a.getId());
		Assert.assertEquals(a, actual);
	}

	@Test
	public void getAccountByPersonIdTest() {
		String personId = java.util.UUID.randomUUID().toString();
		Address a = new Address();
		a.setPersonId(personId);
		a.setId(java.util.UUID.randomUUID().toString());
		a.setCity("Mumbai");
		AddressDao ad = injector.getInstance(AddressDao.class);
		ad.saveAddress(a);

		Address a1 = new Address();
		a1.setCity("Pune");
		a1.setPersonId(personId);
		a1.setId(java.util.UUID.randomUUID().toString());
		ad.saveAddress(a1);

		List<Address> list = ad.getAllAddresssByPersonId(a1.getPersonId());
		Assert.assertNotNull(list);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void removeTest() {
		Address a = new Address();
		a.setCity("Mumbai");
		a.setPersonId(java.util.UUID.randomUUID().toString());
		a.setId(java.util.UUID.randomUUID().toString());
		AddressDao ad = injector.getInstance(AddressDao.class);
		ad.saveAddress(a);

		Address actual = ad.getAddressById(a.getId());
		Assert.assertEquals(a, actual);
		ad.removeAddress(a.getId());

		actual = ad.getAddressById(a.getId());
		Assert.assertNull(actual);
	}
}
