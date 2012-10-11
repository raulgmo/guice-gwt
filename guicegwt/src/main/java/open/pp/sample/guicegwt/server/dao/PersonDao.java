package open.pp.sample.guicegwt.server.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import open.pp.sample.guicegwt.server.entity.Person;
import open.pp.sample.guicegwt.server.injector.PersistenceLifeCycleManager;
import open.pp.sample.guicegwt.server.injector.UserPersistService;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;

public class PersonDao {
	private static org.apache.log4j.Logger log = Logger
			.getLogger(PersonDao.class);

	@Inject
	@UserPersistService
	PersistenceLifeCycleManager manager;

	@Transactional
	public boolean savePerson(Person p) {
		manager.getEntityManager().persist(p);
		return true;
	}

	@Transactional
	public boolean mergePerson(Person p) {
		manager.getEntityManager().merge(p);
		return true;
	}

	@Transactional
	public List<Person> getAllPersons() {
		List<Person> list = null;
		TypedQuery<Person> qry = manager.getEntityManager().createNamedQuery("Person.getAll",
				Person.class);
		if (qry != null) {
			list = qry.getResultList();
		}
		return list;
	}

	@Transactional
	public Person getPersonById(String id) {
		return manager.getEntityManager().find(Person.class, id);
	}

	@Transactional
	public Person getPersonByEmailId(String emailId) {
		List<Person> list = new ArrayList<Person>();
		Person p = null;
		if (emailId != null && !("".equals(emailId))) {
			TypedQuery<Person> qry = manager.getEntityManager().createNamedQuery(
					"Person.getByEmailId", Person.class);
			qry.setParameter("emailId", emailId);

			if (qry != null) {
				list = qry.getResultList();
			}
			if (list.size() > 1) {
				log.error("Multiple enteries found for emailId '" + emailId);
				return null;
			}
			if (list != null && list.size() > 0) {
				p = list.get(0);
			}
		}
		return p;
	}

	@Transactional
	public boolean removePerson(String id) {
		Person p = getPersonById(id);
		manager.getEntityManager().remove(p);
		return true;
	}
}
