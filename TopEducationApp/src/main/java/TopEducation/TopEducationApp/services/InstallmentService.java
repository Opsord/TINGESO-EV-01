package TopEducation.TopEducationApp.services;


import TopEducation.TopEducationApp.entities.InstallmentEntity;
import TopEducation.TopEducationApp.repositories.InstallmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service

public class InstallmentService {

    @Autowired
    private InstallmentRepository installmentRepository;

    // Get all the installments
    public ArrayList<InstallmentEntity> getAllInstallments() {
        return (ArrayList<InstallmentEntity>) installmentRepository.findAll();
    }

    // Save an installment
    public void saveInstallment(InstallmentEntity installment) {
        installmentRepository.save(installment);
    }

    // Delete an installment
    public void deleteInstallment(Long id) {
        try {
            installmentRepository.deleteById(id);
        } catch (Exception ignored) {
        }
    }

    // Delete all installments
    public void deleteAllInstallments() {
        try {
            installmentRepository.deleteAll();
        } catch (Exception ignored) {
        }
    }

    // Find by methods

    // Find by installmentRUT
    public InstallmentEntity findByInstallmentRut(String installmentRUT) {
        return installmentRepository.findByInstallmentRUT(installmentRUT);
    }

    // Find by installment amount
    public InstallmentEntity findByInstallmentAmount(int installmentAmount) {
        return installmentRepository.findByInstallmentAmount(installmentAmount);
    }

    // Find by installment date
    public InstallmentEntity findByInstallmentDate(LocalDate installmentDate) {
        return installmentRepository.findByInstallmentPaymentDate(installmentDate);
    }

    // Find by installment overdue status
    public InstallmentEntity findByInstallmentOverdue(int installmentOverdue) {
        return installmentRepository.findByInstallmentOverdueStatus(installmentOverdue);
    }

    // Find by installment overdue price
    public InstallmentEntity findByInstallmentOverduePrice(int installmentOverduePrice) {
        return installmentRepository.findByInstallmentOverduePrice(installmentOverduePrice);
    }

    // Find all installments by student RUT
    public ArrayList<InstallmentEntity> findAllByInstallmentRUT(String installmentRUT) {
        return (installmentRepository.findAllInstallmentsByRUT(installmentRUT));
    }

    // Find all paid installments by student RUT
    public ArrayList<InstallmentEntity> findAllPaidInstallmentsByRUT(String installmentRUT) {
        return (installmentRepository.findAllPaidInstallmentsByRUT(installmentRUT));
    }

    // Verify is an installment is overdue
    public boolean isInstallmentOverdue(InstallmentEntity installment) {
        // If the installment payment date is 1 month before the current date
        // and the installment status is 0 (unpaid), then the installment is overdue
        return installment.getInstallmentPaymentDate().isBefore(LocalDate.now().minusMonths(1)) && installment.getInstallmentStatus() == 0;
    }

    // Find all overdue installments by student RUT
    public ArrayList<InstallmentEntity> findAllOverdueInstallmentsByRUT(String installmentRUT) {
        return (installmentRepository.findAllOverdueInstallmentsByRUT(installmentRUT));
    }

    // Update installment overdue status
    public void updateInstallmentOverdueStatus(InstallmentEntity installment) {
        // If the installment is overdue, then set the installment overdue status to 1
        if (isInstallmentOverdue(installment)) {
            installment.setInstallmentOverdueStatus(1);
        }
    }

}
