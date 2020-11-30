package tests;

import common.TestCaseClass;

import java.util.stream.Stream;

public class TestCase25 extends TestCaseClass<Integer> {

    @Override
    public void execute(Integer number) {
        Stream.iterate(0, i -> i + 1).limit(50_000_000).filter(num -> num % 2 == 0).count();
    }
}
