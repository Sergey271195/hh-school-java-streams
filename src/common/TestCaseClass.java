package common;

public abstract class TestCaseClass<T> {

    public abstract void execute(T t);

    public void test(int repeat, T t, String timeComplexity, String message) {
        System.out.println("\n\t<-- " + this.getClass().getSimpleName() + " -->");

        if (message != "") {
            System.out.println("Comment: " + message);
        }

        if (timeComplexity != "") {
            System.out.println("Estimated time complexity - " + timeComplexity);
        }

        SimpleTimer timer = new SimpleTimer();
        timer.start();
        for (int i = 0; i < repeat; i++) {
            execute(t);
        }
        timer.end(repeat);
    }

}
