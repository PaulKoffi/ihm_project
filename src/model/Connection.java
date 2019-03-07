package model;

import java.util.Objects;

public class Connection {

    /**
     * Attributes
     */
    private String mail;
    private String password;

    /**
     * Constructor
     */
    public Connection(String mail,String password) {
        this.mail = mail;
        this.password = password;
    }

    /**
     * getters
     */

    public String getmail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    /**
     * setters
     */

    public void setmail(String mail) {
        this.mail = mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Connection)) return false;
        Connection that = (Connection) o;
        return Objects.equals(getmail(), that.getmail()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getmail(), getPassword());
    }
}
