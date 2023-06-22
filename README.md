# BasicProjFunctionalInterface----------------BooleanSupplierFunctionalInterface----------------------
package com.infotech;

import java.util.function.BooleanSupplier;

public class BooleanSupplierFunctionalInterfaceTest {

	public static void main(String[] args) {
		BooleanSupplier bs = ()->true;
		System.out.println(bs.getAsBoolean());
		
		int x= 100;
		int y = 70;
		
		bs =()->x<y;
		System.out.println(bs.getAsBoolean());
	}

}
-------------------Java Lambda-BiConsumer andThen-----------------------
package com.infotech.client;

import java.util.function.BiConsumer;

public class ClientTest {

	public static void main(String[] args) {

		BiConsumer<Integer, Integer> adder =(I1,I2)->System.out.println("Adder:"+(I1+I2));
		BiConsumer<Integer, Integer> multiplier =(I1,I2)->System.out.println("Multiplier:"+(I1*I2));
		
		adder.andThen(multiplier).accept(30, 50);
		System.out.println("--------------------------------------");
		multiplier.andThen(adder).accept(10, 30);

	}

}
-----------------------Java Lambda-BiFunction andThen -----------------
package com.infotech.client;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ClientTest {

	public static void main(String[] args) {
		BiFunction<String, Integer, Integer> bifunction = (designation, age) -> {
			if (designation != null && age != null) {
				if (designation.equalsIgnoreCase("Manager") && age > 30) {
					return 120000;
				} else if (designation.equalsIgnoreCase("Developer") && age > 25) {
					return 90000;
				} else {
					return 0;
				}
			} else {
				return 0;
			}
		};

		int salary = bifunction.apply("Manager", 31);
		System.out.println(salary);

		salary = bifunction.apply("Developer", 28);
		System.out.println(salary);

		Function<Integer, String> function2 = (sal) -> {
			if (sal >= 100000) {
				return "Band 5";
			} else if (sal >= 80000) {
				return "Band 4";
			} else {
				return "Band 3";
			}
		};
		// Example of andThen method
		String salaryBand = bifunction.andThen(function2).apply("Manager", 31);
		System.out.println(salaryBand);
		
		String salaryBand2 = bifunction.andThen(function2).apply("Developer", 26);
		System.out.println(salaryBand2);
	
	}

}
-----------------Java Lambda-BiFunction andThen 2----------------------
package com.infotech.client;

import java.util.function.BiFunction;
import java.util.function.Function;

public class ClientTest2 {

	public static void main(String[] args) {
		BiFunction<String, Integer, Integer> bifunction = (designation, age) -> getSalaryByAgeAndDesign(designation, age);

		int salary = bifunction.apply("Manager", 31);
		System.out.println(salary);

		salary = bifunction.apply("Developer", 28);
		System.out.println(salary);

		Function<Integer, String> function2 = sal ->getBandBasedOnSalary(sal);
		
		// Example of andThen method
		String salaryBand = bifunction.andThen(function2).apply("Manager", 31);
		System.out.println(salaryBand);
		
		String salaryBand2 = bifunction.andThen(function2).apply("Developer", 26);
		System.out.println(salaryBand2);
	}

	private static String getBandBasedOnSalary(Integer sal) {
		if (sal >= 100000) {
			return "Band 5";
		} else if (sal >= 80000) {
			return "Band 4";
		} else {
			return "Band 3";
		}
	}

