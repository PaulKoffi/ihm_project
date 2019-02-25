package model;

import java.util.Objects;

public class Account {
    /**
     * Attributes
     */
    private String firstName;
    private String famillyName;
    private String email;
    private String password;

    /**
     * Constructor
     */
    public Account(String firstName, String famillyName, String email, String password) {
        this.firstName = firstName;
        this.famillyName = famillyName;
        this.email = email;
        this.password = password;
    }

    /*Getters*/

    public String getFirstName() {
        return firstName;
    }

    public String getFamillyName() {
        return famillyName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    /*Setters*/

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setFamillyName(String famillyName) {
        this.famillyName = famillyName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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
