package model;

public enum Duration {
    infiny("Infini", 0), oneYear("Un an", 1), oneMounth("Un mois", 2), oneDay("Un jour", 3);

    private final String string;
    private final int level;

    Duration(String string, int level) {
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
