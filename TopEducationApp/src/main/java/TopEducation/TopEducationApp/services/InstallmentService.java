package TopEducation.TopEducationApp.services;


import TopEducation.TopEducationApp.entities.InstallmentEntity;
import TopEducation.TopEducationApp.repositories.InstallmentRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class InstallmentService {

    @Autowired
    private InstallmentRepository installmentRepository;

    // Save an installment
    public void saveInstallment(InstallmentEntity installment) {
        installmentRepository.save(installment);
    }

    // Delete an installment by id
    public void deleteInstallment(Long id) {
        try {
            installmentRepository.deleteById(id);
        } catch (Exception ignored) {
        }
    }

    // Delete all installments
    @Generated
    public void deleteAllInstallments() {
        try {
            installmentRepository.deleteAll();
        } catch (Exception ignored) {
        }
    }

    // Find by methods

    // Find all installments by student RUT
    public List<InstallmentEntity> findAllByInstallmentRUT(String installmentRUT) {
        return (installmentRepository.findAllInstallmentsByRUT(installmentRUT));
    }

    // Find all paid installments by student RUT
    public List<InstallmentEntity> findAllPaidInstallmentsByRUT(String installmentRUT) {
        return (installmentRepository.findAllPaidInstallmentsByRUT(installmentRUT));
    }

    // Find all overdue installments by student RUT
    public List<InstallmentEntity> findAllOverdueInstallmentsByRUT(String installmentRUT) {
        return (installmentRepository.findAllOverdueInstallmentsByRUT(installmentRUT));
    }

    // Verify is an installment is overdue
    public boolean updateInstallmentOverdueStatus(InstallmentEntity installment) {
        LocalDate paymentDate = installment.getInstallmentPaymentDate();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.isAfter(paymentDate.plusMonths(1)) && installment.getInstallmentStatus() == 0) {
            installment.setInstallmentOverdueStatus(1);
            return true;
        }
        installment.setInstallmentOverdueStatus(0);
        return false;
    }

    // Verify overdue installments and update status given a student RUT
    public void updateInstallmentsOverdueStatusByRUT(String installmentRUT) {
        List<InstallmentEntity> installments = findAllByInstallmentRUT(installmentRUT);
        for (InstallmentEntity installment : installments) {
            updateInstallmentOverdueStatus(installment);
        }
    }

    // Change installment status to pay
    public void markInstallmentAsPAid(InstallmentEntity installment) {
        installment.setInstallmentStatus(1);
    }
}
