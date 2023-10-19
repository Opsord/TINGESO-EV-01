package TopEducation.TopEducationApp;

import TopEducation.TopEducationApp.entities.InstallmentEntity;
import TopEducation.TopEducationApp.services.InstallmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InstallmentServiceTest {

    @Autowired
    InstallmentService installmentService;

    @Test
        // Saving and deleting an installment
    void saveAndDeleteInstallment() {
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
        installmentService.deleteInstallment(installment.getId());

        // Verify that the installment was deleted
        ArrayList<InstallmentEntity> deletedInstallment = installmentService.findAllByInstallmentRUT("TestRUT");
        assert(deletedInstallment.isEmpty());
    }

    @Test
        // Test for findAllByInstallmentRUT method
    void findAllByInstallmentRUT() {
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
        installmentService.deleteInstallment(installment.getId());
    }

    @Test
        // Test for findAllPaidInstallmentsByRUT method
    void findAllPaidInstallmentsByRUT() {
        InstallmentEntity unpaidInstallment = new InstallmentEntity();
        unpaidInstallment.setInstallmentRUT("TestRUT");
        unpaidInstallment.setInstallmentPaymentDate(LocalDate.parse("2021-01-01"));
        unpaidInstallment.setInstallmentStatus(0);
        unpaidInstallment.setInstallmentAmount(100000);
        installmentService.saveInstallment(unpaidInstallment);

        InstallmentEntity paidInstallment = new InstallmentEntity();
        paidInstallment.setInstallmentRUT("TestRUT");
        paidInstallment.setInstallmentPaymentDate(LocalDate.parse("2021-01-01"));
        paidInstallment.setInstallmentStatus(1);
        paidInstallment.setInstallmentAmount(100000);
        installmentService.saveInstallment(paidInstallment);

        // Get all paid installments
        ArrayList<InstallmentEntity> paidInstallments = installmentService.findAllPaidInstallmentsByRUT("TestRUT");

        // Verify that there is only one paid installment
        assert(paidInstallments.size() == 1);
        assert(paidInstallments.get(0).getInstallmentRUT().equals("TestRUT"));
        assert(paidInstallments.get(0).getInstallmentPaymentDate().equals(LocalDate.parse("2021-01-01")));
        assert(paidInstallments.get(0).getInstallmentStatus() == 1);
        assert(paidInstallments.get(0).getInstallmentAmount() == 100000);


        // Delete the installments
        installmentService.deleteInstallment(unpaidInstallment.getId());
        installmentService.deleteInstallment(paidInstallment.getId());
    }

    @Test
        // Test for findAllOverdueInstallmentsByRUT method
    void findAllOverdueInstallmentsByRUT() {
        InstallmentEntity unpaidInstallment = new InstallmentEntity();
        unpaidInstallment.setInstallmentRUT("TestRUT");
        unpaidInstallment.setInstallmentPaymentDate(LocalDate.parse("2023-10-01"));
        unpaidInstallment.setInstallmentStatus(0);
        unpaidInstallment.setInstallmentOverdueStatus(0);
        unpaidInstallment.setInstallmentAmount(100000);
        installmentService.saveInstallment(unpaidInstallment);

        InstallmentEntity unpaidInstallment2 = new InstallmentEntity();
        unpaidInstallment2.setInstallmentRUT("TestRUT");
        unpaidInstallment2.setInstallmentPaymentDate(LocalDate.parse("2023-01-01"));
        unpaidInstallment2.setInstallmentStatus(0);
        unpaidInstallment2.setInstallmentOverdueStatus(1);
        unpaidInstallment2.setInstallmentAmount(100000);
        installmentService.saveInstallment(unpaidInstallment2);

        // Get all overdue installments
        ArrayList<InstallmentEntity> overdueInstallments = installmentService.findAllOverdueInstallmentsByRUT("TestRUT");

        // Verify that there is only one overdue installment
        assert(overdueInstallments.get(0).getInstallmentRUT().equals("TestRUT"));
        assert(overdueInstallments.get(0).getInstallmentPaymentDate().equals(LocalDate.parse("2023-01-01")));
        assert(overdueInstallments.get(0).getInstallmentStatus() == 0);
        assert(overdueInstallments.get(0).getInstallmentOverdueStatus() == 1);
        assert(overdueInstallments.get(0).getInstallmentAmount() == 100000);

        // Delete the installments
        installmentService.deleteInstallment(unpaidInstallment.getId());
        installmentService.deleteInstallment(unpaidInstallment2.getId());
    }

    @Test
        // Test for isInstallmentOverdue method
    void isInstallmentOverdueFalse() {
        InstallmentEntity installment = new InstallmentEntity();
        installment.setInstallmentRUT("TestRUT");
        installment.setInstallmentPaymentDate(LocalDate.of(2023, 10, 1));
        installment.setInstallmentStatus(0);
        installment.setInstallmentOverdueStatus(0);
        installment.setInstallmentOverduePrice(150000);
        installment.setInstallmentAmount(100000);

        installmentService.saveInstallment(installment);
        assertFalse(installmentService.updateInstallmentOverdueStatus(installment));
        // Find the installment by RUT and delete it
        ArrayList<InstallmentEntity> savedInstallment = installmentService.findAllByInstallmentRUT("TestRUT");
        installmentService.deleteInstallment(savedInstallment.get(0).getId());
    }

    @Test
        // Test for isInstallmentOverdue method
    void isInstallmentOverdueTrue() {
        InstallmentEntity installment = new InstallmentEntity();
        installment.setInstallmentRUT("TestRUT");
        installment.setInstallmentPaymentDate(LocalDate.of(2021, 10, 1));
        installment.setInstallmentStatus(0);
        installment.setInstallmentOverdueStatus(0);
        installment.setInstallmentOverduePrice(150000);
        installment.setInstallmentAmount(100000);

        installmentService.saveInstallment(installment);
        assertTrue(installmentService.updateInstallmentOverdueStatus(installment));
        // Find the installment by RUT and delete it
        ArrayList<InstallmentEntity> savedInstallment = installmentService.findAllByInstallmentRUT("TestRUT");
        installmentService.deleteInstallment(savedInstallment.get(0).getId());
    }

    @Test
        // Test for updateInstallmentStatus method
    void markInstallmentAsPAid() {
        InstallmentEntity installment = new InstallmentEntity();
        installment.setInstallmentRUT("TestRUT");
        installment.setInstallmentPaymentDate(LocalDate.of(2021, 10, 1));
        installment.setInstallmentStatus(0);
        installment.setInstallmentOverdueStatus(0);
        installment.setInstallmentOverduePrice(150000);
        installment.setInstallmentAmount(100000);

        installmentService.saveInstallment(installment);
        // Find the installment by RUT
        ArrayList<InstallmentEntity> savedInstallment = installmentService.findAllByInstallmentRUT("TestRUT");
        installmentService.markInstallmentAsPAid(savedInstallment.get(0));

        assertEquals(savedInstallment.get(0).getInstallmentStatus(), 1);
        installmentService.deleteInstallment(savedInstallment.get(0).getId());

    }

}