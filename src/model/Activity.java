package model;

import java.util.Date;
import java.util.Objects;
import java.util.Observable;

public class Activity extends Observable {
    /**
     * Attributes
     */
    private String name;
    private Date endDate;
    private Frequency frequency;
    private Integer minimumBudget;
    private Integer maximumBudget;
    private Importance importance;

    /**
     * Constructor
     * @param name
     * @param endDate
     * @param frequency
     * @param minimumBudget
     * @param maximumBudget
     * @param importance
     */
    public Activity(String name, Date endDate, Frequency frequency, int minimumBudget, int maximumBudget, Importance importance) {
        this.name = name;
        this.endDate = endDate;
        this.frequency = frequency;
        this.minimumBudget = minimumBudget;
        this.maximumBudget = maximumBudget;
        this.importance = importance;
    }

    public Activity(String name){
        this.name = name;
    }

    /* Getters */
    public String getName() {
        return name;
    }

    public Date getEndDate() {
        return endDate;
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

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
                this.endDate.toString().toUpperCase().contains(str) ||
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
