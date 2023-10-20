package net.javaguides.springbootbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springbootbackend.repository.EmployeeRepository;
import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.exception.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class EmployeeController {
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public List<Employee> getAllemployees()
    {
    	return employeeRepository.findAll();
    }
    
    @PostMapping("/employees")
    public Employee createEmployee(@RequestBody Employee e)
    {
    	return employeeRepository.save(e);
    }
    
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Employee not exist with id :" + id));
		return ResponseEntity.ok(employee);
	}
    @PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResouceNotFoundException("Employee not exist with id :" + id));
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		employeeRepository.save(employee);
		return ResponseEntity.ok(employee);
	}
    
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long id)
    {
    	Employee employee = employeeRepository.findById(id).orElseThrow(() -> new ResouceNotFoundException("Employee not exist with id :" + id));
    	employeeRepository.delete(employee);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
