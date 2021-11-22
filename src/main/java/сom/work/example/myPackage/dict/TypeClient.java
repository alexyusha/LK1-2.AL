package сom.work.example.myPackage.dict;


public enum TypeClient {
    INDIVIDUAL("INDIVIDUAL"),
    ENTITY("ENTITY");

    private final String name;

    TypeClient(String s){
        name = s;
    }

    public String getValue(){
        return name;
    }
}
