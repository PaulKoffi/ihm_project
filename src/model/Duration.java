package model;

public enum Duration {
    infiny("Infini"), oneYear("Un an"), oneMounth("Un mois"), oneDay("Un jour");

    private final String string;

    Duration(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }
}
