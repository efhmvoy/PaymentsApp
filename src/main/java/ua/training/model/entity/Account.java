package ua.training.model.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account implements Serializable {

    private static final long serialVersionUID = -1766480376628389597L;

    private Long id;

    private Long number;

    private String name;

    private BigDecimal balance;

    private AccountStatus accountStatus;

    private CreditCard creditCard;

    private List<Payment> paymentList = new ArrayList<>();

    private User user;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", accountStatus=" + accountStatus +
                ", creditCard=" + creditCard +
                ", paymentList=" + paymentList +
                ", user=" + user +
                '}';
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public static class Builder{

        private Account account;

        public Builder()
        {
            account =  new Account();
        }

        public Builder withId(Long id){
            account.setId(id);
            return this;
        }

        public Builder withName(String name){
            account.setName(name);
            return this;
        }

        public Builder withNumber(Long number){
            account.setNumber(number);
            return this;
        }



        public Builder withBalance(BigDecimal balance){
            account.setBalance(balance);
            return this;
        }

        public Builder withAccountStatus(AccountStatus accountStatus){
            account.setAccountStatus(accountStatus);
            return this;
        }

        public Builder withCreditCard(CreditCard creditCard){
            account.setCreditCard(creditCard);
            return this;
        }

        public Builder withPaymentList(List<Payment> paymentList){
            account.setPaymentList(paymentList);
            return this;
        }

        public Account build(){
            return account;
        }
    }
}
