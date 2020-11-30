package tests;

import common.Person;
import common.TestCaseClass;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCase24 extends TestCaseClass<Integer> {

    private int count;

    @Override
    public void execute(Integer number) {
        count = 0;
        Stream.iterate(0, i -> i + 1).limit(50_000_000).filter(num -> num % 2 == 0).forEach(num -> count++);
    }
}

