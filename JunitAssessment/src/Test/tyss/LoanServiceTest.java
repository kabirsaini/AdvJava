package tyss;

import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class LoanServiceTest {

    LoanService loanService = new LoanService();


    @Test
    void testValidEligibility() {
        assertTrue(loanService.isEligible(30, 50000));
    }

    @Test
    void testInvalidAgeBelow(){
        assertFalse(loanService.isEligible(23,50000));
    }

    @Test
    void testInvalidAgeAbove(){
        assertFalse(loanService.isEligible(61,50000));
    }

    @Test
    void testInvalidSalary(){
        assertFalse(loanService.isEligible(30,20000));
    }

    @Test
    void testBoundaryEligibility() {
        assertTrue(loanService.isEligible(21, 25000));
        assertTrue(loanService.isEligible(60, 25000));
    }

    @Test
    void testCalculateEMI() {
        double emi = loanService.calculateEMI(120000, 1);
        assertEquals(10000, emi);
    }

    @Test
    void testInvalidLoanAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> loanService.calculateEMI(0, 1));
    }


    @Test
    void testInvalidTenure() {
        assertThrows(IllegalArgumentException.class,
                () -> loanService.calculateEMI(100000, 0));
    }

    @Test
    void testLoanCategories() {
        assertAll(
                () -> assertEquals("Premium", loanService.getLoanCategory(800)),
                () -> assertEquals("Standard", loanService.getLoanCategory(700)),
                () -> assertEquals("High Risk", loanService.getLoanCategory(500))
        );
    }

    @Test
    void testLoanCategoryNotNull() {
        assertNotNull(loanService.getLoanCategory(650));
    }


}
