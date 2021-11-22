import org.junit.jupiter.api.Test;
import сom.work.example.myPackage.dict.GetDate;
import сom.work.example.myPackage.model.InsuredPerson;
import сom.work.example.myPackage.service.PersonSortAlphabetComparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonSortAlphabetComparatorTest {

    @Test
    void compare() {
        InsuredPerson person1 = new InsuredPerson.Builder()
                .withFirstName("irst")
                .withLastName("a")
                .withMiddleName("middle")
                .withBirthday(GetDate.getDate(10, 1,2003))
                .withINN("1")
                .withPrice(5000.0)
                .build();
        InsuredPerson person2 = new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName( "b")
                .withMiddleName("iddle")
                .withBirthday(GetDate.getDate(1, 1,2004))
                .withINN("2")
                .withPrice(8000.0)
                .build();
        InsuredPerson person3 =  new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("d")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(30, 6,2001))
                .withINN("4")
                .withPrice(1000.0)
                .build();
        InsuredPerson person4 = new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("e")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(26, 1,2002))
                .withINN("5")
                .withPrice(0)
                .build();
        InsuredPerson person5 = new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("e")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(26, 1,2002))
                .withINN("5")
                .withPrice(0)
                .build();
        PersonSortAlphabetComparator personSortAlphabetComparator = new PersonSortAlphabetComparator();
        assertEquals(-1, personSortAlphabetComparator.compare(person1, person2));
        assertEquals(1, personSortAlphabetComparator.compare(person4, person3));
        assertEquals(0, personSortAlphabetComparator.compare(person4, person5));
    }
}