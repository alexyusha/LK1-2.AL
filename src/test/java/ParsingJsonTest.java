import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ParsingJsonTest {

    @Test
    void searchFile() {
        String paths = "src";
        String filenameJson = "F:\\Project\\JAVA IDEA\\task_work\\LK_1_AL\\src\\main\\resources\\contract.json";
        String filenameCsv = "F:\\Project\\JAVA IDEA\\task_work\\LK_1_AL\\src\\main\\resources\\contract.csv";

        ParsingJson json = new ParsingJson();
        json.searchFile(paths, ".json");

        ParsingJson csv = new ParsingJson();
        csv.searchFile(paths, ".csv");

        for (File x : json.getPathsSet()){
            assertEquals(filenameJson, x.toString());
        }

        for (File x1 : csv.getPathsSet()){
            assertEquals(filenameCsv, x1.toString());
        }
    }

    @Test
    void readFilesJson() {
    }
}