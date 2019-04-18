package no.experisacademy.securepaymentapi.repositories;

import no.experisacademy.securepaymentapi.models.StripeChargeRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StripeChargeRepository extends JpaRepository<StripeChargeRequest, Long> {
    List<StripeChargeRequest> findAll();
}
