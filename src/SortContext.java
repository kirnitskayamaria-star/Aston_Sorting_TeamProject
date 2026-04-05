public class SortContext {
    private SortingStrategy strategy;

    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }

    public void sort(Car[] cars) {
        if (strategy == null) throw new IllegalStateException("Strategy not set");
        strategy.sort(cars);
    }
}