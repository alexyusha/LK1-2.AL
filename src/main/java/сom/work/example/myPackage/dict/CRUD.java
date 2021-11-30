package сom.work.example.myPackage.dict;

import java.util.Map;

public interface CRUD<T> {
    void create(T t);
    T read(int numberContract);
    void update(int numberContract, Map<String, Object> updated);
    void delete(int numberContract);
}
