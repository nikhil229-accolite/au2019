package cm.accolite.au.jaxb;



import javax.xml.bind.annotation.*;

@XmlRootElement
public class Employee  {
	
	private int id;
	private String firstName;
	private String lastName;
	private Address address;
	
	public Employee()
	{
		super();
	}
	
	@XmlElement
	public Address getAddress() {
		return address;
	}
	
 
	public void setAddress(Address address) {
		this.address = address;
	}

	public Employee(int id, String firstName, String lastName, Address address) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
	}
	@XmlAttribute
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	@XmlElement
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", address=" + address
				+ "]";
	}
	

	
	

}
