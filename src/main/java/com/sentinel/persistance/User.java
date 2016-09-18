package com.sentinel.persistance;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GenericGenerator(name="users_id_seq" , strategy="increment")
    @Column(name = "id")
    public Integer id;

    @Column(name = "email")
    public String email;

    @Column(name = "first_name")
    public String firstName;

    @Column(name = "last_name")
    public String lastName;

    @Column(name = "password")
    public String password;

    public User(Integer id, String email, String firstName, String last_name, String password) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = last_name;
        this.password = password;
    }

    public User() {
    }
}
