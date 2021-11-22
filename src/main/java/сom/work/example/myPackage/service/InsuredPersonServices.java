package сom.work.example.myPackage.service;

import сom.work.example.myPackage.model.InsuredPerson;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsuredPersonServices extends InsuredPerson{

   public static String checkINN(String INN) throws SQLException {
       String tempINN = INN;
       String sql = "SELECT INN FROM insured_people WHERE INN = ?";
       Connection connection = CRUD.get();
           PreparedStatement statement = connection.prepareStatement(sql);
           statement.setString(1, INN);
           ResultSet set = statement.executeQuery();
           while (set.next()){
               if (set.getString("INN").equals(INN))
               System.out.println("Человек с таким ИНН существует, проверьте введенный данные");
               tempINN = null;
           }
           set.close();
           connection.close();
           statement.close();

       return tempINN;
/*
        String tempINN = INN;
        if (INN != null && INN.length() == 1){
            for (InsuredPerson person : allInsuredPeople){
                if (INN.equals(person.getINN())){
                    System.out.println("Человек с таким ИНН существует, проверьте введенный данные");
                    tempINN = null;
                    break;
                }
            }
        }
        else{
            System.out.println("Проверьте введенные данные");
            tempINN = null;
        }
       return tempINN;*/

    }
}
