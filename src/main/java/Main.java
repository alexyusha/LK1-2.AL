import сom.work.example.myPackage.service.ParsingJson;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {



        ParsingJson parsingJson = new ParsingJson();
        System.out.println(parsingJson.readFilesJson());


    }
}
