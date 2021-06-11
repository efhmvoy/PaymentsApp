package ua.training.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment implements Serializable {

    private static final long serialVersionUID = -2176948818195080301L;

    private Long id;

    private Long number;

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    private LocalDateTime createTime;

    private LocalDateTime executeTime;

    private PaymentStatus paymentStatus;

    private BigDecimal amount;

    private Integer recieverAccount;

    private Account account;

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", number=" + number +
                ", createTime=" + createTime +
                ", executeTime=" + executeTime +
                ", paymentStatus=" + paymentStatus +
                ", amount=" + amount +
                ", recieverAccount=" + recieverAccount +
                ", account=" + account +
                '}';
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getRecieverAccount() {
        return recieverAccount;
    }

    public void setRecieverAccount(Integer recieverAccount) {
        this.recieverAccount = recieverAccount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(LocalDateTime executeTime) {
        this.executeTime = executeTime;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }





    public static class Builder {

        private Payment payment;

        public Builder withId(Long id){
            payment.setId(id);
            return this;
        }

        public Builder withNumber(Long number){
            payment.setNumber(number);
            return this;
        }

        public Builder withCreateTime(LocalDateTime createTime){
            payment.setCreateTime(createTime);
            return this;
        }

        public Builder withExecuteTime(LocalDateTime executeTime){
            payment.setExecuteTime(executeTime);
            return this;
        }

        public Builder withPaymentStatus(PaymentStatus paymentStatus){
            payment.setPaymentStatus(paymentStatus);
            return this;
        }

        public Builder withAmount(BigDecimal amount){
            payment.setAmount(amount);
            return this;
        }

        public Builder withRecieverAccount(Integer recieverAccount){
            payment.setRecieverAccount(recieverAccount);
            return this;
        }

        public Payment build(){
            return payment;
        }

    }

}
