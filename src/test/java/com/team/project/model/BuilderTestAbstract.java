public abstract class BuilderTestAbstract {
    protected static final int minPower = 100;
    protected static final int maxPower = 2000;
    protected static final int minYear = 1900;
    protected static final int maxYear = 2026;
    protected static final String[] models = {"Toyota", "BMW", "Ferrari",
            "Bugatti", "Maseratti", "Mercedez Bens", "Austin Martin", "Rolls Royce",
            "Porsche", "Haval", "Lada", "Audi", "Saab", "Chevron", "Volkswagen",
            "Land Rover", "Lamborgini", "Smart", "Nissan", "Mini-Cooper",
            "Kamaz", "Niva", "Aurus"};
    protected int power;
    protected int year;
    protected String model;

    public abstract void input();

    public abstract Car output();
}
