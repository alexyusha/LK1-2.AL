package сom.work.example.myPackage.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import сom.work.example.myPackage.dict.deser.CalendarDeserializer;
import сom.work.example.myPackage.dict.deser.ClientDeserializer;
import сom.work.example.myPackage.dict.deser.InsuredPersonDeserializer;

import javax.validation.constraints.*;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter

@NoArgsConstructor
public class Contract {
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
    private List<InsuredPerson> insuredPeoples;

    public static class Builder{
        private Contract newContract;

        public Builder(){
            newContract = new Contract();
        }

        public Builder withNumber(int number){
            newContract.number = number;
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

        public Builder withInsuredPeople(List<InsuredPerson> insuredPeople){
            newContract.insuredPeoples = insuredPeople;
            return this;
        }

        public Contract build(){
            return newContract;
        }
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

   @Override
    public String toString() {
        return "сom.work.example.myPackage.model.Contract(" +
                "number=" + number +
                ", dateConclusion=" + dateConclusion.get(Calendar.DAY_OF_MONTH) + "." + dateConclusion.get(Calendar.MONTH) + "." + dateConclusion.get(Calendar.YEAR) +
                ", startContract="  + startContract.get(Calendar.DAY_OF_MONTH) + "." + startContract.get(Calendar.MONTH) + "." + startContract.get(Calendar.YEAR) +
                ", finishContract="  + finishContract.get(Calendar.DAY_OF_MONTH) + "." + finishContract.get(Calendar.MONTH) + "." + finishContract.get(Calendar.YEAR) +
                ", client=" + client +
                ", insuredPeoples"  + insuredPeoples +
                ')';
    }


}
