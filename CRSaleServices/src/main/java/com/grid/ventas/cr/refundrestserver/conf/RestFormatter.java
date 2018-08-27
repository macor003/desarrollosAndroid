package com.grid.ventas.cr.refundrestserver.conf;

import com.becoblohm.cr.models.AnulDev;
import com.becoblohm.cr.models.Order;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.models.PaymentMethod;
import com.becoblohm.cr.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * Created by eve0017280 on 10/05/16.
 */
@Component
public class RestFormatter {

    public Transaction cleanOrder(Transaction transaction) {
        if (transaction != null) {
            Collection<Payment> payments = transaction.getPayments();
            if (payments != null) {
                for (Payment payment : payments) {
                    if (payment != null) {
                        PaymentMethod payMethod = payment.getPayMethod();
                        if (payMethod != null) {
                            PaymentMethod counterPart = payMethod.getCounterPart();
                            if (counterPart != null) {
                                counterPart.setCounterPart(null);
                            }
                        }
                    }
                }
            }
        }
        return transaction;
    }

    public void avoidRecursionAnulDev(AnulDev anulDev) {
        if (anulDev != null) {
            Transaction transaction = cleanOrder(anulDev.getSpecialOrder());
            anulDev.setSpecialOrder((Order) transaction);
            anulDev.setOriginalSale(cleanOrder(anulDev.getOriginalSale()));
        }
    }
}
