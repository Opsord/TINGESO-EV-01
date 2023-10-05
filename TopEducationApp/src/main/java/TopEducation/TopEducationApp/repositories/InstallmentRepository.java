package TopEducation.TopEducationApp.repositories;

import TopEducation.TopEducationApp.entities.InstallmentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;

@Repository

public interface InstallmentRepository extends CrudRepository<InstallmentEntity, Long> {
    // Custom query's here

    // Custom query to find all installments for a given student and
    // save them in a list
    @Query("SELECT e FROM InstallmentEntity e WHERE e.installmentRUT = :installmentRUT")
    ArrayList<InstallmentEntity> findAllInstallmentsByRUT(@Param("installmentRUT") String installmentRUT);

    // Custom query to find all paid installments for a given student and
    // save them in a list
    @Query("SELECT e FROM InstallmentEntity e WHERE e.installmentRUT = :installmentRUT AND e.installmentStatus = 1")
    ArrayList<InstallmentEntity> findAllPaidInstallmentsByRUT(@Param("installmentRUT") String installmentRUT);

    // FindBy methods

    // Find by installment RUT
    InstallmentEntity findByInstallmentRUT(String installmentRUT);

    // Find by installment amount
    InstallmentEntity findByInstallmentAmount(int installmentAmount);

    // Find by installment status
    InstallmentEntity findByInstallmentStatus(int installmentStatus);

    // Find by installment payment date
    InstallmentEntity   findByInstallmentPaymentDate(LocalDate installmentPaymentDate);

}
