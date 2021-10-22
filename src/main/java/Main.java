import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<InsuredPerson> insuredPersonList = new ArrayList<>();
        Client client = new Client(TypeClient.INDIVIDUAL, "fullName", "address");
        for (int i = 0; i < 10; i++){
            insuredPersonList.add(new InsuredPerson("first" ,"ast", "iddle", "01.01.2004","000002", (Math.random() * 10000.0)));
        }

        Contract contract1 = new Contract(1, "10.10.2010" ,"10.10.2010", "10.10.2020", client, insuredPersonList);
        System.out.println(contract1.getNumber());
        System.out.println(contract1.getDateConclusion());
        System.out.println(contract1.getStartContract());
        System.out.println(contract1.getFinishContract());
        System.out.println(contract1.getClient().getName());
        for (InsuredPerson person : insuredPersonList){
            System.out.println(person.fullName() + " " + person.getPrice());
        }
        System.out.println(contract1.allSum());
        /*insuredPersonList.add(new InsuredPerson("irst" ,"last", "middle", "10.01.2003","000001", (Math.random() * 10000.0)));
        insuredPersonList.add(new InsuredPerson("first" ,"ast", "iddle", "01.01.2004","000002", (Math.random() * 10000.0)));
        insuredPersonList.add(new InsuredPerson("first" ,"ti", "d", "26.01.2002","000003", (Math.random() * 10000.0)));
        insuredPersonList.add(new InsuredPerson("first" ,"ti", "d", "13.03.2002","000003", (Math.random() * 10000.0)));
        insuredPersonList.add(new InsuredPerson("first" ,"ti", "d", "30.06.2001","000003", (Math.random() * 10000.0)));
        Contract contract1 = new Contract(1, "10.10.2010" ,"10.10.2010", "10.10.2020", client, insuredPersonList);
       contract1.sort(insuredPersonList, sortType.ALPHABET);*/

    }
}
