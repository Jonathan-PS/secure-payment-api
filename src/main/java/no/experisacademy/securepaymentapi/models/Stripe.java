package no.experisacademy.securepaymentapi.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "stripe_transaction")
public class Stripe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stripe_transaction_id")
    private long stripeTransactionId;
    
}
