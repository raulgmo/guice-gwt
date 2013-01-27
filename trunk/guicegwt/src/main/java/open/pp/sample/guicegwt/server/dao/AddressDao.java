package open.pp.sample.guicegwt.server.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import open.pp.sample.guicegwt.server.entity.Address;
import open.pp.sample.guicegwt.server.injector.AddressPersistService;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

@Singleton
public class AddressDao {

	@Inject
	@AddressPersistService
	PersistenceLifeCycleManager	manager;
	@Inject
	Provider<EntityManager>		em;

	@Transactional
	public boolean saveAddress(Address a) {
		em.get().persist(a);
		return true;
	}

	@Transactional
	public boolean mergeAddress(Address a) {
		em.get().merge(a);
		return true;
	}

	@Transactional
	public List<Address> getAllAddresssByPersonId(String personId) {
		List<Address> list = null;
		TypedQuery<Address> qry = em.get().createNamedQuery("Address.getByPersonId", Address.class);
		qry.setParameter("personId", personId);
		if (qry != null) {
			list = qry.getResultList();
		}
		return list;
	}

	@Transactional
	public Address getAddressById(String id) {
		return em.get().find(Address.class, id);
	}

	@Transactional
	public boolean removeAddress(String id) {
		Address a = getAddressById(id);
		if (a != null) {
			em.get().remove(a);
			return true;
		}
		return false;
	}
}
