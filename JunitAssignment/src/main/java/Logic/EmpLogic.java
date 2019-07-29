package Logic;

import pojo.Employee;

public class EmpLogic {
	   // Calculate the yearly salary of employee
	   public double calculateYearlySalary(Employee employee) {
	      double yearlySalary = 0;
	      yearlySalary = employee.getSalary() * 12;
	      return yearlySalary;
	   }
		
	   // Calculate the appraisal amount of employee
	   public double calculateAppraisal(Employee employee) {
	      double appraisal = 0;
			
	      if(employee.getSalary() < 10000){
	         appraisal = 500;
	      }else{
	         appraisal = 1000;
	      }
			
	      return appraisal;
	   }
	   
	   
	   public boolean isAllowedToSmoke(Employee e)
	   {
		   return e.getAge()>18 ? true : false; 
	   }
	   
	   public boolean isAllowedToMarry(Employee e)
	   {
		   return e.getAge() > 25 ?true : false;
	   }

}
