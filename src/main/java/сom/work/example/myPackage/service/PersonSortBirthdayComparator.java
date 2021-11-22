package сom.work.example.myPackage.service;

import сom.work.example.myPackage.model.InsuredPerson;

import java.util.Comparator;

public class PersonSortBirthdayComparator implements Comparator<InsuredPerson> {
    @Override
    public int compare(InsuredPerson a, InsuredPerson b) {
        return a.getBirthday().compareTo(b.getBirthday());
    }
}
