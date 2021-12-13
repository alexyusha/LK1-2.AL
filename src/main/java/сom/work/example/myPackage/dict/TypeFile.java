package сom.work.example.myPackage.dict;

public enum TypeFile {
    JSON(".json"),
    CSV(".csv"),
    XML(".xml");

    private final String name;

    TypeFile(String s) {
        name = s;
    }

    public String getValue() {
        return name;
    }
}

