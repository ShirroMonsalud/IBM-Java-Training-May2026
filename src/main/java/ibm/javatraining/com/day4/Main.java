package ibm.javatraining.com.day4;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		List<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee("Alice", "IT", 55000));
        employees.add(new Employee("Bob", "Finance", 60000));
        employees.add(new Employee("Alice", "HR", 50000)); // duplicate name
        employees.add(new Employee("Ken", "IT", 60000));
        employees.add(new Employee("Maria", "HR", 52000));
        employees.add(new Employee("John", "Finance", 70000));
        employees.add(new Employee("Ken", "Finance", 65000)); // duplicate name
        employees.add(new Employee("Lara", "IT", 62000));
        employees.add(new Employee("Sam", "HR", 48000));
        employees.add(new Employee("Bob", "IT", 59000)); // duplicate name
        
        
        List<Employee> uniqueEmployees = new ArrayList<>();
        Set<String> dupClean = new HashSet<>();
        
        for (Employee emp : employees)
        {
        	if(!dupClean.contains(emp.getName()))
        	{
        		
        		dupClean.add(emp.getName());
        		uniqueEmployees.add(emp);
        	}
        }
        
        
        System.out.println("=== Unique Employees ===");
        for (Employee emp : uniqueEmployees)
        {
        	System.out.println(emp);
        }
        
        Map<String, List<Employee>> departmentGroup = new HashMap<>();
        for (Employee emp : employees)
        {
        	String dept = emp.getDepartment();
        	if(!departmentGroup.containsKey(dept))
        	{
        		departmentGroup.put(dept, new ArrayList<>());
        	}
        	departmentGroup.get(dept).add(emp);
        }
        
        System.out.println("\n=== Employees By Department ===");
        
        for (String dept : departmentGroup.keySet())
        {
        	System.out.println(dept + ": ");
        	for(Employee emp : departmentGroup.get(dept))
        	{
        		System.out.println(" - " + emp);
        	}
        }
        
        System.out.println("\n=== Highest Paid per Department ===");
        for (String dept : departmentGroup.keySet())
        {
        	List<Employee> highList = departmentGroup.get(dept);
        	
        	Employee highest = highList.get(0);
        	
        	for (Employee emp : highList)
        	{
        		if (emp.getSalary() > highest.getSalary())
        		{
        			highest = emp;
        		}
        	}
        	System.out.println(dept + ": " + highest);
        }
        
        System.out.println("\n=== Employees Sorted by Salary (Desc) ===");
        employees.sort(Comparator.comparing(Employee::getSalary).reversed());
        for (Employee emp : employees)
        {
        	System.out.println(emp);
        }
        
        System.out.println("\n=== Unique Salaries (Sorted) ===");
        Set<Double> uniqueSalary = new TreeSet<>();
        for (Employee emp : employees)
        {
        	if(!uniqueSalary.contains(emp.getSalary()))
        	{
        		uniqueSalary.add(emp.getSalary());
        	}
        	
        }
        
        for (Double salary : uniqueSalary)
        {
        	System.out.println(salary);
        }
 
        
	}

}
