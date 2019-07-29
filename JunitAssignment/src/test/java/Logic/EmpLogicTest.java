package Logic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pojo.Employee;

public class EmpLogicTest {
	EmpLogic empLogicObj = new EmpLogic();
	private static EmpLogic empBusinessLogic = new EmpLogic();
	private static Employee employee = new Employee();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		employee.setName("Rajeev");
		employee.setAge(28);
		employee.setSalary(8000);
	

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	
	

	// test to check appraisal
	@SuppressWarnings("deprecation")
	@Test
	public void testCalculateAppriasal() {
		assertEquals(500, empLogicObj.calculateAppraisal(employee),0);
		}
	
	
	@Test
	public void testCalculateAppraisal() {
		assertNotEquals(1000, empLogicObj.calculateAppraisal(employee),0);
		}
	
	@Test
	public void calculateYearlySalary()
	{
		assertEquals(employee.getSalary()*12,empLogicObj.calculateYearlySalary(employee),0);
	}
	

	@Test
	public void testIsAllowedToSmoke()
	{
		assertFalse(employee.getAge() < 18);
	}
	
	@Test
	public void testIsAllowedToMarry()
	{
		assertTrue(employee.getAge() > 25);
		//asser
	}
	
	
	
	

}
