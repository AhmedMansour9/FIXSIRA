package com.example.ic.fixera.Model;

/**
 * Created by ic on 9/26/2018.
 */

public class UserRegister {

    private String FirstName,LastName,Phone,Email,password,ConFirmpassword,Gender,id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConFirmpassword() {
        return ConFirmpassword;
    }

    public void setConFirmpassword(String conFirmpassword) {
        ConFirmpassword = conFirmpassword;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }


}
