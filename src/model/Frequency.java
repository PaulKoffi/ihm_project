package model;

public enum Frequency {
    oneByDay("Un par jour"), oneByWeek("Un par semaine"), oneByMonth("Un par mois"), oneByYear("Un par an");

    private final String string;

    Frequency(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
