import сom.work.example.myPackage.dao.ClientDao;
import сom.work.example.myPackage.dao.ContractDao;
import сom.work.example.myPackage.dao.InsuredPersonDao;
import сom.work.example.myPackage.util.GetDate;
import сom.work.example.myPackage.dict.TypeClient;
import сom.work.example.myPackage.model.Client;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;
import сom.work.example.myPackage.service.ParsingJson;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        List<InsuredPerson> insuredPersonList = new LinkedList<>();

        Client client = new Client.Builder()
                .withTypeClient(TypeClient.INDIVIDUAL)
                .withName("fullName")
                .withAddress("address")
                .build();

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

        Contract contract1 = new Contract.Builder()
                .withNumber(1)
                .withDateConclusion(GetDate.getDate(10,10,2010))
                .withStartContract(GetDate.getDate(10,10,2010))
                .withFinishContract(GetDate.getDate(10,10,2010))
                .withClient(client)
                .withInsuredPeople(insuredPersonList)
                .build();


        ParsingJson parsingJson = new ParsingJson();
        System.out.println(parsingJson.readFilesJson());
        Map<String, Object> update = new HashMap<>();

        ContractDao contractDao = new ContractDao();
         /* update.put("start_contract", GetDate.getDateForDB(10,10,2020));
        update.put("number", 10);
        update.put

        contractDao.create(contract1);
        System.out.println(contractDao.read(1));
        System.out.println(contractDao.read(10));
        contractDao.delete(2);
        contractDao.update(1,update);*/
        ClientDao clientDao = new ClientDao(0);
        clientDao.setNumberContract(1);
        /*update.put("name", "XXXX");
        clientDao.create(client);
        System.out.println(clientDao.read(1));
        clientDao.update(1, update);
        clientDao.delete(1);*/

        InsuredPersonDao insuredPersonDao = new InsuredPersonDao(0);
        insuredPersonDao.setNumberContract(1);
        insuredPersonDao.createAll(insuredPersonList);
        System.out.println(insuredPersonDao.read("2"));
        //System.out.println(insuredPersonDao.readAll("2", "1","3"));
        insuredPersonDao.delete("5");
        update.put("price", 10000.5);
        update.put("id", 1000);
        insuredPersonDao.update("4", update);
        //insuredPersonDao.deleteAll(1);







    }





}
