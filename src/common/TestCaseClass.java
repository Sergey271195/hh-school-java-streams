package common;

public abstract class TestCaseClass<T> {

    public abstract void execute(T t);

    public void test(int repeat, T t, String timeComplexity) {
        System.out.println("<-- " + this.getClass().getSimpleName() + " -->");
        System.out.println("Estimated time complexity - " + timeComplexity);
        SimpleTimer timer = new SimpleTimer();
        timer.start();
        for (int i = 0; i < repeat; i++) {
            execute(t);
        }
        timer.end(repeat);
    }

}
