package com.example.VotingProject.in.voter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.VotingProject.in.voter.dto.EmpUpdateDTO;
import com.example.VotingProject.in.voter.entity.Emp;
import com.example.VotingProject.in.voter.service.EmpService;

import jakarta.validation.Valid;

@RestController  //indicates that the class is a controller where every method returns a domain object instead of a view  ( view = spring MVC, thymeleaf or something. but we are building REST API's here)
@RequestMapping("/api/emp")  //base URL for all endpoints in this controller 
public class EmpController {

    private EmpService serv;

    @Autowired //used for automatic dependency injection of the EmpService bean into the EmpController class 
    public EmpController(EmpService serv) {
        this.serv = serv;
    }

    @PostMapping("/add")                        //maps HTTP POST requests to /api/emp/add to this method
    public String addEmp(@Valid @RequestBody Emp emp) { //@RequestBody binds the HTTP request body (of JSON format) to the Emp object // @Valid is used to validate the request body against the constraints defined in the Emp entity class
        return serv.addEmp(emp);                //calls the addEmp method of the EmpService class to add a new employee
    } 

    @GetMapping("/{empId}")  //maps HTTP GET requests to /api/emp/get/{empId} to this method
    public Emp getEmp(@PathVariable("empId")Integer empId) { //@PathVariable binds the emp parameter from the URL to the Emp object
        return serv.getEmp(empId);
    }

    @PutMapping("/update/{empId}")  //maps HTTP POST requests to /api/emp/update/{empId} to this method
    public String updateEmp(@Valid @RequestBody EmpUpdateDTO empDTO, @PathVariable("empId") Integer empId) { //@RequestBody binds the HTTP request body (of JSON format) to the Emp object // @Valid is used to validate the request body against the constraints defined in the Emp entity class
        return serv.updateEmp(empDTO, empId);
    }

   
}
