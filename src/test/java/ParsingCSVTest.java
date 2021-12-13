import org.junit.jupiter.api.Test;
import сom.work.example.myPackage.service.ParsingCSV;
import сom.work.example.myPackage.util.GetDate;

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParsingCSVTest {

    @Test
    void parsingCsv() {
        /*String filename = "src\\main\\resources\\contract.csv";
        сom.work.example.myPackage.service.ParsingCSV.parsingCsv(filename);

        String contracts = "сom.work.example.myPackage.model.Contract(number=1, dateConclusion=10.9.2010, startContract=10.9.2010, finishContract=10.9.2010, client=сom.work.example.myPackage.model.Client(typeClient=INDIVIDUAL, name=fullName, address=address), insuredPeoples[сom.work.example.myPackage.model.InsuredPerson(firstName=null, lastName=Gnilitsky, middleName=Serg, birthday=10.9.2010, INN=null, price=0.0), сom.work.example.myPackage.model.InsuredPerson(firstName=Alex, lastName=Gnilitsky, middleName=Serg, birthday=10.9.2010, INN=1, price=10000.0), сom.work.example.myPackage.model.InsuredPerson(firstName=Alex, lastName=null, middleName=null, birthday=10.9.2010, INN=null, price=10000.0)])\n" +
                "сom.work.example.myPackage.model.Contract(number=2, dateConclusion=10.9.2010, startContract=10.9.2010, finishContract=10.9.2010, client=сom.work.example.myPackage.model.Client(typeClient=INDIVIDUAL, name=fullName, address=address), insuredPeoples[сom.work.example.myPackage.model.InsuredPerson(firstName=Alex, lastName=Gnilitsky, middleName=Serg, birthday=10.9.2010, INN=null, price=10000.0)])\n" +
                "сom.work.example.myPackage.model.Contract(number=3, dateConclusion=10.9.2010, startContract=10.9.2010, finishContract=10.9.2010, client=сom.work.example.myPackage.model.Client(typeClient=INDIVIDUAL, name=fullName, address=address), insuredPeoples[сom.work.example.myPackage.model.InsuredPerson(firstName=Alex, lastName=Gnilitsky, middleName=Serg, birthday=10.9.2010, INN=null, price=10000.0)])\n";

        StringBuilder stringBuilder = new StringBuilder();
        for (сom.work.example.myPackage.model.Contract contract : сom.work.example.myPackage.model.Contract.allContracts){
                stringBuilder.append(contract).append("\n");
        }

        assertEquals(contracts, stringBuilder.toString());*/
    }

    @Test
    void checkNull() {
        assertEquals("text", ParsingCSV.checkNull("text"));
        assertNull(ParsingCSV.checkNull(""));
        assertNull(ParsingCSV.checkNull(null));
    }

    @Test
    void getSetInsuredPerson() {
        //?
        /*String line = "1,10-10-2010,10-10-2010,10-10-2010,INDIVIDUAL;fullName;address,Alex;Gnilitsky;Serg;10-10-2010;1;10000.0,;Gnilitsky;Serg;10-10-2010;1,Alex;;;10-10-2010;;10000.0";
        String[] data = line.split(",");
        Set<сom.work.example.myPackage.model.InsuredPerson> getSet = сom.work.example.myPackage.service.ParsingCSV.getSetInsuredPerson(data);
        List<сom.work.example.myPackage.model.InsuredPerson> list = new ArrayList<>();
        list.addAll(getSet);

        String ins = "сom.work.example.myPackage.model.InsuredPerson(firstName=Alex, lastName=null, middleName=null, birthday=10.9.2010, INN=null, price=10000.0)\n" +
                "сom.work.example.myPackage.model.InsuredPerson(firstName=Alex, lastName=Gnilitsky, middleName=Serg, birthday=10.9.2010, INN=null, price=10000.0)\n" +
                "сom.work.example.myPackage.model.InsuredPerson(firstName=null, lastName=Gnilitsky, middleName=Serg, birthday=10.9.2010, INN=null, price=0.0)";

        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < list.size();i++){
            stringBuilder.append(list.get(i));
            if (!(i == list.size()-1)){
                stringBuilder.append("\n");
            }
        }

        assertEquals(ins, stringBuilder.toString());
*/
    }

    @Test
    void getClient() {
        /*String dataClient = "INDIVIDUAL;Alex Gnilitsky;address";
        String dataClient1 = "";
        String dataClient2 = ";Alex Gnilitsky;address";
        String dataClient3 = ";Alex Gnilitsky;";

        Client client = new Client(TypeClient.INDIVIDUAL, "Alex Gnilitsky", "address");
        Client client2 = new Client.Builder().withName("Alex Gnilitsky").withAddress("address").build();
        Client client3 = new Client.Builder().withName("Alex Gnilitsky").build();

        assertNull(ParsingCSV.getClient(dataClient1));
        assertNull(ParsingCSV.getClient(null));
        assertEquals(client.toString(), ParsingCSV.getClient(dataClient).toString());
        assertEquals(client2.toString(), ParsingCSV.getClient(dataClient2).toString());
        assertEquals(client3.toString(), ParsingCSV.getClient(dataClient3).toString());

        assertEquals(client.getTypeClient(), ParsingCSV.getClient(dataClient).getTypeClient());
        assertEquals(client.getName(), ParsingCSV.getClient(dataClient).getName());
        assertEquals(client.getAddress(), ParsingCSV.getClient(dataClient).getAddress());*/
    }

    @Test
    void getDate() {
        Calendar calendar = GetDate.getDate(10, 10, 2010);
        assertEquals(calendar.get(Calendar.DAY_OF_MONTH), ParsingCSV.getDate("10-10-2010").get(Calendar.DAY_OF_MONTH));
        assertEquals(calendar.get(Calendar.MONTH), ParsingCSV.getDate("10-10-2010").get(Calendar.MONTH));
        assertEquals(calendar.get(Calendar.YEAR), ParsingCSV.getDate("10-10-2010").get(Calendar.YEAR));
        assertNull(ParsingCSV.getDate(""));
        assertNull(ParsingCSV.getDate(null));
    }
}