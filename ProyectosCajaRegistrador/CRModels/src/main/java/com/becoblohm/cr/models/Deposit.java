/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import com.becoblohm.cr.interfaces.AbstractAuthorizableModel;
import com.becoblohm.cr.types.AuditTables;
import com.becoblohm.cr.types.CRBigDecimal;

/**
 */
public class Deposit extends AbstractAuthorizableModel {

    /**
     * Field serialVersionUID. (value is 1)
     */
    private static final long serialVersionUID = 1L;

    /**
     * Id del Abono en base de datos
     */
    private long id;

    /**
     * Datos de la session que guardo la transaccion
     */
    private Session session;

    /**
     * Contiene, en caso de que este abono sea de tipo anulacion, el abono que se esta
     * anulando
     */
    private Deposit originalDeposit;

    /**
     * Numero del Pedido especial
     */
    private long orderNumber;

    /**
     * Id del Pedido especial
     */
    private long orderId;

    /**
     * Id del Pedido especial
     */
    private long serverOrderId;

    /**
     * Numero del abono
     */
    private long number;

    /**
     * Fecha de emision del abono
     */
    private Date date;

    /**
     * Almacena el estado del abono. En caso de que {@link #type} sea anulacion, este
     * estado sera activo, mientras que el abono que se encuentra almacenado en
     * {@link #originalDeposit} tendra status anulado
     */
    private SourceStatus status;

    /**
     * Field fromPos.
     */
    private boolean fromPos;

    /**
     */
    public enum SourceStatus {
        /**
         * Field Active.
         */
        Active("A"),
        /**
         * Field Cancelleted.
         */
        Cancelleted("I"),
        /**
         * Field Pendint.
         */
        Pendint("P");

        /**
         * Field lookup.
         */
        private static final Map<String, SourceStatus> lookup = new HashMap<String, SourceStatus>();

        static {
            for (SourceStatus s : EnumSet.allOf(SourceStatus.class))
                lookup.put(s.getValue(), s);
        }

        /**
         * Field value.
         */
        private String value;

        /**
         * Constructor for SourceStatus.
         * 
         * @param value String
         */
        SourceStatus(String value) {
            this.setValue(value);
        }