	private static Integer getSalaryByAgeAndDesign(String designation, Integer age) {
		if (designation != null && age != null) {
			if (designation.equalsIgnoreCase("Manager") && age > 30) {
				return 120000;
			} else if (designation.equalsIgnoreCase("Developer") && age > 25) {
				return 90000;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
}
------------------------BinaryOperatorDefaultMethodTest-----------------
package com.infotech.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

import com.infotech.model.Employee;

public class BinaryOperatorDefaultMethodTest {

	public static void main(String[] args) {
		
		List<Employee> empList = new ArrayList<>();
		
		empList.add(new Employee("A", 30000.00, "HR"));
		empList.add(new Employee("B", 20000.00, "HR"));
		empList.add(new Employee("C", 100000.00, "HR"));
		empList.add(new Employee("D", 30000.00, "HR"));
		
		empList.add(new Employee("X", 30000.00, "Finance"));
		empList.add(new Employee("Y", 20000.00, "Finance"));
		empList.add(new Employee("X", 1000.00, "Finance"));
		empList.add(new Employee("P", 90000.00, "Finance"));
		
		Comparator<Employee> salaryComparator = Comparator.comparing(Employee::getSalary);
		
		Map<String, Optional<Employee>> maxSalByDeptMap = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
				Collectors.reducing(BinaryOperator.maxBy(salaryComparator))));
		System.out.println("Employee Max salary by department wise:::::");
		maxSalByDeptMap.forEach((dentName,emp)->{
			System.out.println(dentName);
			Employee employee = emp.get();
			System.out.println(employee);
		});
		System.out.println();
		System.out.println("Employee Min salary by department wise:::::");
		Map<String, Optional<Employee>> minSalByDeptMap = empList.stream().collect(Collectors.groupingBy(Employee::getDeptName,
				Collectors.reducing(BinaryOperator.minBy(salaryComparator))));
		
		minSalByDeptMap.forEach((dentName,emp)->{
			System.out.println(dentName);
			Employee employee = emp.get();
			System.out.println(employee);
		});
		
		
	}
}
----------------------------------Employee.java----------------------------
package com.infotech.model;

public class Employee {

	private String name;
	private Double salary;
	private String deptName;
	
	public Employee(String name, Double salary, String deptName) {
		super();
		this.name = name;
		this.salary = salary;
		this.deptName = deptName;
	}
	public String getName() {
		return name;
	}
	public Double getSalary() {
		return salary;
	}
	public String getDeptName() {
		return deptName;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", deptName=" + deptName + "]";
	}
}
-------------------------BiPredicateClientTest--------------------------
package com.infotech.client;

import java.util.function.BiPredicate;

public class BiPredicateClientTest {

	public static void main(String[] args) {
		BiPredicate<Integer, Integer> biPredicate1 =(x,y)->x>y;
		BiPredicate<Integer, Integer> biPredicate2 =(x,y)->x-2>y;
		
		System.out.println(biPredicate1.and(biPredicate2).test(20, 15));
		System.out.println(biPredicate1.and(biPredicate2).test(10, 8));
		System.out.println("----------------------------------------");
		System.out.println(biPredicate1.or(biPredicate2).test(10, 15));
		System.out.println(biPredicate1.or(biPredicate2).test(15, 8));
		
		System.out.println("----------------------------------------");
		
		boolean test = biPredicate1.negate().test(20, 18);
		System.out.println(test);

	}

}
---------------------FunctionAndThenTest 1------------------------------
package com.infotech.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.infotech.model.Employee;

public class FunctionAndThenTest {
	public static void main(String args[]) {
		Function<Employee, String> empNameFun = e -> e.getName();
		
		List<Employee> employeeList = Arrays.asList(new Employee("Tom Jones", 45), new Employee("Harry Major", 25),
				new Employee("Ethan Hardy", 65), new Employee("Nancy Smith", 15),
				new Employee("Deborah Sprightly", 29));

		Function<String, String> initialFunction = s -> s.substring(0, 1);

		List<String> empNameListInitialChar = convertEmpListToNamesList(employeeList,
				empNameFun.andThen(initialFunction));
		empNameListInitialChar.forEach(s -> {
			System.out.println(s);
		});
	}

	public static List<String> convertEmpListToNamesList(List<Employee> employeeList,
			Function<Employee, String> funcEmpToString) {
		List<String> empNameList = new ArrayList<String>();
		for (Employee emp : employeeList) {
			empNameList.add(funcEmpToString.apply(emp));
		}
		return empNameList;
	}
}
----------------------------FunctionComposeTest 2-----------------------
package com.infotech.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.infotech.model.Employee;

public class FunctionComposeTest {
	public static void main(String args[]) {
		Function<Employee, String> empNameFun = e -> e.getName();
		
		Function<Employee, Employee> funcEmpFirstName = e -> {
			int index = e.getName().indexOf(" ");
			String firstName = e.getName().substring(0, index);
			e.setName(firstName);
			return e;
		};
		List<Employee> employeeList = Arrays.asList(new Employee("Tom Jones", 45), new Employee("Harry Major", 25),
				new Employee("Ethan Hardy", 65), new Employee("Nancy Smith", 15),
				new Employee("Deborah Sprightly", 29));
		List<String> empFirstNameList = convertEmpListToNamesList(employeeList, empNameFun.compose(funcEmpFirstName));
		empFirstNameList.forEach(s -> {
			System.out.println(s);
		});
	}

