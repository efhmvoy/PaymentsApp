package ua.training.model.entity;

import java.io.Serializable;
import java.time.LocalDate;

public class CreditCard implements Serializable {

    private static final long serialVersionUID = 9194892729239475619L;

    private Long id;

    private Integer number;

    private LocalDate expireDate;

    private Integer CVV;

    @Override
    public String toString() {
        return "CreditCard{" +
                "id=" + id +
                ", number=" + number +
                ", expireDate=" + expireDate +
                ", CVV=" + CVV +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public Integer getCVV() {
        return CVV;
    }

    public void setCVV(Integer CVV) {
        this.CVV = CVV;
    }

    public static class Builder {

        private CreditCard creditCard;

        public Builder withId(Long id){
            creditCard.setId(id);
            return this;
        }

        public Builder withNumber(Integer number){
            creditCard.setNumber(number);
            return this;
        }

        public Builder withExpireDate(LocalDate expireDate){
            creditCard.setExpireDate(expireDate);
            return this;
        }

        public Builder withCVV(Integer CVV){
            creditCard.setCVV(CVV);
            return this;
        }

        public CreditCard build(){
            return creditCard;
        }
    }
}
