import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContractTest {

  public static Set<InsuredPerson> insuredPersonList = new HashSet<>();
    Client client = new Client(TypeClient.INDIVIDUAL, "fullName", "address");

    static {
        insuredPersonList.add(new InsuredPerson("irst", "a", "middle",GetDate.getDate(10, 1,2003) , 1, 5000.0));
        insuredPersonList.add(new InsuredPerson("first", "b", "iddle",GetDate.getDate(1, 1,2004) , 2, 8000.0));
        insuredPersonList.add(new InsuredPerson("first", "d", "d",GetDate.getDate(30, 6,2001) , 4, 1000.0));
        insuredPersonList.add(new InsuredPerson("first", "e", "d", GetDate.getDate(26, 1,2002), 5, 0));
        insuredPersonList.add(new InsuredPerson("first", "c", "d",GetDate.getDate(13, 3,2002) , 6, 100.50));
    }

    Contract contract1 = new Contract(1, GetDate.getDate(26, 1,2002), GetDate.getDate(26, 1,2002), GetDate.getDate(26, 1,2002), client, insuredPersonList);


    @Test
    void allSum() {
        assertEquals(14100.50, contract1.allSum());
    }

   /* @Test
    void sort() {
        TreeSet<InsuredPerson> set = contract1.sort(insuredPersonList, sortType.ALPHABET);
        List<InsuredPerson> list1 = new ArrayList<>();
        list1.add(new InsuredPerson("irst", "a", "middle",GetDate.getDate(10, 1,2003) , 1, 5000.0));
        list1.add(new InsuredPerson("first", "b", "iddle",GetDate.getDate(1, 1,2004) , 2, 8000.0));
        list1.add(new InsuredPerson("first", "c", "d",GetDate.getDate(13, 3,2002) , 6, 100.50));
        list1.add(new InsuredPerson("first", "d", "d",GetDate.getDate(30, 6,2001) , 4, 1000.0));
        list1.add(new InsuredPerson("first", "e", "d", GetDate.getDate(26, 1,2002), 5, 0));

        int index = -1;
        for (InsuredPerson person : set){
            index++;
            System.out.println(index);
            System.out.println(list1.get(index).fullName());
            System.out.println(person.fullName());

            //assertEquals(list1.get(index).fullName(), person.fullName());

        }

        contract1.sort(insuredPersonList, sortType.BIRTHDAY);
        List<InsuredPerson> list2 = new ArrayList<>();
        list2.add(new InsuredPerson("first", "d", "d",GetDate.getDate(30, 6,2001) , 4, 1000.0));
        list2.add(new InsuredPerson("first", "e", "d", GetDate.getDate(26, 1,2002), 5, 0));
        list2.add(new InsuredPerson("first", "c", "d",GetDate.getDate(13, 3,2002) , 6, 100.50));
        list2.add(new InsuredPerson("irst", "a", "middle",GetDate.getDate(10, 1,2003) , 1, 5000.0));
        list2.add(new InsuredPerson("first", "b", "iddle",GetDate.getDate(1, 1,2004) , 2, 8000.0));
        int index1 = 0;
        for (InsuredPerson person : insuredPersonList){
            assertEquals(list1.get(index1).fullName(), person.fullName());
            index1++;
        }
    }*/
    @Test
    void searchPerson() {
        InsuredPerson person = new InsuredPerson("first", "c", "d",GetDate.getDate(13, 3,2002) , 6, 100.50);
        assertEquals(person.getINN(), contract1.searchPerson(6).getINN());
        assertEquals(null, contract1.searchPerson(10));
    }

}