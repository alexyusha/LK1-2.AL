package сom.work.example.myPackage.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import сom.work.example.myPackage.dict.TypeFile;
import сom.work.example.myPackage.model.Contract;

import java.io.File;
import java.io.IOException;
import java.util.*;
@Setter
@Getter
public class ParsingJson {

    public static Set<File> searchFile(String root, TypeFile typeFile){
        Set<File> pathsSet = new HashSet<>();
        Queue<File> queue = new ArrayDeque<>();
        queue.add(new File(root));

        while (!queue.isEmpty()){
            File[] files = new File(String.valueOf(queue.poll())).listFiles();
            for (File files1 : files){
                if (files1.isDirectory()){
                    queue.add(files1.getAbsoluteFile());
                }
                else{
                    if (files1.getName().endsWith(typeFile.getValue())){
                        pathsSet.add(files1.getAbsoluteFile());
                    }
                }
            }
        }
        return pathsSet;
    }

    public List<Contract> readFilesJson() throws IOException {
        List<Contract> allContracts = new ArrayList<>();
        for (File file : searchFile("src\\main\\resources", TypeFile.JSON)){
           List<Contract> list =  new ObjectMapper().readValue(file, new TypeReference<List<Contract>>() {});
           allContracts.addAll(list);
        }
        return allContracts;
    }
}
