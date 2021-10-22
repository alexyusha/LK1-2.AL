import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ContractTest {
    public static List<InsuredPerson> insuredPersonList = new ArrayList<>();
    Client client = new Client(TypeClient.INDIVIDUAL, "fullName", "address");

    static {
        insuredPersonList.add(new InsuredPerson("irst", "a", "middle", "10.01.2003", "000001", 5000.0));
        insuredPersonList.add(new InsuredPerson("first", "b", "iddle", "01.01.2004", "000002", 8000.0));
        insuredPersonList.add(new InsuredPerson("first", "d", "d", "26.01.2002", "000004", 1000.0));
        insuredPersonList.add(new InsuredPerson("first", "e", "d", "13.03.2002", "000005", 0));
        insuredPersonList.add(new InsuredPerson("first", "c", "d", "30.06.2001", "000006", 100.50));
    }

    Contract contract1 = new Contract(1, "10.10.2010", "10.10.2010", "10.10.2020", client, insuredPersonList);


    @Test
    void allSum() {
        assertEquals(14100.50, contract1.allSum());
    }

    @Test
    void sort() {
        contract1.sort(insuredPersonList, sortType.ALPHABET);
        List<InsuredPerson> list1 = new ArrayList<>();
        list1.add(new InsuredPerson("irst", "a", "middle", "10.01.2003", "000001", 5000.0));
        list1.add(new InsuredPerson("first", "b", "iddle", "01.01.2004", "000002", 8000.0));
        list1.add(new InsuredPerson("first", "c", "d", "30.06.2001", "000003", 100.50));
        list1.add(new InsuredPerson("first", "d", "d", "26.01.2002", "000004", 1000.0));
        list1.add(new InsuredPerson("first", "e", "d", "13.03.2002", "000005", 0));

        for (int i = 0; i < list1.size(); i++) {
            assertEquals(list1.get(i).fullName(), insuredPersonList.get(i).fullName());
        }

        contract1.sort(insuredPersonList, sortType.BIRTHDAY);
        List<InsuredPerson> list2 = new ArrayList<>();
        list2.add(new InsuredPerson("first", "c", "d", "30.06.2001", "000006", 100.50));
        list2.add(new InsuredPerson("first", "d", "d", "26.01.2002", "000005", 1000.0));
        list2.add(new InsuredPerson("first", "e", "d", "13.03.2002", "000004", 0));
        list2.add(new InsuredPerson("irst", "a", "middle", "10.01.2003", "000001", 5000.0));
        list2.add(new InsuredPerson("first", "b", "iddle", "01.01.2004", "000002", 8000.0));
        for (int i = 0; i < list2.size(); i++) {
            assertEquals(list2.get(i).getBirthday(), insuredPersonList.get(i).getBirthday());
        }
    }
    @Test
    void searchPerson() {
        assertEquals(insuredPersonList.get(0).getINN(), contract1.searchPerson("000006").getINN());
        assertEquals(null, contract1.searchPerson("000010"));
    }

}