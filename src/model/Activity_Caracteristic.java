package model;

public enum Activity_Caracteristic {
    importance("importance"), name("nom"), endDate("date de fin"), frequency("frequence"), averageBudget("budget moyen");

    private String str;

    Activity_Caracteristic(String str) {
        this.str = str;
    }

    @Override
    public String toString() {
        return str;
    }
}
