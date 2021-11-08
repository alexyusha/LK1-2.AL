import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.*;
@Setter
@Getter
public class ParsingJson {

    private Set<File> pathsSet = new HashSet<>();

    public  void searchFile(String root, String typeFile) {
        Queue<File> queue = new ArrayDeque<>();
        queue.add(new File(root));

        while (!queue.isEmpty()) {
            File[] files = new File(String.valueOf(queue.poll())).listFiles();
            for (File files1 : files) {
                if (files1.isDirectory()) {
                    queue.add(files1.getAbsoluteFile());
                } else {
                    if (files1.getName().endsWith(typeFile)) {
                        pathsSet.add(files1.getAbsoluteFile());
                    }
                }
            }
        }
    }

    public  void readFilesJson() throws IOException {
        for (File file : pathsSet){
            new ObjectMapper().readValue(file, new TypeReference<List<Contract>>() {});
        }
    }
}
