package сom.work.example.myPackage.service;

import сom.work.example.myPackage.dict.GetDate;
import сom.work.example.myPackage.dict.SortType;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContractServices {

    public static double allSum(Contract contract){
        double scale = Math.pow(10, 2);
        double sum = contract.getInsuredPeoples().stream().map(InsuredPerson::getPrice).mapToDouble(Double::doubleValue).sum();
        return Math.ceil(sum * scale) / scale;
    }

    public static void sort(Set<InsuredPerson> list, SortType type){
        List<InsuredPerson> insuredPeople = new ArrayList<>(list);
        if (SortType.ALPHABET.equals(type)){
            PersonSortAlphabetComparator personSortAlphabetComparator = new PersonSortAlphabetComparator();
            insuredPeople.sort(personSortAlphabetComparator);
            insuredPeople.sort(personSortAlphabetComparator);

        }
        else{
            PersonSortBirthdayComparator personSortBirthdayComparator = new PersonSortBirthdayComparator();
            insuredPeople.sort(personSortBirthdayComparator);

        }
        for (InsuredPerson person : insuredPeople){
            System.out.println(person.fullName() + " " + person.getBirthday().getTime() + " " + person.getINN());
        }
    }

    public static InsuredPerson searchPerson(String INN) throws SQLException {
        InsuredPerson person = null;
        String sql = "SELECT first_name, last_name, middle_name, birthday, INN, price FROM insured_people WHERE INN = ?";
        Connection connection = CRUD.get();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, INN);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                person = new InsuredPerson.Builder()
                        .withFirstName(set.getString("first_name"))
                        .withLastName(set.getString("last_name"))
                        .withMiddleName(set.getString("middle_name"))
                        .withBirthday(GetDate.getDate(set.getDate("birthday").toString()))
                        .withINN(set.getString("INN"))
                        .withPrice(set.getDouble("price")).build();
            }
            set.close();
            connection.close();
            statement.close();



        /*InsuredPerson p = null;
        for (InsuredPerson person1 : InsuredPerson.allInsuredPeople){
            if (INN.equals(person1.getINN())){
                p = person1;
            }
        }*/
        return person;
    }

    public static  int checkUniqueness(int number) throws SQLException {
            String sql = "SELECT number FROM contracts WHERE number = ?";
            Connection connection = CRUD.get();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, number);
            ResultSet set = statement.executeQuery();
            while (set.next()){
                if (set.getInt("number") == number){
                    number++;
                    System.out.println(set.getInt("number"));
                    return checkUniqueness(number);
                }


            }
            connection.close();
            set.close();
        statement.close();
        System.out.println("Номер контракта -" + number);

        return number;

/*
        for (Contract i : Contract.allContracts){
            if (number == i.getNumber()){
                number++;
                return checkUniqueness(number);
            }
        }
        System.out.println("Номер контракта -" + number);
        return number;*/
    }
}
