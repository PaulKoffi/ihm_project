package model;

public class Activity {
    private String name;
    private Duration duration;
    private Frequency frequency;
    private Integer minimumBudget;
    private Integer maximumBudget;
    private Importance importance;

    public Activity(String name, Duration duration, Frequency frequency, int minimumBudget, int maximumBudget, Importance importance) {
        this.name = name;
        this.duration = duration;
        this.frequency = frequency;
        this.minimumBudget = minimumBudget;
        this.maximumBudget = maximumBudget;
        this.importance = importance;
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

    public Integer getMinimumBudget() {
        return minimumBudget;
    }

    public void setMinimumBudget(Integer minimumBudget) {
        this.minimumBudget = minimumBudget;
    }

    public Integer getMaximumBudget() {
        return maximumBudget;
    }

    public void setMaximumBudget(Integer maximumBudget) {
        this.maximumBudget = maximumBudget;
    }

    public Importance getImportance() {
        return importance;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }
}
