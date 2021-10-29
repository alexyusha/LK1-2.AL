import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Comparator;


public class InsuredPerson{
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String middleName;
    @Past
    @NotNull
    private Calendar birthday;
    @NotNull
    @Positive
    @Size(min = 12, max = 12)
    private int INN;
    @Positive
    private double price;

    public InsuredPerson(String firstName, String lastName, String middleName, Calendar birthday, int INN, double price) {
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

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public int getINN() {
        return INN;
    }

    public void setINN(int INN) {
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
       return a.getBirthday().compareTo(b.getBirthday());
    }
}
