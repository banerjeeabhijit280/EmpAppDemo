package com.example.VotingProject.in.voter.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.VotingProject.in.voter.entity.Emp;

@Repository  //indicates that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects
public interface EmpRepository extends JpaRepository<Emp, Integer> {  //JpaRepository is a JPA specific extension of Repository
    public Optional <Emp> findByEName(String ename);  //custom method to find employee by name
//JpaRepository provides JPA related methods such as flushing the persistence context and delete records in a batch
}//interface ends
//repository ends
//JpaRepository<EntityClass, PrimaryKeyType>
//here EntityClass is Emp and PrimaryKeyType is Integer
//Optional is a container object which may or may not contain a non-null value
//If a value is present, isPresent() will return true and get() will return the value
//If no value is present, the object is considered empty and isPresent() will return false
//This is used to avoid null pointer exceptions
//Custom method findByEname will generate a query to find an employee by their name
//Spring Data JPA will automatically implement this method based on its name
//JpaRepository provides methods like save(), findById(), findAll(), deleteById() etc.
//@Repository annotation is used to indicate that the class is a repository and will be used to interact with the database
//This interface will be used in the service layer to perform CRUD operations on Emp entity
//No need to write implementation class for this interface as Spring Data JPA will provide the implementation at runtime
