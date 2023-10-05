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

    // Find all installments by student RUT
    public ArrayList<InstallmentEntity> findAllByInstallmentRUT(String installmentRUT) {
        return (ArrayList<InstallmentEntity>) installmentRepository.findAllInstallmentsByRUT(installmentRUT);
    }

    // Find all paid installments by student RUT
    public ArrayList<InstallmentEntity> findAllPaidInstallmentsByRUT(String installmentRUT) {
        return (ArrayList<InstallmentEntity>) installmentRepository.findAllPaidInstallmentsByRUT(installmentRUT);
    }

}
