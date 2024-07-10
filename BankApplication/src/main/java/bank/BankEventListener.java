package bank;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class BankEventListener {

    @EventListener
    public void onEvent(AddBankEvent e) {
        switch (e.getEventType()) {
            case CREATE_ACCOUNT:
                handleCreateAccountEvent(e);
                break;
            case DEPOSIT:
                handleDepositEvent(e);
                break;
            case WITHDRAW:
                handleWithdrawEvent(e);
                break;
            case TRANSFER_FUNDS:
                handleTransferFundsEvent(e);
                break;
            default:
                System.out.println("Unknown event type: " + e.getEventType());
        }
    }

    private void handleCreateAccountEvent(AddBankEvent e) {
        System.out.println("*************************EVENT Listener for Create Account*************************************");
        System.out.println(e.toString());
    }

    private void handleDepositEvent(AddBankEvent e) {
        System.out.println("*************************EVENT Listener for Deposit*************************************");
        System.out.println(e.toString());
    }

    private void handleWithdrawEvent(AddBankEvent e) {
        System.out.println("*************************EVENT Listener for Withdraw*************************************");
        System.out.println(e.toString());
    }

    private void handleTransferFundsEvent(AddBankEvent e) {
        System.out.println("*************************EVENT Listener for Transfer Funds*************************************");
        System.out.println(e.toString());
    }
}
