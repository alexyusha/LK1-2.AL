import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;
import java.util.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Contract {
    public static Set<Contract> allContracts = new LinkedHashSet<>();
    @Positive
    private int number;
    @FutureOrPresent
    @JsonDeserialize(using = CalendarDeserializer.class)
    private Calendar dateConclusion;
    @Future
    @JsonDeserialize(using = CalendarDeserializer.class)
    private Calendar startContract;
    @Future
    @JsonDeserialize(using = CalendarDeserializer.class)
    private Calendar finishContract;
    @NotNull
    @JsonDeserialize(using = ClientDeserializer.class)
    private Client client;
    @NotEmpty
    @JsonDeserialize(using = InsuredPersonDeserializer.class)
    @JsonProperty("insuredPeoples")
    private Set<InsuredPerson> insuredPeoples;

    public static class Builder{
        private Contract newContract;

        public Builder(){
            newContract = new Contract();
        }

        public Builder withNumber(int number){
            newContract.number = newContract.checkUniqueness(number);
            return this;
        }

        public Builder withDateConclusion(Calendar dateConclusion){
            newContract.dateConclusion = dateConclusion;
            return this;
        }

        public Builder withStartContract(Calendar startContract){
            newContract.startContract = startContract;
            return this;
        }

        public Builder withFinishContract(Calendar finishContract){
            newContract.finishContract = finishContract;
            return this;
        }

        public Builder withClient(Client client){
            newContract.client = client;
            return this;
        }

        public Builder withInsuredPeople(Set<InsuredPerson> insuredPeople){
            newContract.insuredPeoples = insuredPeople;
            return this;
        }

        public Contract build(){
            allContracts.add(newContract);
            return newContract;
        }
    }

    public double allSum(){
        double scale = Math.pow(10, 2);
        double sum = traversal5(getInsuredPeoples());
        return Math.ceil(sum * scale) / scale;
    }

    /*private double traversal1(List<InsuredPerson> list){
        double sum = 0;

        for (int i = 0; i < list.size(); i++){
            sum += list.get(i).getPrice();
        }

        return sum;
    }*/

    private double traversal1(Set<InsuredPerson> list){
        double sum = 0;
        Object[] person = list.toArray();
        for (int i = 0; i < person.length; i++){
            InsuredPerson per = (InsuredPerson)person[i];
            sum += per.getPrice();
        }

        return sum;
    }

    private double traversal2(Set<InsuredPerson> list){
        double sum = 0;

        for (InsuredPerson person : list){
            sum += person.getPrice();
        }

        return sum;
    }

    private double traversal3(Set<InsuredPerson> list){
        double sum = 0;

        for(Iterator<InsuredPerson> iter = list.iterator(); iter.hasNext();){
            sum += iter.next().getPrice();
        }

        return sum;
    }

    /*private double traversal4(List<InsuredPerson> list){
        double sum = 0;

        for(ListIterator<InsuredPerson> iter = list.listIterator(); iter.hasNext();){
            sum += iter.next().getPrice();
        }

        return sum;
    }*/

    private double traversal4(Set<InsuredPerson> list){
        double sum = 0;
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            InsuredPerson person = (InsuredPerson) iterator.next();
            sum += person.getPrice();
        }

        return sum;
    }

    private double traversal5(Set<InsuredPerson> list){
        return  list.stream().map(InsuredPerson::getPrice).mapToDouble(Double::doubleValue).sum();
    }

    public void sort(Set<InsuredPerson> list, SortType type){
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

    public InsuredPerson searchPerson(String INN){
        InsuredPerson p = null;
        for (InsuredPerson person : insuredPeoples){
            if (INN.equals(person.getINN())){
                p = person;
            }
        }
        return p;
    }

    private  int checkUniqueness(int number){
        for (Contract i : allContracts){
            if (number == i.getNumber()){
                number++;
               return checkUniqueness(number);
            }
        }
        System.out.println("Номер контракта -" + number);
       return number;
    }

    public String getFullNumberForPrint(){
        StringBuilder fullNumber = new StringBuilder();
        String format = String.format("%07d", number);

        fullNumber.append(this.client.getTypeClient().toString().charAt(0));
        fullNumber.append(format);
        fullNumber.append("-");
        fullNumber.append(this.insuredPeoples.size());

        return  fullNumber.toString();
    }
    @JsonProperty("number")
    public void setNumber(int number) {
        this.number = checkUniqueness(number);
    }

   /* @Override
    public String toString() {
        return "Contract(" +
                "number=" + number +
                ", dateConclusion=" + dateConclusion.get(Calendar.DAY_OF_MONTH) + "." + dateConclusion.get(Calendar.MONTH) + "." + dateConclusion.get(Calendar.YEAR) +
                ", startContract="  + startContract.get(Calendar.DAY_OF_MONTH) + "." + startContract.get(Calendar.MONTH) + "." + startContract.get(Calendar.YEAR) +
                ", finishContract="  + finishContract.get(Calendar.DAY_OF_MONTH) + "." + finishContract.get(Calendar.MONTH) + "." + finishContract.get(Calendar.YEAR) +
                ", client=" + client +
                ", insuredPeoples"  + insuredPeoples +
                ')';
    }*/


}
