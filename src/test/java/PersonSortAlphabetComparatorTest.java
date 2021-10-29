import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonSortAlphabetComparatorTest {

    @Test
    void compare() {
        InsuredPerson person1 = new InsuredPerson("first", "a", "d",GetDate.getDate(10, 1,2002) , 6, 100.50);
        InsuredPerson person2 = new InsuredPerson("first", "b", "d",GetDate.getDate(13, 3,2002) , 6, 100.50);
        InsuredPerson person3 = new InsuredPerson("first", "c", "d",GetDate.getDate(5, 8,2001) , 6, 100.50);
        InsuredPerson person4 = new InsuredPerson("first", "d", "d",GetDate.getDate(5, 8,2004) , 6, 100.50);
        PersonSortAlphabetComparator personSortAlphabetComparator = new PersonSortAlphabetComparator();
        assertEquals(-1, personSortAlphabetComparator.compare(person1, person2));
        assertEquals(1, personSortAlphabetComparator.compare(person4, person3));
        assertEquals(0, personSortAlphabetComparator.compare(person4, person4));
        //assertEquals(-1, personSortAlphabetComparator.compare(person1, person3));

    }
}