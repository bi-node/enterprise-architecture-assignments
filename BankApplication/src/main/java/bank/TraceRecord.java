package bank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TraceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    private long accountNumber;
    private String operation;
    private double amount;

    public TraceRecord(LocalDateTime dateTime, long accountNumber, String operation, double amount) {
        this.dateTime = dateTime;
        this.accountNumber = accountNumber;
        this.operation = operation;
        this.amount = amount;
    }


}