	public static List<String> convertEmpListToNamesList(List<Employee> employeeList,
			Function<Employee, String> funcEmpToString) {
		List<String> empNameList = new ArrayList<String>();
		for (Employee emp : employeeList) {
			empNameList.add(funcEmpToString.apply(emp));
		}
		return empNameList;
	}
}
------------------------FunctionIdentityTest 3--------------------------
package com.infotech.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.infotech.model.Employee;

public class FunctionIdentityTest {
	public static void main(String args[]) {
		List<Employee> employeeList = Arrays.asList(new Employee("Tom Jones", 45), new Employee("Harry Major", 25),
				new Employee("Ethan Hardy", 65), new Employee("Nancy Smith", 15),
				new Employee("Deborah Sprightly", 29));
		
		List<Employee> empNameListInitials = applyIdentityToEmpList(employeeList, Function.identity());
		empNameListInitials.forEach(System.out::println);
	}

	public static List<Employee> applyIdentityToEmpList(List<Employee> employeeList,
			Function<Employee, Employee> funcEmpToEmp) {
		List<Employee> empNameList = new ArrayList<Employee>();
		for (Employee emp : employeeList) {
			empNameList.add(funcEmpToEmp.apply(emp));
		}
		return empNameList;
	}
}
--------------------------Employe.java----------------------------------
package com.infotech.model;

public class Employee {

	private String name;
	private int age;

	public Employee(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", age=" + age + "]";
	}
}
----------------------------ClientTest----------------------------------
package com.infotech.client;

import java.util.ArrayList;
import java.util.List;

import com.infotech.model.Employee;

public class ClientTest {

	public static void main(String[] args) {
		
		List<Employee> empList = new ArrayList<>();
		
		empList.add(new Employee("Raj", 90000.00, "HR"));
		empList.add(new Employee("Frank", 80000.00, "Finance"));
		empList.add(new Employee("Kishan", 30000.00, "HR"));
		empList.add(new Employee("Sean", 90000.00, "Finance"));
		empList.add(new Employee("Rajesh", 90000.00, "HR"));
		
		empList.forEach(s->System.out.println(s));
		System.out.println("------------------------------------------------");
		
		empList.removeIf(s->"HR".equalsIgnoreCase(s.getDeptName()));
		
		empList.forEach(s->System.out.println(s));
		
	}

}
--------------------------Employe.java----------------------------------
package com.infotech.model;

public class Employee {

	private String name;
	private Double salary;
	private String deptName;
	
	public Employee(String name, Double salary, String deptName) {
		this.name = name;
		this.salary = salary;
		this.deptName = deptName;
	}
	public String getName() {
		return name;
	}
	public Double getSalary() {
		return salary;
	}
	public String getDeptName() {
		return deptName;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", deptName=" + deptName + "]";
	};
}
-------------------------Collection and Iterable Interfaces-------------------
package com.infotech.client;

import java.util.ArrayList;
import java.util.List;

import com.infotech.model.Employee;

public class ListDefaultMethodsTest {

	public static void main(String[] args) {

		List<Employee> empList = new ArrayList<>();
		
		empList.add(new Employee("Raj", 90000.00, "HR"));
		empList.add(new Employee("Frank", 80000.00, "Finance"));
		empList.add(new Employee("Kishan", 30000.00, "HR"));
		empList.add(new Employee("Sean", 90000.00, "Finance"));
		empList.add(new Employee("Rajesh", 90000.00, "HR"));
		
		empList.forEach(System.out::println);
		
		System.out.println("-------------------------------");
		System.out.println("Employee sorted by salary..");
		
		empList.sort((e1,e2)->e1.getSalary()>e2.getSalary()?1:-1);
		empList.forEach(System.out::println);
		
		System.out.println("-------------------------------");
		
		empList.replaceAll(e->{
			if(e.getSalary()>60000.00){
				return e;
			}else{
				e.setSalary(e.getSalary()+e.getSalary()*0.1);
				return e;
			}
		});
		empList.forEach(System.out::println);
	}
}
-------------------------Employe.java----------------------------
package com.infotech.model;

public class Employee {

