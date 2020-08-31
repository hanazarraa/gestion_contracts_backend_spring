package com.luv2code.ecommerce.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long user_id;
    @Column(name = "firstname")
    private String firstname;
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

	@Column(name = "lastname")
    private String lastname;


    @Column(name = "username")
    private String username;


    @Column(name = "password")
    private String password;

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public User(long user_id,String firstname,String lastname, String username, String password) {
        this.user_id = user_id;
        this.firstname=firstname;
        this.lastname=lastname;
        this.username = username;
        this.password = password;
    }
    public User (){}

    public void setPassword(String password) {
        this.password = password;
    }
}
