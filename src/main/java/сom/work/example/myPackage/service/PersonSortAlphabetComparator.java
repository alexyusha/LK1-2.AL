package сom.work.example.myPackage.service;

import сom.work.example.myPackage.model.InsuredPerson;

import java.util.Comparator;

public class PersonSortAlphabetComparator implements Comparator<InsuredPerson> {
    @Override
    public int compare(InsuredPerson o1, InsuredPerson o2) {
        return o1.fullName().compareTo(o2.fullName());
    }
}
