import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class InsuredPerson{
    public static Set<InsuredPerson> allInsuredPeople = new LinkedHashSet<>();
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String middleName;
    @Past
    @NotNull
    @JsonDeserialize(using = CalendarDeserializer.class)
    private Calendar birthday;
    @NotNull
    @Positive
    @Size(min = 12, max = 12)
    private String INN;
    @Positive
    private double price;

    public static class Builder{
        private InsuredPerson insuredPerson;

        public Builder() {
            insuredPerson = new InsuredPerson();
        }

        public Builder withFirstName(String firstName){
            insuredPerson.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            insuredPerson.lastName = lastName;
            return this;
        }

        public Builder withMiddleName(String middleName){
            insuredPerson.middleName = middleName;
            return this;
        }

        public Builder withBirthday(Calendar birthday){
            insuredPerson.birthday = birthday;
            return this;
        }

        public Builder withINN(String INN){
            insuredPerson.INN = insuredPerson.checkINN(INN);
            return this;
        }

        public Builder withPrice(double price){
            insuredPerson.price = price;
            return this;
        }

        public InsuredPerson build(){
            allInsuredPeople.add(insuredPerson);
            return insuredPerson;
        }
    }

    public String fullName(){
        String fullName = null;
        try {
            fullName = lastName.substring(0,1).toUpperCase() + lastName.substring(1) + " " + firstName.substring(0, 1).toUpperCase() + "." + middleName.substring(0, 1).toUpperCase() + ".";
        }
        catch (NullPointerException e){
            System.out.println("gg");
        }
        return fullName;
    }

    protected String checkINN(String INN) {
        String tempINN = INN;
        try{
            if (INN.length() == 1){
                for (InsuredPerson person : allInsuredPeople){
                    if (INN.equals(person.getINN())){
                        System.out.println("Человек с таким ИНН существует, проверьте введенный данные");
                        tempINN = null;
                        break;
                    }
                }
            }
            else{
                System.out.println("Проверьте введенные данные");
                tempINN = null;
            }
        }
        catch (NullPointerException e){
            tempINN = null;
            System.out.println("gg");
        }
        return tempINN;
    }
    @JsonProperty("INN")
    public void setINN(String INN) {
        this.INN = checkINN(INN);
    }

    /*@Override
    public String toString() {
        return "InsuredPerson(" +
                "firstName=" + firstName  +
                ", lastName=" + lastName +
                ", middleName=" + middleName  +
                ", birthday=" + birthday.get(Calendar.DAY_OF_MONTH) + "." + birthday.get(Calendar.MONTH) + "." + birthday.get(Calendar.YEAR) +
                ", INN=" + INN +
                ", price=" + price +
                ')';
    }*/

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


