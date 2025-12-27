package com.example.VotingProject.in.voter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.VotingProject.in.voter.dto.EmpUpdateDTO;
import com.example.VotingProject.in.voter.entity.Emp;
import com.example.VotingProject.in.voter.exception.EmpAlreadyExistsException;
import com.example.VotingProject.in.voter.exception.NoSuchEmpExistsException;
import com.example.VotingProject.in.voter.repository.EmpRepository;

@Service
public class EmpService {
    private EmpRepository empRepo;
    @Autowired
    public EmpService(EmpRepository empRepo) {
        this.empRepo = empRepo;
    }
    public String addEmp(Emp emp){
        Emp e =empRepo.findByEName(emp.getEName()).orElse(null);
        if (e!=null) {
            throw new EmpAlreadyExistsException("Employee with " + emp.getEName() + " already exists || Reply from service layer");
        }
        empRepo.save(emp);
        return "Employee added successfully";
    }

    public Emp getEmp(Integer empId){
         Emp e =empRepo.findById(empId).orElse(null);
        if (e==null) {
            throw new NoSuchEmpExistsException("Employee with " + empId + " does not exist || Reply from service layer");
        }
        return e;
    }

    public String updateEmp(EmpUpdateDTO empDTO, Integer empId){
        Emp e = empRepo.findById(empId).orElse(null);
        if (e==null) {
            throw new NoSuchEmpExistsException("Employee with " + empId + " does not exist || Reply from service layer");
        }
        //return empRepo.save(emp);
        if (empDTO.getEname() == null && empDTO.getSal()==null) {
            throw new RuntimeException("No fields to update provided || EMpty object not allowed for updation || Reply from service layer");        
        }
        if (empDTO.getEname()!=null) {
            e.setEName(empDTO.getEname());
        }
        if (empDTO.getSal()!=null) {
            e.setSal(empDTO.getSal());
        }
        empRepo.save(e);
        return "Emp updated successfully || Reply from service layer";
        
    }
}
