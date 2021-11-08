import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ContractTest {

    public static Set<InsuredPerson> insuredPersonList = new HashSet<>();
    public  static Client client = new Client.Builder().
            withTypeClient(TypeClient.INDIVIDUAL)
            .withName("fullName")
            .withAddress("address")
            .build();

    static {
        Contract.allContracts.remove(Contract.allContracts);
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("irst")
                .withLastName("a")
                .withMiddleName("middle")
                .withBirthday(GetDate.getDate(10, 1,2003))
                .withINN("1")
                .withPrice(5000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName( "b")
                .withMiddleName("iddle")
                .withBirthday(GetDate.getDate(1, 1,2004))
                .withINN("2")
                .withPrice(8000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("d")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(30, 6,2001))
                .withINN("4")
                .withPrice(1000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("e")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(26, 1,2002))
                .withINN("5")
                .withPrice(0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("c")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(13, 3,2002))
                .withINN("6")
                .withPrice(100.50)
                .build());

    }

    public static Contract contract1 = new Contract.Builder()
            .withNumber(1)
            .withDateConclusion(GetDate.getDate(26, 1,2002))
            .withStartContract(GetDate.getDate(26, 1,2002))
            .withStartContract(GetDate.getDate(26, 1,2002))
            .withClient(client)
            .withInsuredPeople(insuredPersonList)
            .build();

    @Test
    void allSum() {
        assertEquals(14100.50, contract1.allSum());
    }

    @Test
    void searchPerson() {
        assertEquals(100.5, contract1.searchPerson("6").getPrice());
        assertNull(contract1.searchPerson("10"));

    }

    @Test
    void getFullNumberForPrint(){
        assertEquals("I0000001-5", contract1.getFullNumberForPrint());
    }

    @Test
    void checkUniqueness(){
        Contract contract2 = new Contract.Builder().withNumber(1).build();
        assertEquals(2, contract2.getNumber());
    }


}