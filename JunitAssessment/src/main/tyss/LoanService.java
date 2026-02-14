package tyss;

public class LoanService {

    public boolean isEligible(int age, double salary) {
        if (age >= 21 && age <= 60 && salary >= 25000) {
            return true;
        }
        return false;
    }


    public double calculateEMI(double loanAmount, int tenureYears) {
        if (loanAmount <= 0) {
            throw new IllegalArgumentException("Loan amount must be greater than zero");
        }
        if (tenureYears <= 0) {
            throw new IllegalArgumentException("Tenure must be greater than zero");
        }

        return loanAmount / (tenureYears * 12);
    }

    public String getLoanCategory(int creditScore) {
        if (creditScore >= 750) {
            return "Premium";
        } else if (creditScore >= 600) {
            return "Standard";
        } else {
            return "High Risk";
        }
    }
}


