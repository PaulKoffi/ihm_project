package model;

public enum Importance{
    Low(0), Medium(1), High(2);

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

    Importance(int level){
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
}
