package com.incs.SpendTrackingApplication.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class QuantityDto {
    @NotNull(message = "Quantity Required")
    @Positive(message = "Value Must Be Positive")
    private int productQuantity;

    public QuantityDto() {
    }

    public QuantityDto(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
