
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<InsuredPerson> insuredPersonList = new HashSet<>();
        Client client = new Client(TypeClient.INDIVIDUAL, "fullName", "address");
       /* for (int i = 0; i < 10; i++){
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
        System.out.println(contract1.allSum());*/
        insuredPersonList.add(new InsuredPerson("irst" ,"last", "middle", GetDate.getDate(10,10,2010),1, (Math.random() * 10000.0)));
        insuredPersonList.add(new InsuredPerson("first" ,"ast", "iddle", GetDate.getDate(20,2,2001),2, (Math.random() * 10000.0)));
        insuredPersonList.add(new InsuredPerson("firs" ,"ti5", "d6", GetDate.getDate(10,10,2010),3, (Math.random() * 10000.0)));
        insuredPersonList.add(new InsuredPerson("fir" ,"ti", "d4", GetDate.getDate(10,10,2010),4, (Math.random() * 10000.0)));
        insuredPersonList.add(new InsuredPerson("first" ,"t5i", "d", GetDate.getDate(10,10,2010),5, (Math.random() * 10000.0)));
        Contract contract1 = new Contract(1, GetDate.getDate(10,10,2010) ,GetDate.getDate(10,10,2010), GetDate.getDate(10,10,2010), client, insuredPersonList);
       /*for (InsuredPerson person : insuredPersonList){
           System.out.println(person);
       }*/
        contract1.sort(insuredPersonList, sortType.ALPHABET);




    }
}
