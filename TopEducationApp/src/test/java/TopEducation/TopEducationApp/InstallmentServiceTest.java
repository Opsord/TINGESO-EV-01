package TopEducation.TopEducationApp;

import TopEducation.TopEducationApp.entities.InstallmentEntity;
import TopEducation.TopEducationApp.services.InstallmentService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

class InstallmentServiceTest {

    InstallmentService installmentService = new InstallmentService();


    @Test
        // Save an installment
    void saveInstallment() {
    InstallmentEntity installment = new InstallmentEntity();
    installment.setInstallmentRUT("TestRUT");
    installment.setInstallmentPaymentDate(LocalDate.parse("2021-01-01"));
    installment.setInstallmentStatus(0);
    installment.setInstallmentAmount(100000);
    installmentService.saveInstallment(installment);

    // Verify that the installment was saved
        ArrayList<InstallmentEntity> savedInstallment = installmentService.findAllByInstallmentRUT("TestRUT");
        assert(savedInstallment.size() == 1);
        assert(savedInstallment.get(0).getInstallmentRUT().equals("TestRUT"));
        assert(savedInstallment.get(0).getInstallmentPaymentDate().equals(LocalDate.parse("2021-01-01")));
        assert(savedInstallment.get(0).getInstallmentStatus() == 0);
        assert(savedInstallment.get(0).getInstallmentAmount() == 100000);

    // Delete the installment
        installmentService.deleteInstallment(installment);

    }

    @Test
        // Test for isInstallmentOverdue method
    void isInstallmentOverdue() {
        InstallmentEntity installment = new InstallmentEntity();
        installment.setInstallmentRUT("TestRUT");
        installment.setInstallmentPaymentDate(LocalDate.parse("2021-01-01"));
        installment.setInstallmentStatus(0);
        installment.setInstallmentAmount(100000);

        // Verify that the installment is not overdue
        assert(!installmentService.isInstallmentOverdue(installment));

        // Set the installment payment date to 1 month before the current date
        installment.setInstallmentPaymentDate(LocalDate.now().minusMonths(1));

        // Verify that the installment is overdue
        assert(installmentService.isInstallmentOverdue(installment));
    }
}