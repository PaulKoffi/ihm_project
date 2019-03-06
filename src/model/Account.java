package model;

import java.util.Objects;

public class Account {
    /**
     * Attributes
     */
    private String firstName;
    private String name;
    private String email;
    private String password;
    private double salary;

    /**
     * Constructor
     */
    public Account(String firstName, String name, String email, String password, double salary) {
        this.firstName = firstName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.salary = salary;
    }

    /*Getters*/

    public String getFirstName() {
        return firstName;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public double getSalary() {
        return salary;
    }

    /*Setters*/

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(email, account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
