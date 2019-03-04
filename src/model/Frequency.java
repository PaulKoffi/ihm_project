package model;

public enum Frequency {
    oneByDay("Un par jour", 0), oneByWeek("Un par semaine", 1), oneByMonth("Un par mois", 2), oneByYear("Un par an", 3);

    private final String string;
    private final int level;

    Frequency(String string, int level) {
        this.string = string;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return string;
    }
}