	private String name;
	private Double salary;
	private String deptName;
	
	public Employee(String name, Double salary, String deptName) {
		this.name = name;
		this.salary = salary;
		this.deptName = deptName;
	}
	public String getName() {
		return name;
	}
	public Double getSalary() {
		return salary;
	}
	public String getDeptName() {
		return deptName;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", deptName=" + deptName + "]";
	};
}
---------------------List Interface Default Methods------------
package com.infotech.client;

import java.util.ArrayList;
import java.util.List;

import com.infotech.model.Employee;

public class ListDefaultMethodsTest {

	public static void main(String[] args) {

		List<Employee> empList = new ArrayList<>();
		
		empList.add(new Employee("Raj", 90000.00, "HR"));
		empList.add(new Employee("Frank", 80000.00, "Finance"));
		empList.add(new Employee("Kishan", 30000.00, "HR"));
		empList.add(new Employee("Sean", 90000.00, "Finance"));
		empList.add(new Employee("Rajesh", 90000.00, "HR"));
		
		empList.forEach(System.out::println);
		
		System.out.println("-------------------------------");
		System.out.println("Employee sorted by salary..");
		
		empList.sort((e1,e2)->e1.getSalary()>e2.getSalary()?1:-1);
		empList.forEach(System.out::println);
		
		System.out.println("-------------------------------");
		
		empList.replaceAll(e->{
			if(e.getSalary()>60000.00){
				return e;
			}else{
				e.setSalary(e.getSalary()+e.getSalary()*0.1);
				return e;
			}
		});
		empList.forEach(System.out::println);
	}
}
---------------------Employe.java------------------------------
package com.infotech.model;

public class Employee {

	private String name;
	private Double salary;
	private String deptName;
	
