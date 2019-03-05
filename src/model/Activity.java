package model;

import java.util.Objects;

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

    public boolean match(String str){
        str = str.toUpperCase();
        return this.name.toUpperCase().contains(str) ||
                this.duration.toString().toUpperCase().contains(str) ||
                this.frequency.toString().toUpperCase().contains(str) ||
                this.minimumBudget.toString().toUpperCase().contains(str) ||
                this.maximumBudget.toString().toUpperCase().contains(str) ||
                this.importance.toString().toUpperCase().contains(str);
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Activity activity = (Activity) o;
        return Objects.equals(name.trim().toUpperCase(), activity.name.trim().toUpperCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name.trim().toUpperCase());
    }
}
