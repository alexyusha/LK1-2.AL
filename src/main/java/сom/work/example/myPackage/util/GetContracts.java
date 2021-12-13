package сom.work.example.myPackage.util;

import сom.work.example.myPackage.dict.TypeClient;
import сom.work.example.myPackage.model.Client;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GetContracts {
    public static Contract getContract1() {
        List<InsuredPerson> insuredPersonList = new LinkedList<>();

        Client client = new Client.Builder()
                .withTypeClient(TypeClient.INDIVIDUAL)
                .withName("fullName")
                .withAddress("")
                .withINN("10")
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

        Contract contract = new Contract.Builder()
                .withNumber("1")
                .withDateConclusion(GetDate.getDate(10, 10, 2010))
                .withStartContract(GetDate.getDate(10, 10, 2010))
                .withFinishContract(GetDate.getDate(10, 10, 2020))
                .withClient(client)
                .withInsuredPeople(insuredPersonList)
                .build();

        return contract;
    }

    public static Contract getContract2() {
        List<InsuredPerson> insuredPersonList = new LinkedList<>();

        Client client = new Client.Builder()
                .withTypeClient(TypeClient.INDIVIDUAL)
                .withName("fullName")
                .withAddress("ads")
                .withINN("11")
                .build();

        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("irst")
                .withLastName("a")
                .withMiddleName("middle")
                .withBirthday(GetDate.getDate(10, 1, 2003))
                .withINN("25")
                .withPrice(5000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("b")
                .withMiddleName("iddle")
                .withBirthday(GetDate.getDate(1, 1, 2004))
                .withINN("26")
                .withPrice(8000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("d")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(30, 6, 2001))
                .withINN("41")
                .withPrice(1000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("e")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(26, 1, 2002))
                .withINN("50")
                .withPrice(0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("c")
                .withMiddleName("d")
                .withBirthday(GetDate.getDate(13, 3, 2002))
                .withINN("64")
                .withPrice(100.50)
                .build());

        Contract contract = new Contract.Builder()
                .withNumber("2")
                .withDateConclusion(GetDate.getDate(10, 10, 2010))
                .withStartContract(GetDate.getDate(10, 10, 2010))
                .withFinishContract(GetDate.getDate(10, 10, 2020))
                .withClient(client)
                .withInsuredPeople(insuredPersonList)
                .build();

        return contract;
    }

    public static Contract getContract3() {
        List<InsuredPerson> insuredPersonList = new LinkedList<>();

        Client client = new Client.Builder()
                .withTypeClient(TypeClient.INDIVIDUAL)
                .withName("fullName")
                .withAddress("ads")
                .withINN("1")
                .build();

        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("ist")
                .withLastName("avj")
                .withMiddleName("mddle")
                .withBirthday(GetDate.getDate(15, 1, 2003))
                .withINN("25")
                .withPrice(5000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("fist")
                .withLastName("b")
                .withMiddleName("ddle")
                .withBirthday(GetDate.getDate(10, 1, 2004))
                .withINN("26")
                .withPrice(8000.0)
                .build());
        insuredPersonList.add(new InsuredPerson.Builder()
                .withFirstName("first")
                .withLastName("c")
                .withMiddleName("dfgndsgn")
                .withBirthday(GetDate.getDate(13, 3, 2010))
                .withINN("99")
                .withPrice(100.50)
                .build());

        Contract contract = new Contract.Builder()
                .withNumber("2")
                .withDateConclusion(GetDate.getDate(10, 10, 2010))
                .withStartContract(GetDate.getDate(10, 10, 2010))
                .withFinishContract(GetDate.getDate(10, 10, 2020))
                .withClient(client)
                .withInsuredPeople(insuredPersonList)
                .build();

        return contract;
    }

    public static List<Contract> getListContract() {
        List<Contract> contracts = new ArrayList<>();
        contracts.add(getContract1());
        contracts.add(getContract2());
        contracts.add(getContract3());

        return contracts;
    }
}
