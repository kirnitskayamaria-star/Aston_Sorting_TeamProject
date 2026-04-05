import java.util.Comparator;

public class CarSortingStrategies {

    public static final Comparator<Car> BY_MODEL = Comparator.comparing(Car::getModel);
    public static final Comparator<Car> BY_POWER = Comparator.comparingInt(Car::getPower);
    public static final Comparator<Car> BY_YEAR = Comparator.comparingInt(Car::getYear);

    public static class SortByModel implements SortingStrategy {
        @Override
        public void sort(Car[] cars) {
            bubbleSort(cars, BY_MODEL);
        }
    }

    public static class SortByPower implements SortingStrategy {
        @Override
        public void sort(Car[] cars) {
            bubbleSort(cars, BY_POWER);
        }
    }

    public static class SortByYear implements SortingStrategy {
        @Override
        public void sort(Car[] cars) {
            bubbleSort(cars, BY_YEAR);
        }
    }

    private static void bubbleSort(Car[] arr, Comparator<Car> comparator) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    swap(arr, j);
                }
            }
        }
    }

    private static void swap(Car[] arr, int i) {
        Car temp = arr[i];
        arr[i] = arr[i + 1];
        arr[i + 1] = temp;
    }
}