import java.util.*;

public class Contract {
    private int number;
    private String dateConclusion;
    private String startContract;
    private String finishContract;
    private Client client;
    private List<InsuredPerson> insuredPeoples;

    public Contract(int number, String dateConclusion, String startContract, String finishContract, Client client, List<InsuredPerson> insuredPeoples) {
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

    private double traversal1(List<InsuredPerson> list){
        double sum = 0;

        for (int i = 0; i < list.size(); i++){
            sum += list.get(i).getPrice();
        }

        return sum;
    }

    private double traversal2(List<InsuredPerson> list){
        double sum = 0;

        for (InsuredPerson person : list){
            sum += person.getPrice();
        }

        return sum;
    }

    private double traversal3(List<InsuredPerson> list){
        double sum = 0;

        for(Iterator<InsuredPerson> iter = list.iterator(); iter.hasNext();){
            sum += iter.next().getPrice();
        }

        return sum;
    }

    private double traversal4(List<InsuredPerson> list){
        double sum = 0;

        for(ListIterator<InsuredPerson> iter = list.listIterator(); iter.hasNext();){
            sum += iter.next().getPrice();
        }

        return sum;
    }

    private double traversal5(List<InsuredPerson> list){
        return  list.stream().map(InsuredPerson::getPrice).mapToDouble(Double::doubleValue).sum();
    }

    public void sort(List<InsuredPerson> list, sortType type){
       if (sortType.ALPHABET.equals(type)){
            PersonSortAlphabetComparator personSortAlphabetComparator = new PersonSortAlphabetComparator();
            list.sort(personSortAlphabetComparator);
        }
        else{
            PersonSortBirthdayComparator personSortBirthdayComparator = new PersonSortBirthdayComparator();
            list.sort(personSortBirthdayComparator);
        }

        for (InsuredPerson person : list){
            System.out.println(person.fullName() + " " + person.getBirthday() + " " + person.getINN());
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDateConclusion() {
        return dateConclusion;
    }

    public void setDateConclusion(String dateConclusion) {
        this.dateConclusion = dateConclusion;
    }

    public String getStartContract() {
        return startContract;
    }

    public void setStartContract(String startContract) {
        this.startContract = startContract;
    }

    public String getFinishContract() {
        return finishContract;
    }

    public void setFinishContract(String finishContract) {
        this.finishContract = finishContract;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<InsuredPerson> getInsuredPeoples() {
        return insuredPeoples;
    }

    public void setInsuredPeoples(List<InsuredPerson> insuredPeoples) {
        this.insuredPeoples = insuredPeoples;
    }
}

enum sortType{
    ALPHABET,
    BIRTHDAY
}


