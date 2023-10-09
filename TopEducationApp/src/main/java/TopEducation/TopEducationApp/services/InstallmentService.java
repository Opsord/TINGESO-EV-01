package TopEducation.TopEducationApp.services;


import TopEducation.TopEducationApp.entities.InstallmentEntity;
import TopEducation.TopEducationApp.repositories.InstallmentRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service

public class InstallmentService {

    @Autowired
    private InstallmentRepository installmentRepository;

    // Save an installment
    public void saveInstallment(InstallmentEntity installment) {
        installmentRepository.save(installment);
    }

    // Delete an installment
    public void deleteInstallment(InstallmentEntity installment) {
        installmentRepository.delete(installment);
    }

    // Find by methods

    // Find all installments by student RUT
    @Generated
    public ArrayList<InstallmentEntity> findAllByInstallmentRUT(String installmentRUT) {
        return (installmentRepository.findAllInstallmentsByRUT(installmentRUT));
    }

    // Find all paid installments by student RUT
    @Generated
    public ArrayList<InstallmentEntity> findAllPaidInstallmentsByRUT(String installmentRUT) {
        return (installmentRepository.findAllPaidInstallmentsByRUT(installmentRUT));
    }

    // Find all overdue installments by student RUT
    @Generated
    public ArrayList<InstallmentEntity> findAllOverdueInstallmentsByRUT(String installmentRUT) {
        return (installmentRepository.findAllOverdueInstallmentsByRUT(installmentRUT));
    }

    // Verify is an installment is overdue
    public boolean isInstallmentOverdue(InstallmentEntity installment) {
        // If the installment payment date is 1 month before the current date
        // and the installment status is 0 (unpaid), then the installment is overdue
        return installment.getInstallmentPaymentDate().isBefore(LocalDate.now().minusMonths(1)) && installment.getInstallmentStatus() == 0;
    }

    // Update installment overdue status
    public void updateInstallmentOverdueStatus(InstallmentEntity installment) {
        // If the installment is overdue, then set the installment overdue status to 1
        if (isInstallmentOverdue(installment)) {
            installment.setInstallmentOverdueStatus(1);
        }
    }

}
