package model;

public class Activity {
    private String name;
    private Duration duration;
    private Frequency frequency;
    private int minimumBudget;
    private int maximumBudget;

    public Activity(String name, Duration duration, Frequency frequency, int minimumBudget, int maximumBudget) {
        this.name = name;
        this.duration = duration;
        this.frequency = frequency;
        this.minimumBudget = minimumBudget;
        this.maximumBudget = maximumBudget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public int getMinimumBudget() {
        return minimumBudget;
    }

    public void setMinimumBudget(int minimumBudget) {
        this.minimumBudget = minimumBudget;
    }

    public int getMaximumBudget() {
        return maximumBudget;
    }

    public void setMaximumBudget(int maximumBudget) {
        this.maximumBudget = maximumBudget;
    }
}
