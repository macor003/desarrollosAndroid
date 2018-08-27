package com.becoblohm.cr.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class PaymentMethodCategory {

    private int categoryId;

    private String categoryName;

    private int categoryPmProcessed;

    private int categoryPmCharged;

    private double categoryProcessedAmount;

    private double categoryCommitedAmount;

    private String categoryDifferenceQuantity;

    private String categoryDifferenceAmount;

    private ArrayList<HashMap<String, String>> categoryCurrencyDetails;

    public String getCategoryDifferenceQuantity() {
        if (getCategoryName().equalsIgnoreCase("efectivo") || getCategoryName().equalsIgnoreCase("dolares")) {
            return "0";
        } else {
            return String.valueOf(Math.abs(getCategoryPmProcessed() - getCategoryPmCharged()));
        }
    }

    public ArrayList<HashMap<String, String>> getCategoryCurrencyDetails() {
        return categoryCurrencyDetails;
    }

    public void setCategoryCurrencyDetails(ArrayList<HashMap<String, String>> categoryCurrencyDetails) {
        this.categoryCurrencyDetails = categoryCurrencyDetails;
    }

    public void setCategoryDifferenceQuantity(String categoryDifferenceQuantity) {
        this.categoryDifferenceQuantity = categoryDifferenceQuantity;
    }

    public String getCategoryDifferenceAmount() {
        return categoryDifferenceAmount;
    }

    public void setCategoryDifferenceAmount(String categoryDifferenceAmount) {
        this.categoryDifferenceAmount = categoryDifferenceAmount;
    }

    public PaymentMethodCategory() {
        this.setCategoryCommitedAmount(0.0);
        this.setCategoryCurrencyDetails(new ArrayList<HashMap<String, String>>());
        this.setCategoryDifferenceAmount("0");
        this.setCategoryDifferenceQuantity("0");
        this.setCategoryId(0);
        this.setCategoryName("");
        this.setCategoryPmCharged(0);
        this.setCategoryPmProcessed(0);
        this.setCategoryProcessedAmount(0.0);
    }

    public ArrayList<CategoryDetails> generateCategoryDetails() {

        ArrayList<CategoryDetails> resp = new ArrayList<CategoryDetails>();
        ArrayList<HashMap<String, String>> details = categoryCurrencyDetails;
        for (HashMap<String, String> hashMap : details) {
            CategoryDetails catDetail = new CategoryDetails(hashMap);
            resp.add(catDetail);
        }
        return resp;
    }

    public PaymentMethodCategory(int id) {
        this.categoryId = id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryPmProcessed() {
        return categoryPmProcessed;
    }

    public void setCategoryPmProcessed(int categoryPmProcessed) {
        this.categoryPmProcessed = categoryPmProcessed;
    }

    public int getCategoryPmCharged() {
        return categoryPmCharged;
    }

    public void setCategoryPmCharged(int categoryPmCharged) {
        this.categoryPmCharged = categoryPmCharged;
    }

    public double getCategoryProcessedAmount() {
        return categoryProcessedAmount;
    }

    public void setCategoryProcessedAmount(double categoryProcessedAmount) {
        this.categoryProcessedAmount = categoryProcessedAmount;
    }

    public double getCategoryCommitedAmount() {
        return categoryCommitedAmount;
    }

    public void setCategoryCommitedAmount(double categoryCommitedAmount) {
        this.categoryCommitedAmount = categoryCommitedAmount;
    }

    public static class CategoryDetails {

        private String name;

        private String SymbolCurrency;

        private Long Bills;

        private BigDecimal Amount;

        public CategoryDetails() {

        }

        public CategoryDetails(HashMap<String, String> details) {
            this.name = details.get("name");
            this.SymbolCurrency = details.get("symbolcurrency");
            this.Bills = new Long(details.get("bills"));

            this.Amount = new BigDecimal(details.get("amount").replace(",", ""));
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSymbolCurrency() {
            return SymbolCurrency;
        }

        public void setSymbolCurrency(String symbolCurrency) {
            SymbolCurrency = symbolCurrency;
        }

        public Long getBills() {
            return Bills;
        }

        public void setBills(Long bills) {
            Bills = bills;
        }

        public BigDecimal getAmount() {
            return Amount;
        }

        public void setAmount(BigDecimal amount) {
            Amount = amount;
        }
    }
}
