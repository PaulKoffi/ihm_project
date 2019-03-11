package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Account;
import model.Activity;
import model.Frequency;
import model.Importance;
import view.View;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Home_Controller {
    public class ActivityBudget{
        private String name;
        private Activity activity;
        private Double budget;

        public ActivityBudget(Activity activity, Double budget){
            this.activity = activity;
            this.name = activity.getName();
            this.budget = budget;
        }

        public Activity getActivity() {
            return activity;
        }

        public void setActivity(Activity activity) {
            this.activity = activity;
        }

        public Double getBudget() {
            return budget;
        }

        public void setBudget(Double budget) {
            this.budget = budget;
        }

        public String getName() {
            return name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof ActivityBudget)) return false;
            ActivityBudget that = (ActivityBudget) o;
            return Objects.equals(activity, that.activity);
        }

        @Override
        public int hashCode() {

            return Objects.hash(activity);
        }
    }


    @FXML
    private TableView TVtableView;
    @FXML
    private TableColumn<ActivityBudget, String> TCwhat;
    @FXML
    private TableColumn<ActivityBudget, Double> TChowMuch;

    private ObservableList<Activity> activities;
    private Account currentAccount;
    public ObservableList<ActivityBudget> activityBudgets;

    public void init(ObservableList<Activity> activities, Account currentAccount){
        this.activities = activities;
        this.currentAccount = currentAccount;
        refresh();
    }

    public void refresh(){
        for (Account account : AccountsJSONadmin.getAccountListFromJSON(View.ACCOUNT_JSON_FILE)){
            if (account.getEmail().equals(currentAccount.getEmail())){
                currentAccount = account;
                break;
            }
        }

        this.activityBudgets = FXCollections.observableArrayList();
        double budgetMonth = currentAccount.getSalary();

        //Remplissage avec acti importante
        for(Activity activity : activities){
            if (activity.getImportance() == Importance.High){
                if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - activity.getMinimumBudget()*30 > 0){
                    budgetMonth -= activity.getMinimumBudget()*30;
                    activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget() * 30)));
                } else if (activity.getFrequency() == Frequency.oneByMonth && budgetMonth - activity.getMinimumBudget() > 0){
                    budgetMonth -= activity.getMinimumBudget();
                    if (LocalDate.now().getDayOfMonth() == 1)
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget())));
                } else if (activity.getFrequency() == Frequency.oneByWeek && budgetMonth - activity.getMinimumBudget()*4.5 > 0){
                    budgetMonth -= activity.getMinimumBudget()*4.5;
                    if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY)
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget() * 4.5)));
                } else if (activity.getFrequency() == Frequency.oneByYear && budgetMonth - activity.getMinimumBudget()*0.083 > 0){
                    budgetMonth -= activity.getMinimumBudget()*0.083;
                    if (LocalDate.now().getDayOfYear() == 1)
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget() * 0.083)));
                }
            }
        }

        //Remplissage avec acti moyenne
        for(Activity activity : activities){
            if (activity.getImportance() == Importance.Medium){
                if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - activity.getMinimumBudget()*30 > 0){
                    budgetMonth -= activity.getMinimumBudget()*30;
                    activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget() * 30)));
                } else if (activity.getFrequency() == Frequency.oneByMonth && budgetMonth - activity.getMinimumBudget() > 0){
                    budgetMonth -= activity.getMinimumBudget();
                    if (LocalDate.now().getDayOfMonth() == 1)
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget())));
                } else if (activity.getFrequency() == Frequency.oneByWeek && budgetMonth - activity.getMinimumBudget()*4.5 > 0){
                    budgetMonth -= activity.getMinimumBudget()*4.5;
                    if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY)
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget() * 4.5)));
                } else if (activity.getFrequency() == Frequency.oneByYear && budgetMonth - activity.getMinimumBudget()*0.083 > 0){
                    budgetMonth -= activity.getMinimumBudget()*0.083;
                    if (LocalDate.now().getDayOfYear() == 1)
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget() * 0.083)));
                }
            }
        }

        //Remplissage avec acti low
        for(Activity activity : activities){
            if (activity.getImportance() == Importance.Low){
                if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - activity.getMinimumBudget()*30 > 0){
                    budgetMonth -= activity.getMinimumBudget()*30;
                    activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget() * 30)));
                } else if (activity.getFrequency() == Frequency.oneByMonth && budgetMonth - activity.getMinimumBudget() > 0){
                    budgetMonth -= activity.getMinimumBudget();
                    if (LocalDate.now().getDayOfMonth() == 1)
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget())));
                } else if (activity.getFrequency() == Frequency.oneByWeek && budgetMonth - activity.getMinimumBudget()*4.5 > 0){
                    budgetMonth -= activity.getMinimumBudget()*4.5;
                    if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY)
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget() * 4.5)));
                } else if (activity.getFrequency() == Frequency.oneByYear && budgetMonth - activity.getMinimumBudget()*0.083 > 0){
                    budgetMonth -= activity.getMinimumBudget()*0.083;
                    if (LocalDate.now().getDayOfYear() == 1)
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMinimumBudget() * 0.083)));
                }
            }
        }

        //Remplissage avec acti importante
        for(Activity activity : activities){
            if (activity.getImportance() == Importance.High){
                if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget())*30 > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget()) * 30;
                    activityBudgets.remove(new ActivityBudget(activity,0.0));
                    activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget())));
                } else if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget()) > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget());
                    activityBudgets.remove(new ActivityBudget(activity,0.0));
                    if (LocalDate.now().getDayOfMonth() == 1) {
                        activityBudgets.remove(new ActivityBudget(activity, 0.0));
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget())));
                    }
                } else if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget())*4.5 > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget()) * 4.5;

                    if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY) {
                        activityBudgets.remove(new ActivityBudget(activity, 0.0));
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget() * 4.5)));
                    }
                } else if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget())*0.083 > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget()) * 0.083;
                    if (LocalDate.now().getDayOfYear() == 1) {
                        activityBudgets.remove(new ActivityBudget(activity, 0.0));
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget() * 0.083)));
                    }
                }
            }
        }

        //Remplissage avec acti moyenne
        for(Activity activity : activities){
            if (activity.getImportance() == Importance.Medium){
                if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget())*30 > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget()) * 30;
                    activityBudgets.remove(new ActivityBudget(activity,0.0));
                    activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget())));
                } else if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget()) > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget());
                    if (LocalDate.now().getDayOfMonth() == 1) {
                        activityBudgets.remove(new ActivityBudget(activity, 0.0));
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget())));
                    }
                } else if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget())*4.5 > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget()) * 4.5;

                    if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY) {
                        activityBudgets.remove(new ActivityBudget(activity, 0.0));
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget() * 4.5)));
                    }
                } else if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget())*0.083 > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget()) * 0.083;
                    if (LocalDate.now().getDayOfYear() == 1) {
                        activityBudgets.remove(new ActivityBudget(activity, 0.0));
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget() * 0.083)));
                    }
                }
            }
        }

        //Remplissage avec acti low
        for(Activity activity : activities){
            if (activity.getImportance() == Importance.Low){
                if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget())*30 > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget()) * 30;
                    activityBudgets.remove(new ActivityBudget(activity,0.0));
                    activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget())));
                } else if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget()) > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget());
                    if (LocalDate.now().getDayOfMonth() == 1) {
                        activityBudgets.remove(new ActivityBudget(activity, 0.0));
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget())));
                    }
                } else if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget())*4.5 > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget()) * 4.5;

                    if (LocalDate.now().getDayOfWeek() == DayOfWeek.MONDAY) {
                        activityBudgets.remove(new ActivityBudget(activity, 0.0));
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget() * 4.5)));
                    }
                } else if (activity.getFrequency() == Frequency.oneByDay && budgetMonth - (activity.getMaximumBudget() - activity.getMinimumBudget())*0.083 > 0){
                    budgetMonth -= (activity.getMaximumBudget() - activity.getMinimumBudget()) * 0.083;

                    if (LocalDate.now().getDayOfYear() == 1) {
                        activityBudgets.remove(new ActivityBudget(activity, 0.0));
                        activityBudgets.add(new ActivityBudget(activity, Double.valueOf(activity.getMaximumBudget() * 0.083)));
                    }
                }
            }
        }

        TCwhat.setCellValueFactory(new PropertyValueFactory<ActivityBudget, String>("Name"));
        TChowMuch.setCellValueFactory(new PropertyValueFactory<ActivityBudget, Double>("Budget"));

        TVtableView.setItems(activityBudgets);
    }
}
