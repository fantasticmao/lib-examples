package cn.fantasticmao.demo.java.database.elasticsearch;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * Account
 *
 * @author fantasticmao
 * @since 2021/12/22
 */
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Account {
    private Integer accountNumber; // type: integer
    private Long balance; // type: long
    private String firstname; // type: keyword
    private String lastname; // type: keyword
    private Integer age; // type: integer
    private String gender; // type: keyword
    private String address; // type: text
    private String employer; // type: keyword
    private String email; // type: text
    private String city; // type: keyword
    private String state; // type: keyword

    public Account() {
    }

    // getter and setter

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
