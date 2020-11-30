package common;

public class SimpleTimer {

    private static long startingTime;

    public void start() {
        System.out.println("Starting Timer...");
        setStartingTime(System.currentTimeMillis());
    }

    public void end() {
        System.out.print("Execution time in milliseconds: ");
        System.out.println(System.currentTimeMillis() - getStartingTime());
    }

    public void end(int n) {
        System.out.println("Execution time in milliseconds");
        System.out.print("Total: ");
        System.out.println((System.currentTimeMillis() - getStartingTime()) + " mil");
        System.out.print("Average of " + n + ": ");
        System.out.println((System.currentTimeMillis() - getStartingTime())/n + " mil");
    }

    public long getStartingTime() {
        return startingTime;
    }

    public void setStartingTime(long startingTime) {
        this.startingTime = startingTime;
    }

}
