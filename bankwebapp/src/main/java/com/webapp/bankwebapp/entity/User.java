package com.webapp.bankwebapp.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String gender;
    private String dob;
    private String phone;
    private String accountType;

    private Double balance;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Transaction> transactions = new ArrayList<>();

    public User(Long id, String firstName, String lastName, String email, String password, String confirmPassword,
            String gender, String dateOfBirth, String phonenumber, String accountType) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.gender = gender;
        this.dob = dateOfBirth;
        this.phone = phonenumber;
        this.accountType = accountType;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
                + ", password=" + password + ", confirmPassword=" + confirmPassword + ", gender=" + gender
                + ", dateOfBirth=" + dob + ", Phonenumber=" + phone + ", accountType=" + accountType
                + "]";
    }

    
    

    

}
