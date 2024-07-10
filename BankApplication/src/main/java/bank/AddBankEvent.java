package bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class AddBankEvent {
    private long accountNumber;
    private String bankName;
    private String message;
    private String customerName;
    private EventType eventType;

    public enum EventType {
        CREATE_ACCOUNT,
        DEPOSIT,
        WITHDRAW,
        TRANSFER_FUNDS
    }

}
