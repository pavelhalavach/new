package structure;

import structure.application.Request;
import structure.taxipark.Driver;
import structure.taxipark.TaxiPark;

import java.util.Arrays;

public class TaxiCompany {
    private TaxiPark[] taxiParks;

    public TaxiCompany(TaxiPark[] taxiParks) {
        this.taxiParks = taxiParks;
    }

    public void printAllDrivers(){
        System.out.println("Drivers:");
        for (var driver : getAllDrivers()){
            if (driver != null) System.out.println(driver.getName() + " " + driver.getSurname());
        }
    }

    private Driver[] getAllDrivers() {
        int length = 0;
        for (var taxiPark : taxiParks) {
            length += taxiPark.getAllDrivers().length;
        }
        Driver[] drivers = new Driver[length];
        int j = 0;
        int indicator;
        for (var taxiPark : taxiParks){
            indicator = 0;
            for (int i = 0; i < taxiPark.getAllDrivers().length; i++){
                for (var driver : drivers){
                    if(driver != null && driver.equals(taxiPark.getAllDrivers()[i])){
                        indicator = 1;
                        break;
                    }
                }
                if(indicator != 1){
                    drivers[i+j] = taxiPark.getAllDrivers()[i];
                }
            }
            j++;
        }
        return drivers;

    }

    public void printTotalIncomeAfterBills(){
        System.out.println("Total income of the company after bills: " +
                calculateTotalIncomeAfterBills());
    }

    private float calculateTotalIncomeAfterBills(){
        float income = 0;
        for (var taxiPark : taxiParks){
            income += taxiPark.calculateIncomeAfterBills();
        }
        return income;
    }

    @Override
    public String toString() {
        return "TaxiCompany{" +
                "taxiParks=" + Arrays.toString(taxiParks) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxiCompany that = (TaxiCompany) o;
        return Arrays.equals(taxiParks, that.taxiParks);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(taxiParks);
    }

    public TaxiPark[] getTaxiParks() {
        return taxiParks;
    }

    public void setTaxiParks(TaxiPark[] taxiParks) {
        this.taxiParks = taxiParks;
    }
}
