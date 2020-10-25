public class Main {

    public static void main(String[] args) {
        MyOwnInterface moi = (a, b, c) -> a + b + c;
        System.out.println(moi.summator(4,5,6));
    }

    @FunctionalInterface
    public interface MyOwnInterface {
        int summator(int a, int b, int c);
    }
}