        /**
         * Method get.
         * 
         * @param value String
         * @return SourceStatus
         */
        public static SourceStatus get(String value) {
            return lookup.get(value);
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Monto total del abono
     */
    private CRBigDecimal amount;

    /**
     * Monto del vuelto del abono
     */
    private CRBigDecimal difference;

    /**
     * Almacena el tipo del abono; en caso de una anulacion, esta sera de tipo anulacion,
     * mientras que la contenida en {@link #originalDeposit} sera de tipo abono
     */

    private SourceType type;

    /**
     */
    public enum SourceType {
        /**
         * Field Deposit.
         */
        Deposit("A"),
        /**
         * Field Cancelation.
         */
        Cancelation("N"),
        /**
         * Field Refund.
         */
        Refund("D");

        /**
         * Field lookup.
         */
        private static final Map<String, SourceType> lookup = new HashMap<String, SourceType>();

        static {
            for (SourceType s : EnumSet.allOf(SourceType.class))
                lookup.put(s.getValue(), s);
        }

        /**
         * Field value.
         */
        private String value;

        /**
         * Constructor for SourceType.
         * 
         * @param value String
         */
        SourceType(String value) {
            this.setValue(value);
        }

        /**
         * Method get.
         * 
         * @param value String
         * @return SourceType
         */
        public static SourceType get(String value) {
            return lookup.get(value);
        }

        /**
         * @param value the value to set
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 
         * @return the value
         */
        public String getValue() {
            return value;
        }
    }

    /**
     * Field isFromCancellationOrder.
     */
    private boolean isFromCancellationOrder = false;

    /**
     * Field store.
     */
    private String store;

    /**
     * Field posId.
     */
    private String posId;

    /**
     * Field user.
     */
    private User user;

    /**
     * Field client.
     */
    private Client client;

    /**
     * Field hour.
     */
    private String hour;

    /**
     * Method getId.
     * 
     * @return long
     */
    public long getId() {
        return id;
    }

    /**
     * Method setId.
     * 
     * @param id long
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Method getOrderNumber.
     * 
     * @return long
     */
    public long getOrderNumber() {
        return orderNumber;
    }

    /**
     * Method setOrderNumber.
     * 
     * @param orderId long
     */
    public void setOrderNumber(long orderId) {
        this.orderNumber = orderId;
    }

    /**
     * Almacena la lista de pagos del abono
     */
    private Collection<Payment> payments;

    /**
     * Constructor for Deposit.
     */
    public Deposit() {
        super(AuditTables.DEPOSIT);
        this.amount = CRBigDecimal.ZERO;
        this.difference = CRBigDecimal.ZERO;
        this.number = -1;
        this.payments = new ArrayList<Payment>();
        this.isFromCancellationOrder = false;
    }

    /**
     * Method getNumber.
     * 
     * @return long
     */
    public long getNumber() {
        return number;
    }

    /**
     * Method getHour.
     * 
     * @return String
     */
    public String getHour() {
        return hour;
    }

    /**
     * Method setHour.
     * 
     * @param hour String
     */
    public void setHour(String hour) {
        this.hour = hour;
    }

    /**
     * Method setNumber.
     * 
     * @param number long
     */
    public void setNumber(long number) {
        this.number = number;
    }

    /**
     * Method getSession.
     * 
     * @return Session
     */
    public Session getSession() {
        return session;
    }

    /**
     * Method setSession.
     * 
     * @param session Session
     */
    public void setSession(Session session) {
        this.session = session;
    }

    /**
     * Method getDate.
     * 
     * @return Date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Method setDate.
     * 
     * @param date Date
     */
    public void setDate(Date date) {
        this.date = date;
        SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
        hour = sdf2.format(date);
    }

    /**
     * Method getStatus.
     * 
     * @return SourceStatus
     */
    public SourceStatus getStatus() {
        return this.status;
    }

    /**
     * Method setStatus.
     * 
     * @param status SourceStatus
     */
    public void setStatus(SourceStatus status) {
        this.status = status;
    }

    /**
     * Method getAmount.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getAmount() {
        return amount;
    }

    /**
     * Method setAmount.
     * 
     * @param amount CRBigDecimal
     */
    public void setAmount(CRBigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Method getDifference.
     * 
     * @return CRBigDecimal
     */
    public CRBigDecimal getDifference() {
        return difference;
    }

    /**
     * Method setDifference.
     * 
     * @param difference CRBigDecimal
     */
    public void setDifference(CRBigDecimal difference) {
        this.difference = difference;
    }

    /**
     * Method getType.
     * 
     * @return SourceType
     */
    public SourceType getType() {
        return type;
    }

    /**
     * Method setType.
     * 
     * @param type SourceType
     */
    public void setType(SourceType type) {
        this.type = type;
    }

    /**
     * Method getOriginalDeposit.
     * 
     * @return Deposit
     */
    public Deposit getOriginalDeposit() {
        return originalDeposit;
    }

    /**
     * Method setOriginalDeposit.
     * 
     * @param originalDeposit Deposit
     */
    public void setOriginalDeposit(Deposit originalDeposit) {
        this.originalDeposit = originalDeposit;
    }

    /**
     * Method getPayments.
     * 
     * @return Collection<Payment>
     */
    public Collection<Payment> getPayments() {
        return payments;
    }

    /**
     * Method setPayments.
     * 
     * @param payments Collection<Payment>
     */
    public void setPayments(Collection<Payment> payments) {
        Collection<Payment> tmp = new ArrayList<Payment>();
        tmp = this.payments;
        this.payments = payments;
        CRBigDecimal tmpTotal = CRBigDecimal.ZERO;
        for (Payment pay : this.payments) {
            tmpTotal = tmpTotal.add(pay.getMoney().getLocalAmmount());
        }
        this.difference = this.amount.subtract(tmpTotal);
        this.fire("payments", tmp, payments);
    }

    /**
     * Method isFromCancellationOrder.
     * 
     * @return boolean
     */
    public boolean isFromCancellationOrder() {
        return isFromCancellationOrder;
    }

    /**
     * Method setFromCancellationOrder.
     * 
     * @param isFromCancellationOrder boolean
     */
    public void setFromCancellationOrder(boolean isFromCancellationOrder) {
        this.isFromCancellationOrder = isFromCancellationOrder;
    }

    /**
     * Method getStore.
     * 
     * @return String
     */
    public String getStore() {
        return store;
    }

    /**
     * Method setStore.
     * 
     * @param store String
     */
    public void setStore(String store) {
        this.store = store;
    }

    /**
     * Method getPosId.
     * 
     * @return String
     */
    public String getPosId() {
        return posId;
    }

    /**
     * Method setPosId.
     * 
     * @param posId String
     */
    public void setPosId(String posId) {
        this.posId = posId;
    }

    /**
     * Method getUser.
     * 
     * @return User
     */
    public User getUser() {
        return user;
    }

    /**
     * Method setUser.
     * 
     * @param user User
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Method getClient.
     * 
     * @return Client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Method setClient.
     * 
     * @param client Client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Method getOrderId.
     * 
     * @return long
     */
    public long getOrderId() {
        return orderId;
    }

    /**
     * Method setOrderId.
     * 
     * @param orderId long
     */
    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    /**
     * Method getServerOrderId.
     * 
     * @return long
     */
    public long getServerOrderId() {
        return serverOrderId;
    }

    /**
     * Method setServerOrderId.
     * 
     * @param serverOrderId long
     */
    public void setServerOrderId(long serverOrderId) {
        this.serverOrderId = serverOrderId;
    }

    /**
     * 
     * @return the fromPos
     */
    public boolean isFromPos() {
        return fromPos;
    }

    /**
     * @param fromPos the fromPos to set
     */
    public void setFromPos(boolean fromPos) {
        this.fromPos = fromPos;
    }

    @Override
    public String getAuditID() {
        return String.valueOf(this.orderId);
    }

}
