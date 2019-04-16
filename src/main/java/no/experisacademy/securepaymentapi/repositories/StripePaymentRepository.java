package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.StripePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StripePaymentRepository extends JpaRepository<StripePayment, Long> {

}
