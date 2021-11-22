package сom.work.example.myPackage.service;

import org.junit.jupiter.api.Test;
import сom.work.example.myPackage.dict.TypeFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParsingJsonTest {

    @Test
    void readFilesJson() throws IOException {
        /*ParsingJson parsingJson = new ParsingJson();
        List<Contract> list = parsingJson.readFilesJson();


        List<InsuredPerson> insuredPersonList = new LinkedList<>();
        List<InsuredPerson> insuredPersonList1 = new LinkedList<>();
        Client client = new Client.Builder().
                withTypeClient(TypeClient.INDIVIDUAL)
                .withName("Alex Gnilitsky")
                .withAddress("address")
                .build();
        Client client1 = new Client.Builder().
                withTypeClient(TypeClient.INDIVIDUAL)
                .withName("Alex Gnilitsky")
                .withAddress("address")
                .build();


        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("firstName")
                .withLastName("lastName")
                .withMiddleName("middleName")
                .withBirthday(GetDate.getDate(10, 10,2010))
                .withINN("1")
                .withPrice(10000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("firstName1")
                .withLastName("lastName1")
                .withMiddleName("middleName1")
                .withBirthday(GetDate.getDate(10, 10,2010))
                .withINN("2")
                .withPrice(10000.0)
                .build());
        insuredPersonList1.add(new InsuredPerson.Builder()
                .withFirstName("firstName")
                .withLastName("lastName")
                .withMiddleName("middleName")
                .withBirthday(GetDate.getDate(10, 10,2010))
                .withINN("1")
                .withPrice(10000.0)
                .build());
        insuredPersonList1.add(new InsuredPerson.Builder()
                .withFirstName("firstName1")
                .withLastName("lastName1")
                .withMiddleName("middleName1")
                .withBirthday(GetDate.getDate(10, 10,2010))
                .withINN("2")
                .withPrice(10000.0)
                .build());

        Contract contract = new Contract.Builder()
                .withNumber(1)
                .withDateConclusion(GetDate.getDate(10, 10,20110))
                .withStartContract(GetDate.getDate(10, 10,2010))
                .withFinishContract(GetDate.getDate(10, 10,2010))
                .withClient(client)
                .withInsuredPeople(insuredPersonList)
                .build();
        Contract contract1 = new Contract.Builder()
                .withNumber(1)
                .withDateConclusion(GetDate.getDate(10, 10,20110))
                .withStartContract(GetDate.getDate(10, 10,2010))
                .withFinishContract(GetDate.getDate(10, 10,2010))
                .withClient(client1)
                .withInsuredPeople(insuredPersonList1)
                .build();


        assertEquals(list.get(0).toString(), contract.toString());
*/
    }

    @Test
    void searchFile() {
        String paths = "src";
        String filenameJson = "F:\\Project\\JAVA IDEA\\task_work\\LK_1_AL\\src\\main\\resources\\contract.json";
        String filenameCsv = "F:\\Project\\JAVA IDEA\\task_work\\LK_1_AL\\src\\main\\resources\\contract.csv";


        Set<File> setJson = ParsingJson.searchFile(paths, TypeFile.JSON);

        Set<File> setCSV = ParsingJson.searchFile(paths, TypeFile.CSV);

        for (File x : setJson){
            assertEquals(filenameJson, x.toString());
        }

        for (File x1 : setCSV){
            assertEquals(filenameCsv, x1.toString());
        }


}

}