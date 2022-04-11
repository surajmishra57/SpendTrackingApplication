package com.incs.SpendTrackingApplication.request;

public class TopSpend {
    private String userEmail;
    private double totalSpend;

    public TopSpend() {
    }

    public TopSpend(String userEmail, double totalSpend) {
        this.userEmail = userEmail;
        this.totalSpend = totalSpend;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public void setTotalSpend(double totalSpend) {
        this.totalSpend = totalSpend;
    }

    @Override
    public String toString() {
        return "TopSpend{" +
                "userEmail='" + userEmail + '\'' +
                ", totalSpend=" + totalSpend +
                '}';
    }
}
