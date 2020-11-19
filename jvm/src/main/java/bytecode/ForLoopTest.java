package bytecode;

public class ForLoopTest {
    private static int[] numbers = {1,6,8};

    public static void main(String[] args) {
        MovingAverage ma = new MovingAverage();
        for(int num : numbers) {// for(int i =0; i < numbers.length; i++) { num = numbers[i];
            ma.submit(num);
        }
        double avg = ma.getAvg();
    }
}