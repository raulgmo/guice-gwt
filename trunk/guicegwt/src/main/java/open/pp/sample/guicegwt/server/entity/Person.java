package open.pp.sample.guicegwt.server.entity;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;

import com.agnie.gwt.helper.requestfactory.marker.RFEntityProxy;
import com.agnie.gwt.helper.requestfactory.marker.RFProxyMethod;

/**
 * 
 * 
 */
@Entity
@NamedQueries({
		@NamedQuery(name = "Person.getByEmailId", query = "select p from Person p where p.emailId = :emailId"),
		@NamedQuery(name = "Person.getAll", query = "select p from Person p") })
@RFEntityProxy
public class Person extends UserBase {

	@Id
	private String id;
	@Version
	private Integer version;
	@Basic
	private String title;
	@Basic
	private String fname;
	@Basic
	private String lname;
	@Basic
	private String emailId;

	@Override
	public int hashCode() {
		if (id != null)
			return id.hashCode();
		else
			return 0;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else {
			if (hashCode() != other.hashCode())
				return false;
			else if (!id.equals(other.id))
				return false;
		}
		return true;
	}

	/**
	 * @return the id
	 */
	@RFProxyMethod
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the version
	 */
	public Integer getVersion() {
		return version;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the title
	 */
	@RFProxyMethod
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	@RFProxyMethod
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the fname
	 */
	@RFProxyMethod
	public String getFname() {
		return fname;
	}

	/**
	 * @param fname
	 *            the fname to set
	 */
	@RFProxyMethod
	public void setFname(String fname) {
		this.fname = fname;
	}

	/**
	 * @return the lname
	 */
	@RFProxyMethod
	public String getLname() {
		return lname;
	}

	/**
	 * @param lname
	 *            the lname to set
	 */
	@RFProxyMethod
	public void setLname(String lname) {
		this.lname = lname;
	}

	/**
	 * @return the emailId
	 */
	@RFProxyMethod
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	@RFProxyMethod
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Person [id=" + id + ", version=" + version + ", title=" + title
				+ ", fname=" + fname + ", lname=" + lname + ", emailId="
				+ emailId + "]";
	}

}
