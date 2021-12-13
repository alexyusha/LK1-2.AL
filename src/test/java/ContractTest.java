import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import сom.work.example.myPackage.util.GetDate;
import сom.work.example.myPackage.dict.TypeClient;
import сom.work.example.myPackage.model.Client;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;
import сom.work.example.myPackage.service.ContractServices;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContractTest {
    private static Contract contract1;

    @SneakyThrows
    @BeforeAll
    public static void setUp() {
        List<InsuredPerson> insuredPersonList = new LinkedList<>();
        Client client = new Client.Builder().
                withTypeClient(TypeClient.INDIVIDUAL)
                .withName("fullName")
                .withAddress("address")
                .build();


        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("irst")
                .withLastName("a")
                .withMiddleName("middle")
                .withBirthday(GetDate.getDate(10, 1, 2003))
                .withINN("1")
                .withPrice(5000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("b")
                .withMiddleName("iddle")
                .withBirthday(GetDate.getDate(1, 1, 2004))
                .withINN("2")
                .withPrice(8000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("d")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(30, 6, 2001))
                .withINN("4")
                .withPrice(1000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("e")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(26, 1, 2002))
                .withINN("5")
                .withPrice(0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("c")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(13, 3, 2002))
                .withINN("6")
                .withPrice(100.50)
                .build());

        contract1 = new Contract.Builder()
                .withNumber("111")
                .withDateConclusion(GetDate.getDate(26, 1, 2002))
                .withStartContract(GetDate.getDate(26, 1, 2002))
                .withStartContract(GetDate.getDate(26, 1, 2003))
                .withClient(client)
                .withInsuredPeople(insuredPersonList)
                .build();

    }

    @Test
    void allSum() {
        assertEquals(14100.50, ContractServices.allSum(contract1));
    }

    @Test
    void getFullNumberForPrint() {
        assertEquals("I0000111-t", contract1.getNumber());
    }

}