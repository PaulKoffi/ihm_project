package model;

public class Activity {
    /**
     * Attributes
     */
    private String name;
    private Duration duration;
    private Frequency frequency;
    private Integer minimumBudget;
    private Integer maximumBudget;
    private Importance importance;

    /**
     * Constructor
     * @param name
     * @param duration
     * @param frequency
     * @param minimumBudget
     * @param maximumBudget
     * @param importance
     */
    public Activity(String name, Duration duration, Frequency frequency, int minimumBudget, int maximumBudget, Importance importance) {
        this.name = name;
        this.duration = duration;
        this.frequency = frequency;
        this.minimumBudget = minimumBudget;
        this.maximumBudget = maximumBudget;
        this.importance = importance;
    }

    /* Getters */
    public String getName() {
        return name;
    }

    public Duration getDuration() {
        return duration;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Integer getMinimumBudget() {
        return minimumBudget;
    }

    public Integer getMaximumBudget() {
        return maximumBudget;
    }

    public Importance getImportance() {
        return importance;
    }

    /* Setters */

    public void setName(String name) {
        this.name = name;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public void setFrequency(Frequency frequency) {
        this.frequency = frequency;
    }

    public void setMinimumBudget(Integer minimumBudget) {
        this.minimumBudget = minimumBudget;
    }

    public void setMaximumBudget(Integer maximumBudget) {
        this.maximumBudget = maximumBudget;
    }

    public void setImportance(Importance importance) {
        this.importance = importance;
    }
}
