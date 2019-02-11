package model;

public class Model {
    private int val1;
    private int val2;


    public Model(int val1, int val2) {
        this.val1 = val1;
        this.val2 = val2;
    }


    public double compute (char operator){
        double res=0;
        switch (operator) {
            case '+': res = val1+val2; break;
            case '-': res = val1-val2; break;
            case 'x': res = val1*val2; break;
            case '/': res = (double) val1/ (double) val2; break;
        }
        return res;
    }
}
