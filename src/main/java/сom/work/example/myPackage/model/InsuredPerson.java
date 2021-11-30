package сom.work.example.myPackage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import сom.work.example.myPackage.model.deser.CalendarDeserializer;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Calendar;

@Getter
@Setter
@NoArgsConstructor
public class InsuredPerson{
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
    @JsonProperty("INN")
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
            insuredPerson.INN = INN;
            return this;
        }

        public Builder withPrice(double price){
            insuredPerson.price = price;
            return this;
        }

        public InsuredPerson build(){
            return insuredPerson;
        }
    }

    public String fullName(){
        if (lastName != null && firstName != null && middleName != null){
           return lastName.substring(0,1).toUpperCase() + lastName.substring(1) + " " + firstName.substring(0, 1).toUpperCase() + "." + middleName.substring(0, 1).toUpperCase() + ".";
        }
        else {
            return null;
        }
    }

    @Override
    public String toString() {
        return "сom.work.example.myPackage.model.InsuredPerson(" +
                "firstName=" + firstName  +
                ", lastName=" + lastName +
                ", middleName=" + middleName  +
                ", birthday=" + birthday.get(Calendar.DAY_OF_MONTH) + "."
                                + birthday.get(Calendar.MONTH) + "."
                                + birthday.get(Calendar.YEAR) +
                ", INN=" + INN +
                ", price=" + price +
                ')';
    }

}



