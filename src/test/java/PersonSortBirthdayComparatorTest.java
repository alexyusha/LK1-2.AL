import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonSortBirthdayComparatorTest {

    @Test
    void compare() {
       InsuredPerson person1 = new InsuredPerson("first", "c", "d",GetDate.getDate(10, 1,2002) , 6, 100.50);
       InsuredPerson person2 = new InsuredPerson("first", "c", "d",GetDate.getDate(13, 3,2002) , 6, 100.50);
       InsuredPerson person3 = new InsuredPerson("first", "c", "d",GetDate.getDate(5, 8,2001) , 6, 100.50);
       InsuredPerson person4 = new InsuredPerson("first", "c", "d",GetDate.getDate(5, 8,2004) , 6, 100.50);
       PersonSortBirthdayComparator personSortBirthdayComparator = new PersonSortBirthdayComparator();
       assertEquals(-1, personSortBirthdayComparator.compare(person1, person2));
       assertEquals(1, personSortBirthdayComparator.compare(person1, person3));
       assertEquals(-1, personSortBirthdayComparator.compare(person1, person4));

    }
}