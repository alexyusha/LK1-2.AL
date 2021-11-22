package сom.work.example.myPackage.model;

import lombok.*;
import сom.work.example.myPackage.dict.TypeClient;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @NotNull
    private Enum<TypeClient> typeClient;
    @NotNull
    private  String name;
    @NotNull
    private String address;

    public static class Builder {
        private Client client;

        public Builder(){
            client = new Client();
        }

        public Builder withTypeClient(TypeClient typeClient){
            client.typeClient = typeClient;
            return this;
        }

        public Builder withName(String name){
            client.name = name;
            return this;
        }

        public Builder withAddress(String address){
            client.address = address;
            return this;
        }

        public Client build(){
            return client;
        }
    }
}


