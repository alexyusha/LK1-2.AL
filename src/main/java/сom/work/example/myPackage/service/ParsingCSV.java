package сom.work.example.myPackage.service;

import сom.work.example.myPackage.dict.TypeClient;
import сom.work.example.myPackage.model.Client;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ParsingCSV {
    private static final int INDEX_NUMBER = 0;
    private static final int INDEX_DATE_CONCLUSION = 1;
    private static final int INDEX_START_CONTRACT = 2;
    private static final int INDEX_FINISH_CONTRACT = 3;
    private static final int INDEX_CLIENT = 4;
    private static final int INDEX_INSURED_PEOPLE = 5;


    private static final int LENGTH_CLIENT = 3;
    private static final int LENGTH_INSURED_PEOPLE = 6;
    private static final int LENGTH_CONTRACT = 6;

    public static void parsingCsv(String filename) throws ArrayIndexOutOfBoundsException{
        List<String> contracts = new ArrayList<>();

        try {
            contracts = Files.readAllLines(Paths.get(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String str : contracts){
            String temp = str.replaceAll("\\s+", "");
            String[] tempArr = temp.split(",");

            String[] data = Arrays.copyOfRange(tempArr, 0, Math.max(tempArr.length, LENGTH_CONTRACT));

            Contract contract = new Contract.Builder()
                    .withDateConclusion(getDate(data[INDEX_DATE_CONCLUSION]))
                    .withStartContract(getDate(data[INDEX_START_CONTRACT]))
                    .withFinishContract(getDate(data[INDEX_FINISH_CONTRACT]))
                    .withClient(getClient(data[INDEX_CLIENT]))
                    .withInsuredPeople(getSetInsuredPerson(data))
                    .build();


            if (!(checkNull(data[INDEX_NUMBER]) == null)){
                contract.setNumber(Integer.parseInt(data[INDEX_NUMBER]));
            }
        }
    }

    public static String checkNull(String str){
        if (str == null || str.equals("")){
            return null;
        }
        else {
            return str;
        }
    }

    protected static List<InsuredPerson> getSetInsuredPerson(String...insuredPeople){
        List<InsuredPerson> people = new LinkedList<>();

        for (int i = INDEX_INSURED_PEOPLE; i < insuredPeople.length; i++){
            String[] temp = insuredPeople[i].split(";");
            String[] insuredPerson = Arrays.copyOfRange(temp, 0, LENGTH_INSURED_PEOPLE);

            InsuredPerson person = new InsuredPerson.Builder()
                    .withFirstName(checkNull(insuredPerson[0]))
                    .withLastName(checkNull(insuredPerson[1]))
                    .withMiddleName(checkNull(insuredPerson[2]))
                    .withBirthday(getDate(insuredPerson[3]))
                    .withINN(checkNull(insuredPerson[4]))
                    .build();


            if (!(checkNull(insuredPerson[5]) == null)){
                person.setPrice(Double.parseDouble(insuredPerson[5]));
            }

            people.add(person);
        }

        return people;
    }

    public static Client getClient(String dataClient){
        Client client;
        if (checkNull(dataClient) == null){
            client = null;
        }
        else{
            TypeClient typeClient;

            String[] temp = dataClient.split(";");
            String[] strClient = Arrays.copyOfRange(temp, 0, LENGTH_CLIENT);

            if (strClient[0].equals("INDIVIDUAL")){
                typeClient = TypeClient.INDIVIDUAL;
            }
            else if (strClient[0].equals("ENTITY")){
                typeClient = TypeClient.ENTITY;
            }
            else{
                typeClient = null;
            }

            client = new Client.Builder()
                    .withTypeClient(typeClient)
                    .withName(checkNull(strClient[1]))
                    .withAddress(checkNull(strClient[2]))
                    .build();
        }

        return client;
    }

    public static Calendar getDate(String date){
        if (checkNull(date) == null){
            return null;
        }
        else{
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            Calendar calendar = Calendar.getInstance();
            try {
                calendar.setTime(format.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return calendar;
        }
    }
}
