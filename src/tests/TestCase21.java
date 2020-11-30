package tests;

import common.Person;
import common.TestCaseClass;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class TestCase21 extends TestCaseClass<List<Collection<Person>>> {

    @Override
    public void execute(List<Collection<Person>> persons) {
        Collection<Person> persons1 = persons.get(0);
        Collection<Person> persons2 = persons.get(1);
        long start = System.currentTimeMillis();
        boolean has = false;
        for (Person person1 : persons1) {
            if (System.currentTimeMillis() - start > 8_000) {
                System.out.println("You don't want to wait for this to finish...");
                System.out.println("Execution time -> infinity");
                break;
            }
            for (Person person2 : persons2) {
                if (person1.equals(person2)) {
                    has = true;
                }
            }
        }
    }
}

