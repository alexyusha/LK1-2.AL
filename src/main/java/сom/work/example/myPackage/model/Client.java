package сom.work.example.myPackage.model;

import lombok.*;
import сom.work.example.myPackage.dict.TypeClient;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NoArgsConstructor
public class Client {
    private long id;
    @NotNull
    private Enum<TypeClient> typeClient;
    @NotNull
    private String name;
    @NotNull
    private String address;
    private String INN;

    public static class Builder {
        private Client client;

        public Builder() {
            client = new Client();
        }

        public Builder withTypeClient(TypeClient typeClient) {
            client.typeClient = typeClient;
            return this;
        }

        public Builder withName(String name) {
            client.name = name;
            return this;
        }

        public Builder withAddress(String address) {
            client.address = address;
            return this;
        }

        public Builder withINN(String INN) {
            client.INN = INN;
            return this;
        }

        public Client build() {
            return client;
        }
    }

    public Client(Enum<TypeClient> typeClient, String name, String address, String INN) {
        this.typeClient = typeClient;
        this.name = name;
        this.address = address;
        this.INN = INN;
    }

    @Override
    public String toString() {
        return "Client{" +
                "typeClient=" + typeClient +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", INN='" + INN + '\'' +
                '}';
    }
}


