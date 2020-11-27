package sbs.academy.data;

import sbs.academy.security.authority.UserRole;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class User {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 150)
    private String name;
    @NotEmpty
    private String age;
    @Email
    @Column(unique = true)
    private String mail;

    @Size(min = 6, max = 300)
    private String password;

    @Size(min = 6, max = 300)
    @Column(name = "confirmpassword")
    private String confrimpassword;

    @NotNull
    private String role = UserRole.SHOPPER.name();

    private String address;

    private String zipCode;

    public User() {
    }

    public User(int id, String name, String age, String mail, String password, String confrimpassword, String address, String zipCode) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.password = password;
        this.confrimpassword = confrimpassword;
        this.address = address;
        this.zipCode = zipCode;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfrimpassword() {
        return confrimpassword;
    }

    public void setConfrimpassword(String confrimpassword) {
        this.confrimpassword = confrimpassword;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

}
