import java.util.Comparator;


public class InsuredPerson{
private String firstName;
private String lastName;
private String middleName;
private String birthday;
private String INN;
private double price;

    public InsuredPerson(String firstName, String lastName, String middleName, String birthday, String INN, double price) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.INN = INN;
        this.price = price;
    }

    public String fullName(){
        String fullName = lastName + " " + firstName.substring(0, 1).toUpperCase() + "." + middleName.substring(0, 1).toUpperCase() + ".";
        return fullName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getINN() {
        return INN;
    }

    public void setINN(String INN) {
        this.INN = INN;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}

class PersonSortAlphabetComparator implements Comparator<InsuredPerson>{
    @Override
    public int compare(InsuredPerson o1, InsuredPerson o2) {
        return o1.fullName().compareTo(o2.fullName());
    }
}

class PersonSortBirthdayComparator implements Comparator<InsuredPerson>{
    @Override
    public int compare(InsuredPerson a, InsuredPerson b) {
        int dateA = GetDate.getDay(a.getBirthday());
        int dateB = GetDate.getDay(b.getBirthday());

        if (dateA > dateB){
            return -1;
        }
        else if (dateA < dateB){
            return  1;
        }
        else {
            return 0;
        }
    }
}
