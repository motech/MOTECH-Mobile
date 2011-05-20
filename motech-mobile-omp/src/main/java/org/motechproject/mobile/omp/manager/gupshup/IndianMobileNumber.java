package org.motechproject.mobile.omp.manager.gupshup;

public class IndianMobileNumber {
    private String number;
    private static final String INDIA_CODE = "91";


    public IndianMobileNumber(String number) {
        this.number = format(number) ;
    }

    private String format(String number) {
        if(number.startsWith(INDIA_CODE)){
            number = number.substring(2);
        }
        if(number.startsWith("0")){
            number = number.substring(1);
        }
        return INDIA_CODE + number;
    }

    @Override
    public String toString() {
        return number;
    }
}
