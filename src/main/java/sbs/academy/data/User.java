package sbs.academy.data;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    private String mail;
    @Size(min = 6, max = 300)
    private String password;

    private String address;
    private String zipCode;

    public User() {
    }

    public User(int id, String name, String age, String mail, String password, String address, String zipCode) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.mail = mail;
        this.password = password;
        this.address = address;
        this.zipCode = zipCode;
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
