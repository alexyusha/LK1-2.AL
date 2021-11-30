package сom.work.example.myPackage.service;

import сom.work.example.myPackage.dict.SortType;
import сom.work.example.myPackage.model.Contract;
import сom.work.example.myPackage.model.InsuredPerson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ContractServices {

    public static double allSum(Contract contract) {
        double scale = Math.pow(10, 2);
        double sum = contract.getInsuredPeoples().stream().map(InsuredPerson::getPrice).mapToDouble(Double::doubleValue).sum();
        return Math.ceil(sum * scale) / scale;
    }

    public static void sort(Set<InsuredPerson> list, SortType type) {
        List<InsuredPerson> insuredPeople = new ArrayList<>(list);
        if (SortType.ALPHABET.equals(type)) {
            PersonSortAlphabetComparator personSortAlphabetComparator = new PersonSortAlphabetComparator();
            insuredPeople.sort(personSortAlphabetComparator);
            insuredPeople.sort(personSortAlphabetComparator);

        } else {
            PersonSortBirthdayComparator personSortBirthdayComparator = new PersonSortBirthdayComparator();
            insuredPeople.sort(personSortBirthdayComparator);

        }
        for (InsuredPerson person : insuredPeople) {
            System.out.println(person.fullName() + " " + person.getBirthday().getTime() + " " + person.getINN());
        }
    }
}
