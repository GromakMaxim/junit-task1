import java.util.ArrayDeque;
import java.util.Deque;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    /*Нужно написать программу, которая считывает данные из консоли, ожидая ввода номера этажа.
    После ввода каждого числа (номера этажа) добавляет значение в очередь дальнейшего перемещения лифта.
    Как только пользователь введет 0, программа должна последовательно вывести список всех этажей,
    на которых лифт делал остановки, в формате: "этаж 1 -> этаж 22 -> этаж 0".
    Если пользователь ввел этаж вне диапазона 0-25, проигнорировать ввод таких данных.
    Для реализации хранения введенных пользователем этажей отлично подойдет структура на основе интерфейса очередь (queue).
    По мере ввода мы сможем добавлять в конец очереди новые значения.
    --------------------
    После ввода нулевого этажа, программа должна рассчитать время движения лифта вместе с остановками.
    Скорость движения лифта между парой этажей равна 5 секунд,
    время остановки на открытие и закрытие дверей лифта 10 секунд.
    Вывести информацию, сколько времени потребовалось лифту, чтобы пройти ранее введенные этажи.*/
    public static final int waitDoorsInSeconds = 10;//время на открытие/закрытие дверей
    public static final int waitMoveInSeconds = 5;//время на движение лифта
    public static final int numberOfFloors = 25;//всего 25 этажей в доме

    public static void main(String[] args) {
        Deque<Integer> liftQueue = new ArrayDeque<>();
        System.out.println();
        //заполняем очередь с этажами, показываем очеред высчитывая время
        System.out.println("время движения - " + showLiftMoving(fillQueue(liftQueue)) + " сек");
    }

    static Deque<Integer> fillQueue(Deque<Integer> liftQueue) {
        Scanner inputCommandToLift = new Scanner(System.in);
        while (true) {
            System.out.println("Ожидаю ввода этажа: (для завершения введите 0)");
            try {
                int inputFloor = inputCommandToLift.nextInt();
                if (inputFloor > 0 && inputFloor <= numberOfFloors) {
                    liftQueue.addLast(inputFloor);
                } else if (inputFloor == 0) {
                    liftQueue.addLast(0);
                    break;
                } else {
                    System.out.println("Такого этажа нет! Повторите ввод:");
                }
            } catch (InputMismatchException inputMismatchException) {
                liftQueue.addLast(0);
                break;
            }
        }
        return liftQueue;
    }

    static int showLiftMoving(Deque<Integer> liftQueue) {
        int prevFloor = 0;//предыдущий этаж
        int curFloor;//текущий этаж
        int totalSeconds = 0;//всего времени в сек
        System.out.println("Лифт проследовал по следующим этажам:");
        while (!liftQueue.isEmpty()) {
            System.out.print("этаж " + liftQueue.element());
            System.out.print(0 != liftQueue.element() ? "-->" : "");//если этаж не 0, рисуем стрелочку
            totalSeconds += waitDoorsInSeconds;
            curFloor = liftQueue.element();
            totalSeconds += Math.abs(curFloor - prevFloor) * waitMoveInSeconds;
            prevFloor = curFloor;
            liftQueue.poll();
        }
        System.out.println();
        return (totalSeconds - waitDoorsInSeconds);
    }
}