	public Employee(String name, Double salary, String deptName) {
		this.name = name;
		this.salary = salary;
		this.deptName = deptName;
	}
	public String getName() {
		return name;
	}
	public Double getSalary() {
		return salary;
	}
	public String getDeptName() {
		return deptName;
	}
	
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", deptName=" + deptName + "]";
	};
}
------------Map Default Methods(getOrDefault and forEach-------
package com.infotech.client;

import java.util.HashMap;
import java.util.Map;

public class ClientTest {

	public static void main(String[] args) {
		
		Map<Integer,String> empMap = new HashMap<>();
		empMap.put(190292, "Martin");
		empMap.put(190928, "Farnk");
		empMap.put(902292, "Andrew");
		
		String result = empMap.getOrDefault(10001, "Rocky");
		System.out.println(result);
		
		System.out.println("----------------");
		
		empMap.forEach((k,v)->System.out.println(k+"\t"+v));
	}
}
-------------Map Default Methods(replaceAll and putIfAbsent)------------
package com.infotech.client;

import java.util.HashMap;
import java.util.Map;

public class ClientTest {

	public static void main(String[] args) {
		
		Map<Double,Double> map = new HashMap<>();
		map.put(2.0, 3.0);
		map.put(3.0, 3.0);
		map.put(2.0, 4.0);
		
		map.replaceAll((n1,n2)->Math.pow(n1, n2));
		
		map.forEach((x,y)->System.out.println(x+"\t"+y));
		System.out.println("---------------------");
		Map<Integer,String> empMap = new HashMap<>();
		empMap.put(190292, "Martin");
		empMap.put(190928, "Farnk");
		empMap.put(902292, "Andrew");
		
		String result = empMap.putIfAbsent(1001, "Sam");
		System.out.println(result);
		
		System.out.println(empMap.get(1001));
		
		String result2 = empMap.putIfAbsent(190928, "KK");
		System.out.println(result2);
		
		System.out.println(empMap.get(190928));
		
	}
}
---------------------ap Default Methods(remove and replace)-------------
package com.infotech.client;

import java.util.HashMap;
import java.util.Map;

public class ClientTest {

	public static void main(String[] args) {
		
		Map<Integer,String> empMap = new HashMap<>();
		empMap.put(20, "Author-A");
		empMap.put(10, "Author-B");
		empMap.put(30, "Author-C");
		
		boolean remove = empMap.remove(10, "Author-B");
		System.out.println(remove);
		
		empMap.forEach((k,v)->System.out.println(k+"\t"+v));
		
		System.out.println("-----------------------------------");
		
		boolean replace = empMap.replace(30, "Author-C", "Author-D");
		System.out.println(replace);
		
		empMap.forEach((k,v)->System.out.println(k+"\t"+v));
		
		System.out.println("-----------------------------------------------");
		
		String replace2 = empMap.replace(20, "Author-X");
		System.out.println(replace2);
		
		String replace3 = empMap.replace(200, "Author-X");
		System.out.println(replace3);
		
		
		
	}
}
------------------Map compute Default Method----------------------------
package com.infotech.client;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapComputeMethodClientTest {

	public static void main(String[] args) {
		Map<Integer, String> map = new LinkedHashMap<>();
		map.put(1, "Java");
		map.put(2, "Java");
		
		System.out.println("Original map: " + map);

		// recompute the values:
		map.compute(1, (key, oldVal) -> oldVal.concat("Script"));
		map.compute(2, (key, oldVal) -> oldVal.concat("FrameWork"));
		System.out.println("Recomputed map: " + map);

		// return "null" to remove value:
		String r1 = map.compute(1, (key, oldVal) -> null);
		System.out.println(r1);
		// null for does nothing:
		String r2 = map.compute(3, (key, oldVal) -> null);
		System.out.println(r2);
		String r3 = map.compute(30, (key, oldVal) -> "Spring Framework");
		System.out.println(r3);
		System.out.println("After null: " + map);
	}
}
-------------------Map computeIfPresent Default Method 1------------------
package com.infotech.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapComputeIfAbsentTest {

	public static void main(String[] args) {

		Map<Integer, String> techNameMap = new LinkedHashMap<>();
		techNameMap.put(1, "Java");
		techNameMap.put(2, null);
		System.out.println("Original map: " + techNameMap);

		techNameMap.computeIfAbsent(1, key -> "Java at " + key);
		System.out.println("No changes: " + techNameMap);

		techNameMap.computeIfAbsent(2, key -> ".Net at " + key);
		System.out.println("Null updated: " + techNameMap);

		techNameMap.computeIfAbsent(3, key -> "Python at " + key);
		System.out.println("New key: " + techNameMap);
		
		System.out.println("------------------------------------------");
		
		Map<String,Collection<String>> multiTechMap = new HashMap<>();
        multiTechMap.computeIfAbsent("names", key -> new ArrayList<>()).add("KK");
        System.out.println("multimap: " + multiTechMap);
 
        multiTechMap.computeIfAbsent("names", key -> new ArrayList<>()).add("PK");
        multiTechMap.computeIfAbsent("names", key -> new ArrayList<>()).add("RK");
        
        multiTechMap.computeIfAbsent("fruits", key -> new ArrayList<>()).add("Apple");
        multiTechMap.computeIfAbsent("fruits", key -> new ArrayList<>()).add("Mango");
        System.out.println("multimap: " + multiTechMap);
	}
}
--------------------Map computeIfPresent Default Method 2-----------------
package com.infotech.client;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapComputeIfPresentTest {

	public static void main(String[] args) {
		Map<Integer, String> map = new LinkedHashMap<>();
		map.put(1, "Java");
		map.put(2, "Java");
		
		System.out.println("Original map: " + map);

		// recompute the values:
		map.computeIfPresent(1, (key, oldVal) -> oldVal.concat("Script"));
		map.computeIfPresent(2, (key, oldVal) -> oldVal.concat("FrameWork"));
		System.out.println("Recomputed map: " + map);

		// return "null" to remove value:
		String r1 = map.computeIfPresent(1, (key, oldVal) -> null);
		System.out.println(r1);
		// null for does nothing:
		String r2 = map.computeIfPresent(3, (key, oldVal) -> null);
		System.out.println(r2);
		String r3 = map.computeIfPresent(30, (key, oldVal) -> "Spring Framework");
		System.out.println(r3);
		System.out.println("After null: " + map);
	}
}
----------------------Map computeIfAbsent Default Method 1----------------
package com.infotech.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapComputeIfAbsentTest {

	public static void main(String[] args) {

		Map<Integer, String> techNameMap = new LinkedHashMap<>();
		techNameMap.put(1, "Java");
		techNameMap.put(2, null);
		System.out.println("Original map: " + techNameMap);

		techNameMap.computeIfAbsent(1, key -> "Java at " + key);
		System.out.println("No changes: " + techNameMap);

		techNameMap.computeIfAbsent(2, key -> ".Net at " + key);
		System.out.println("Null updated: " + techNameMap);

		techNameMap.computeIfAbsent(3, key -> "Python at " + key);
		System.out.println("New key: " + techNameMap);
		
		System.out.println("------------------------------------------");
		
		Map<String,Collection<String>> multiTechMap = new HashMap<>();
        multiTechMap.computeIfAbsent("names", key -> new ArrayList<>()).add("KK");
        System.out.println("multimap: " + multiTechMap);
 
        multiTechMap.computeIfAbsent("names", key -> new ArrayList<>()).add("PK");
        multiTechMap.computeIfAbsent("names", key -> new ArrayList<>()).add("RK");
        
        multiTechMap.computeIfAbsent("fruits", key -> new ArrayList<>()).add("Apple");
        multiTechMap.computeIfAbsent("fruits", key -> new ArrayList<>()).add("Mango");
        System.out.println("multimap: " + multiTechMap);
	}
}
----------------------Map computeIfAbsent Default Method 2--------------
package com.infotech.client;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapComputeIfPresentTest {

	public static void main(String[] args) {
		Map<Integer, String> map = new LinkedHashMap<>();
		map.put(1, "Java");
		map.put(2, "Java");
		
		System.out.println("Original map: " + map);

		// recompute the values:
		map.computeIfPresent(1, (key, oldVal) -> oldVal.concat("Script"));
		map.computeIfPresent(2, (key, oldVal) -> oldVal.concat("FrameWork"));
		System.out.println("Recomputed map: " + map);

		// return "null" to remove value:
		String r1 = map.computeIfPresent(1, (key, oldVal) -> null);
		System.out.println(r1);
		// null for does nothing:
		String r2 = map.computeIfPresent(3, (key, oldVal) -> null);
		System.out.println(r2);
		String r3 = map.computeIfPresent(30, (key, oldVal) -> "Spring Framework");
		System.out.println(r3);
		System.out.println("After null: " + map);
	}
}
-----------------------Map merge Default Method-------------------------
package com.infotech.client;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapMergeMethodsTest {

	public static void main(String[] args) {
		Map<String, Integer> prices = new LinkedHashMap<>();
		System.out.println("Prices map: " + prices);

		prices.merge("fruits", 3, Integer::sum);
		prices.merge("fruits", 5, Integer::sum);
		System.out.println("Prices map: " + prices);

		// null removes mapping for the key:
		prices.merge("fruits", 7, (oldVal, newVal) -> {
			System.out.println("Old val:"+oldVal+","+ "new val:"+newVal);
			return null;
		});
		System.out.println("Prices map: " + prices);

		prices.put("Bread", null);
		System.out.println("Prices map: " + prices);
		// No need to handle initial null value:
		prices.merge("Bread", 42, Integer::sum);
		System.out.println("Prices map: " + prices);
	}
}
-------------------------ConcurrentHashMap in Java 8-------------------
package com.infotech.client;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapForEachClientTest {

	public static void main(String[] args) {

		ConcurrentHashMap<Integer, String> hashMap = new ConcurrentHashMap<>();
		
		for (int i = 1; i < 101; i++) {
			hashMap.put(i, "person_"+i);
		}
		
		hashMap.forEach((key,val)->{
			System.out.println("Thread:"+Thread.currentThread().getName());
			
			System.out.println(key+"\t"+val);
		});
		
		System.out.println("----------------------------------------------------------");
		
		hashMap.forEach(3,(key,val)->{
			System.out.println("Thread:"+Thread.currentThread().getName());
			
			System.out.println(key+"\t"+val);
		});
		
	}

}
