import javax.validation.constraints.*;
import java.util.*;

public class Contract {
    @Positive
    private int number;
    @FutureOrPresent
    private Calendar dateConclusion;
    @Future
    private Calendar startContract;
    @Future
    private Calendar finishContract;
    @NotNull
    private Client client;
    @NotEmpty
    private Set<InsuredPerson> insuredPeoples;

    public Contract(int number, Calendar dateConclusion, Calendar startContract, Calendar finishContract, Client client, Set<InsuredPerson> insuredPeoples) {
        this.number = number;
        this.dateConclusion = dateConclusion;
        this.startContract = startContract;
        this.finishContract = finishContract;
        this.client = client;
        this.insuredPeoples = insuredPeoples;

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

    public void sort(Set<InsuredPerson> list, sortType type){
        List<InsuredPerson> insuredPeople = new ArrayList<>(list);
         //TreeSet<InsuredPerson> set;
       if (sortType.ALPHABET.equals(type)){
            PersonSortAlphabetComparator personSortAlphabetComparator = new PersonSortAlphabetComparator();
            insuredPeople.sort(personSortAlphabetComparator);
            insuredPeople.sort(personSortAlphabetComparator);
            //set = new TreeSet<>(list);
           //set.addAll(list);
        }
        else{
            PersonSortBirthdayComparator personSortBirthdayComparator = new PersonSortBirthdayComparator();
            insuredPeople.sort(personSortBirthdayComparator);
           //set = new TreeSet<>(personSortBirthdayComparator);
           //set.addAll(list);
        }
        for (InsuredPerson person : insuredPeople){
            System.out.println(person.fullName() + " " + person.getBirthday().getTime() + " " + person.getINN());
        }
        //return set;
    }

    public InsuredPerson searchPerson(int INN){
        InsuredPerson p = null;
        for (InsuredPerson person : insuredPeoples){
            if (INN == person.getINN()){
                p = person;
            }
        }
        return p;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Calendar getDateConclusion() {
        return dateConclusion;
    }

    public void setDateConclusion(Calendar dateConclusion) {
        this.dateConclusion = dateConclusion;
    }

    public Calendar getStartContract() {
        return startContract;
    }

    public void setStartContract(Calendar startContract) {
        this.startContract = startContract;
    }

    public Calendar getFinishContract() {
        return finishContract;
    }

    public void setFinishContract(Calendar finishContract) {
        this.finishContract = finishContract;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<InsuredPerson> getInsuredPeoples() {
        return insuredPeoples;
    }

    public void setInsuredPeoples(Set<InsuredPerson> insuredPeoples) {
        this.insuredPeoples = insuredPeoples;
    }
}

enum sortType{
    ALPHABET,
    BIRTHDAY
}


