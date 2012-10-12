package open.pp.sample.guicegwt.server.service;

import java.util.List;

import open.pp.sample.guicegwt.server.dao.AddressDao;
import open.pp.sample.guicegwt.server.dao.PersonDao;
import open.pp.sample.guicegwt.server.entity.Address;
import open.pp.sample.guicegwt.server.entity.Person;

import com.google.inject.Inject;
import com.google.inject.Injector;

public class PersonService {

	@Inject
	private Injector injector;

	public String registerPerson(Person p) {
		String userId = java.util.UUID.randomUUID().toString();
		p.setId(userId);
		PersonDao pd = injector.getInstance(PersonDao.class);
		pd.savePerson(p);
		return p.getId();
	}

	public Person getPersonById(String personId) {
		PersonDao pd = injector.getInstance(PersonDao.class);
		return pd.getPersonById(personId);
	}

	public Address getAddressById(String addressId) {
		AddressDao ad = injector.getInstance(AddressDao.class);
		return ad.getAddressById(addressId);
	}

	public List<Person> getAllPersons() {
		PersonDao pd = injector.getInstance(PersonDao.class);
		return pd.getAllPersons();
	}

	public List<Address> getAllAddressesOfAPerson(String personId) {
		AddressDao ad = injector.getInstance(AddressDao.class);
		return ad.getAllAddresssByPersonId(personId);
	}

	public String saveAddresss(String personId, Address address) {
		address.setId(java.util.UUID.randomUUID().toString());
		address.setPersonId(personId);
		AddressDao ad = injector.getInstance(AddressDao.class);
		ad.saveAddress(address);
		return address.getId();
	}

}
