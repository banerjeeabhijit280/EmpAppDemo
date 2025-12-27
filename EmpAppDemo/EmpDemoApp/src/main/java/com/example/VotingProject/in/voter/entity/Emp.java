package com.example.VotingProject.in.voter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //specifies that the class is an entity and is mapped to a database table
@Data //auto generate getter setter toString hashcode equals
@AllArgsConstructor //generates a constructor with 1 parameter for each field in your class
@NoArgsConstructor //generates a constructor with no parameters
public class Emp {
    @Id  //specifies the primary key of an entity
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //provides for the specification of generation strategies for the values of primary keys
    private Integer empId; //primary key

    @NotBlank(message = "Employee name cannot be blank, is required || Reply from entity layer") //validation to ensure that the field is not null or empty
    private String EName; //employee name

    @NotNull(message = "Employee salary cannot be null, is required || Reply from entity layer") //validation to ensure that the field is not null
    @Min(value = 10000, message = "Employee salary must be atleast ₹10000 || Reply from entity layer") //validation to ensure that the field is positive
    private Double sal; //employee salary
} //class ends
//entity ends
//@Entity annotation is used to mark this class as a JPA entity
//@Data annotation is a convenient shortcut that bundles the features of @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor together
//@AllArgsConstructor generates a constructor with parameters for all fields
//@NoArgsConstructor generates a default constructor with no parameters
//@Id annotation is used to define the primary key
//@GeneratedValue annotation is used to specify the primary key generation strategy
//GenerationType.IDENTITY indicates that the persistence provider must assign primary keys for the entity using database identity columns
//Here empId is the primary key of type String
//eName is the name of the employee
//sal is the salary of the employee
//This class will be used to create Emp objects which will be stored in the database
//The fields of this class will be mapped to the columns of the database table
//The Emp entity will be used in the repository and service layers to perform CRUD operations
//on Emp objects
