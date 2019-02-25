package model;

public enum Importance{
    Low, Medium, High;
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
}
