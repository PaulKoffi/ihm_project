package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    @Test
    void equals() {
        Activity a = new Activity("activity");
        Activity b = new Activity("activity");
        Activity c = new Activity("i am not an activity");

        assertTrue(a.equals(b)); //Macthing

        assertFalse(a.equals(c)); //Not matching
    }

}