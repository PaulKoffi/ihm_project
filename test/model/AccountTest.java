package model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AccountTest {

    @Test
    void equals() {
        Account a = new Account("smith","djamoura","sd@gmail.com","1234",1000);
        Account b = new Account("rachel","dubois","sd@gmail.com","1234",2000);
        Account c = new Account("rachel","dubois","ds@gmail.com","1234",2000);
        Account d = new Account("rachel","dubois","sd@gmail.com","2134",2000);
        Account e = new Account("rachel","dubois","ds@gmail.com","2134",2000);

        assertTrue(a.equals(b)); //Both the adress and the email matching
       // assertFalse(a.equals(c)); //Email not matching
       // assertFalse(a.equals(d)); //Password not matching
        assertFalse(a.equals(e)); //Both the adress and the email not matching

    }
}