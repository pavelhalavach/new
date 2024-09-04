package main.java.org.solvd.structure.taxipark;

import main.java.org.solvd.structure.exceptions.NoRequestsException;
import main.java.org.solvd.structure.interfaces.IncomeGettable;
import main.java.org.solvd.structure.application.Request;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class Accounting implements IncomeGettable {
    private static float bill = 0.26f;
    static {
        bill = 0.26f;
    }
    private float income;
    private static final Logger logger = LogManager.getRootLogger();
    private static final Logger logger_err = LogManager.getLogger("errors");

    protected Accounting() {
    }

    @Override
    public float getIncomeAfterBills(Request[] requests, Assistant assistant){
        logger_err.info("Executing Accounting.getIncomeAfterBills()");
        try {
            calculateIncome(requests, assistant);
        } catch (NoRequestsException e) {
            logger_err.error(e.getMessage());
        }
        logger_err.info("Closing Accounting.getIncomeAfterBills()");
        return income * (1 - bill);
    }

    @Override
    public void calculateIncome(Request[] requests, Assistant assistant) throws NoRequestsException {
        float income = assistant.getSalary();;
        if (requests == null){
            this.income = income;
            throw new NoRequestsException();
        }
        else{
            for (var request : requests){
                income += request.getPrice() * (1 - Driver.getPercentage());
            }
            this.income = income;
        }

    }

    @Override
    public String toString() {
        return "Accounting{" +
                "income=" + income +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Accounting that = (Accounting) o;
        return Float.compare(income, that.income) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(income);
    }

    public static float getBill() {
        return bill;
    }

    public static void setBill(float bill) {
        Accounting.bill = bill;
    }

    public float getIncome() {
        return income;
    }

//    HAVE TO CHANGE IT
    public void setIncome(float income) {
        this.income = income;
    }
}
