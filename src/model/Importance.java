package model;

public enum Importance{
    Low("Faible", 0), Medium("Moyenne", 1), High("Forte", 2);

    private final String str;
    private final int level;

    public static Importance getImportance(String str){
        switch (str){
            case "Faible":
                return Importance.Low;
            case "Moyenne":
                return Importance.Medium;
            case "Forte":
                return Importance.High;
            default:
                return Importance.Low;
        }
    }

    Importance(String str, int level){
        this.str = str;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return str;
    }
}
