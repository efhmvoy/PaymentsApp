package ua.training.model.entity;

import java.util.List;

public class PaymentPage {

    private List<Payment> paymentList;

    private Integer page;

    private Integer pageSize;

    public List<Payment> getPaymentList() {
        return paymentList;
    }

    @Override
    public String toString() {
        return "PaymentPage{" +
                "paymentList=" + paymentList +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", total=" + total +
                '}';
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    private Integer total;
}
