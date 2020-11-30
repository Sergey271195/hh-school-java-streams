package tests;

import common.Person;
import common.TestCaseClass;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class TestCase22 extends TestCaseClass<List<Collection<Person>>> {

    @Override
    public void execute(List<Collection<Person>> persons) {
        Collection<Person> persons1 = persons.get(0);
        Collection<Person> persons2 = persons.get(1);
        HashSet<Person> personsSet1 = new HashSet<>(persons1);
        HashSet<Person> personsSet2 = new HashSet<>(persons2);
        personsSet1.retainAll(personsSet2);
        boolean result = personsSet1.size() > 0;
    }
}

