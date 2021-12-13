import org.junit.jupiter.api.Test;
import сom.work.example.myPackage.util.GetDate;
import сom.work.example.myPackage.model.InsuredPerson;

import static org.junit.jupiter.api.Assertions.*;

class InsuredPersonTest {
    public static InsuredPerson person = new InsuredPerson.Builder()
            .withFirstName("Alex")
            .withLastName("Gnilitsky")
            .withMiddleName("Serg")
            .withBirthday(GetDate.getDate(26, 1, 2002))
            .withINN("9")
            .withPrice(0)
            .build();
    public static InsuredPerson person1 = new InsuredPerson.Builder()
            .withFirstName(null)
            .withLastName("Gnilitsky")
            .withMiddleName("Serg")
            .withBirthday(GetDate.getDate(26, 1, 2002))
            .withINN("9")
            .withPrice(0)
            .build();

    @Test
    void fullName() {
        assertEquals("Gnilitsky A.S.", person.fullName());
        assertNull(person1.fullName());
    }

}