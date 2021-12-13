package сom.work.example.myPackage.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import сom.work.example.myPackage.dao.ClientDao;
import сom.work.example.myPackage.dao.ContractDao;
import сom.work.example.myPackage.dao.InsuredPersonDao;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;
import сom.work.example.myPackage.util.GetContracts;
import сom.work.example.myPackage.util.GetDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ContractDBServiceTest {
    private static ContractDBService contractDBService;
    @SneakyThrows
    @BeforeAll
    public static void setUp() {
        ContractDao contractDao = new ContractDao();
        ClientDao clientDao = new ClientDao();
        InsuredPersonDao insuredPerson = new InsuredPersonDao();
        contractDBService = new ContractDBService(contractDao, clientDao, insuredPerson);
    }

    @Test
    void createContract() {
        Contract contract1 = GetContracts.getContract1();
        contractDBService.createContract(contract1);
        Contract contract = contractDBService.readContract(contract1);
        assertEquals(contract.toString(), contract1.toString());
    }

    @Test
    void readContract() {
        Contract contract1 = GetContracts.getContract1();
        contractDBService.createContract(contract1);
        Contract contract = contractDBService.readContract(contract1);
        assertEquals(contract.toString(), contract1.toString());
    }

    @Test
    void updateContract() {
        Contract contract2 = GetContracts.getContract2();
        contract2.getClient().setName("ALrcknvitn");
        contract2.getClient().setAddress("");
        contract2.getInsuredPeoples().add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("c")
                .withMiddleName("dfgndsgn")
                .withBirthday(GetDate.getDate(13, 3, 2010))
                .withINN("99")
                .withPrice(100.50)
                .build());
        contractDBService.updateContract(contract2);
        Contract contract = contractDBService.readContract(contract2);
        assertEquals(contract.toString(), contract2.toString());
    }

    @Test
    void deleteContract() {
        Contract contract1 = GetContracts.getContract1();
        contractDBService.deleteContract(contract1);
        Contract contract = contractDBService.readContract(contract1);
        assertNull(contract);
    }
}