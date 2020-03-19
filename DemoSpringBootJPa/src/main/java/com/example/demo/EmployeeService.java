package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

//    public List<Employee> getAllEmployees()
//    {
//        List<Employee> employeeList = repository.findAll();
//         
//       return employeeList;
//    }
	public List<Employee> listAll() {
		return repository.findAll();
	}

	public Employee getEmployeeById(Long id) 
	{
		Optional<Employee> employee = repository.findById(id);

		if (employee.isPresent()) {
			return employee.get();
		} else {
			System.out.println("No employee record exist for given id");
			return null;
		}
	}

	public void save(Employee entity) {
		repository.save(entity);
	}

	public void delete(long id) {
		repository.deleteById(id);
	}

//    public void deleteEmployeeById(Long id)
//    {
//        Optional<Employee> employee = repository.findById(id);
//         
//        if(employee.isPresent()) 
//        {
//            repository.deleteById(id);
//        } else {
//            System.out.println("No employee record exist for given id");
//        }
//    } 

}
