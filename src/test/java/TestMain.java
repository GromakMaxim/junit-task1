import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.Deque;
class TestMain {

    @Test
    void test_validQueue_success() {
        Deque<Integer> liftQueue = new ArrayDeque<>();
        liftQueue.addLast(2);
        liftQueue.addLast(5);
        liftQueue.addLast(8);
        liftQueue.addLast(0);

        int actual = Main.showLiftMoving(liftQueue);
        Assertions.assertEquals(110, actual);
    }

    @Test
    void test_ZeroFloorOnly_success() {
        Deque<Integer> liftQueue = new ArrayDeque<>();
        liftQueue.addLast(0);
        int actual = Main.showLiftMoving(liftQueue);
        Assertions.assertEquals(0, actual);
    }

    @Test
    void test_repeatedValuesInQueue_success() {
        Deque<Integer> liftQueue = new ArrayDeque<>();
        liftQueue.addLast(3);
        liftQueue.addLast(3);
        liftQueue.addLast(3);
        liftQueue.addLast(0);

        int actual = Main.showLiftMoving(liftQueue);
        Assertions.assertEquals(60, actual);
    }
}