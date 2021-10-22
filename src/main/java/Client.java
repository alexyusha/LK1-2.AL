

public class Client {
    private Enum<TypeClient> typeClient;
    private  String name;
    private String address;

    public Client(Enum<TypeClient> typeClient, String name, String address) {
        this.typeClient = typeClient;
        this.name = name;
        this.address = address;
    }

    public Enum<TypeClient> getTypeClient() {
        return typeClient;
    }

    public void setTypeClient(Enum<TypeClient> typeClient) {
        this.typeClient = typeClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
enum TypeClient{
    INDIVIDUAL,
    ENTITY
}
