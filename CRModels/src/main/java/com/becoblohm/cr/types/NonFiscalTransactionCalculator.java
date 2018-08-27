/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.types;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;

import com.becoblohm.cr.interfaces.Calculator;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Transaction;

public class NonFiscalTransactionCalculator extends Calculator {

    @Override
    public void updateArticleCosts(Article article) {
        // CRBigDecimal items = article.getItems();
        // CRBigDecimal itemCost = article.getItemCost();
        // CRBigDecimal taxRate = CRBigDecimal.ZERO;
        // CRBigDecimal taxMultiplyer = CRBigDecimal.ONE;
        // CRBigDecimal rebateAmount = CRBigDecimal.ZERO;
        //
        // if (article.getTax() != null) {
        // taxRate = article.getTax().getTaxRate();
        // taxMultiplyer = CRBigDecimal.ONE.add(taxRate
        // .divide(CRBigDecimal.HUNDRED));
        // }
        //
        // if (article.getDiscountType() != null) {
        // rebateAmount = new CRBigDecimal(article.getRebateAmount()
        // .doubleValue());
        // }
        //
        // CRBigDecimal itemTaxedCost = itemCost.multiply(taxMultiplyer);
        // CRBigDecimal taxedOriginal = itemTaxedCost.add(rebateAmount);
        // CRBigDecimal original = taxedOriginal.divide(taxMultiplyer);
        // CRBigDecimal nonTaxedTotal = itemCost.multiply(items);
        // CRBigDecimal total = itemTaxedCost.multiply(items);
        //
        // article.setItemTaxedCost(itemTaxedCost);
        // article.setOriginalItemCost(original);
        // article.setOriginalItemTaxedCost(taxedOriginal);
        // article.setRebateAmount(rebateAmount);
        // article.setTotalCost(total);
        // article.setNonTaxedTotalCost(nonTaxedTotal);

        CRBigDecimal taxRate, reverseTaxRate, originalItemTaxedPrice, taxAmount, nonTaxedCost;
        if (article.getTax() == null) {
            taxRate = CRBigDecimal.ZERO;
        } else if (article.getTax().getTaxRate().compareTo(CRBigDecimal.ZERO) != 0) {
            taxRate = article.getTax().getTaxRate();
        } else {
            taxRate = CRBigDecimal.ZERO;
        }

        article.setItemTaxedCost(calculateTaxedCost(article, taxRate));

        reverseTaxRate = new CRBigDecimal(calculateReverseTax(taxRate));
        nonTaxedCost = new CRBigDecimal(calculateNonTaxedCost(article, reverseTaxRate), 2);
        taxAmount = new CRBigDecimal(calculateTaxAmount(article, nonTaxedCost), 2);
        originalItemTaxedPrice = calculateOriginalCost(article);

        article.setTaxAmount(taxAmount);
        article.setOriginalItemTaxedCost(originalItemTaxedPrice);
        article.setNonTaxedTotalCost(article.getItems().multiply(nonTaxedCost));
        article.setTotalTaxAmount(new CRBigDecimal(calculateTotalTaxAmount(article, taxAmount), 2));
        article.setTotalCost(new CRBigDecimal(calculateTotalArticleCost(article)));
        article.setRebateAmount(calculateTotalRebateAmount(article, originalItemTaxedPrice));

        printArticleAmounts(article, taxAmount, nonTaxedCost);
    }

    private CRBigDecimal calculateTaxedCost(Article art, CRBigDecimal taxRate) {
        return art.getItemCost().add(art.getItemCost().multiply(taxRate.divide(CRBigDecimal.HUNDRED)));
    }

    private BigDecimal calculateTotalArticleCost(Article art) {
        return new BigDecimal(art.getItems().multiply(art.getItemTaxedCost()).doubleValue());
    }

    private BigDecimal calculateTotalTaxAmount(Article art, CRBigDecimal taxAmount) {
        return new BigDecimal(art.getItems().multiply(taxAmount).doubleValue());
    }

    private BigDecimal calculateTaxAmount(Article art, CRBigDecimal nonTaxedCost) {
        return new BigDecimal(art.getItemTaxedCost().subtract(nonTaxedCost).doubleValue());
    }

    private BigDecimal calculateNonTaxedCost(Article art, CRBigDecimal reverseTaxRate) {
        return new BigDecimal(art.getItemTaxedCost().divide(reverseTaxRate).doubleValue());
    }

    private BigDecimal calculateReverseTax(CRBigDecimal taxRate) {
        return new BigDecimal(1 + (taxRate.doubleValue() / 100));
    }

    private void printArticleAmounts(Article art, CRBigDecimal taxAmount, CRBigDecimal nonTaxedCost) {
        // if (art.getTax() != null) {
        // System.out.println("Precio total sin impuesto Articulo "
        // + art.getEpaCode() + ": "
        // + art.getItems().multiply(nonTaxedCost));
        // System.out.println("Costo Orginal del articulo sin impuesto articulo"
        // +art.getEpaCode()+" "+art.getOriginalItemCost()
        // +"Costo Original con Impuesto del articulo "
        // +art.getEpaCode()+" "+art.getOriginalItemTaxedCost());
        // System.out.println("Impuesto total del articulo" + art.getEpaCode()
        // + ": " + art.getItems().multiply(taxAmount));
        // System.out.println("Precio total Articulo" + " " + art.getEpaCode()
        // + ": " + art.getItems() + " x " + art.getItemTaxedCost()
        // + " =" + art.getTotalCost());
        // }
    }

    private CRBigDecimal calculateOriginalCost(Article art) {
        CRBigDecimal originalItemTaxAmount;
        CRBigDecimal originalItemTaxedPrice;
        if (art.getOriginalItemTax() != null) {
            originalItemTaxAmount = new CRBigDecimal(calculateOriginalTaxAmount(art), 2);

            originalItemTaxedPrice = new CRBigDecimal(
                    art.getOriginalItemCost().add(originalItemTaxAmount).doubleValue(), 2);
        } else {
            originalItemTaxedPrice = new CRBigDecimal(art.getItemTaxedCost().doubleValue());
            originalItemTaxAmount = CRBigDecimal.ZERO;
        }
        return originalItemTaxedPrice;
    }

    private double calculateOriginalTaxAmount(Article art) {
        return art.getOriginalItemCost().doubleValue() * art.getOriginalItemTax().getTaxRate().doubleValue() / 100;
    }

    private CRBigDecimal calculateTotalRebateAmount(Article art, CRBigDecimal originalTaxedPrice) {
        if (art.getTax() != null) {
            CRBigDecimal difference = new CRBigDecimal(
                    originalTaxedPrice.subtract(art.getItemTaxedCost()).doubleValue(), 2);
            return new CRBigDecimal(difference.doubleValue(), 5);
        } else {
            return new CRBigDecimal(originalTaxedPrice.subtract(art.getItemTaxedCost()).doubleValue(), 2);
        }
    }

    @Override
    public void updateTransactionCosts(Transaction trans) {
        CRBigDecimal tmpTotalCost = CRBigDecimal.ZERO;
        CRBigDecimal tmpBaseAmount = CRBigDecimal.ZERO;
        CRBigDecimal tmpTaxAmount = CRBigDecimal.ZERO;
        CRBigDecimal tmpTotalRebate = CRBigDecimal.ZERO;
        CRBigDecimal tmpArticleCount = CRBigDecimal.ZERO;
        String rbtdsc = "";
        if (trans.getArticles() != null || !trans.getArticles().isEmpty() || trans.getArticles().size() != 0) {
            HashMap<Double, HashMap> amountsByTaxRate = new HashMap<Double, HashMap>();
            for (Article art : trans.getArticles()) {
                HashMap<String, CRBigDecimal> currentAmounts = new HashMap<String, CRBigDecimal>();

                if (amountsByTaxRate.containsKey(art.getTax().getTaxRate().doubleValue())) {

                    currentAmounts = addArticleAmount(amountsByTaxRate, art);
                    amountsByTaxRate.put(art.getTax().getTaxRate().doubleValue(), currentAmounts);
                } else {

                    currentAmounts = getInitialArticleAmount(art);
                    amountsByTaxRate.put(art.getTax().getTaxRate().doubleValue(), currentAmounts);
                }

                rbtdsc = addRebatesApplied(rbtdsc, art);

                tmpTotalRebate = tmpTotalRebate.add(art.getPlainRebateAmount().multiply(art.getItems()));
                tmpArticleCount = tmpArticleCount.add(art.getItems());
                tmpTotalCost = tmpTotalCost.add(art.getTotalCost());
            }

            for (Iterator iterator = amountsByTaxRate.keySet().iterator(); iterator.hasNext();) {
                Double currentTaxRate = (Double) iterator.next();
                HashMap<String, CRBigDecimal> tax_i = amountsByTaxRate.get(currentTaxRate);

                CRBigDecimal nontaxedartcost = tax_i.get("sinimpuesto");
                CRBigDecimal taxedartcost = tax_i.get("valorimpuesto");

                tmpBaseAmount = tmpBaseAmount.add(nontaxedartcost);
                tmpTaxAmount = tmpTaxAmount.add(taxedartcost);
            }

        }
        trans.setTotalCost(tmpTotalCost);
        trans.setBaseAmount(tmpBaseAmount);
        trans.setTaxAmount(tmpTaxAmount);
        trans.setTotalRebate(tmpTotalRebate);
        trans.setArticlesCount(tmpArticleCount);
        if (rbtdsc.equals("")) {
            rbtdsc = null;
        }
        trans.setRebateDesc(rbtdsc);
        // printTransactionAmounts(tmpTotalCost, tmpBaseAmount, tmpTaxAmount);
    }

    private String addRebatesApplied(String rbtdsc, Article art) {
        boolean articleHasDiscount = art.getDiscountType() != null
                && !art.getDiscountType().getName().equalsIgnoreCase("N");
        String currentRebate = art.getDiscountType().getName() + ":" + art.getDiscountType().getDescription()
                + " ";
        boolean currentRebateNotApplied = !rbtdsc.contains(currentRebate);

        if (articleHasDiscount && currentRebateNotApplied) {
            rbtdsc = rbtdsc.concat(currentRebate);
        }
        return rbtdsc;
    }

    private HashMap<String, CRBigDecimal> getInitialArticleAmount(Article art) {
        HashMap<String, CRBigDecimal> currentAmounts;
        currentAmounts = new HashMap();

        currentAmounts.put("valorimpuesto", art.getTotalTaxAmount());
        currentAmounts.put("conimpuesto", art.getTotalCost());
        currentAmounts.put("sinimpuesto", art.getNonTaxedTotalCost());
        return currentAmounts;
    }

    private HashMap<String, CRBigDecimal> addArticleAmount(HashMap<Double, HashMap> amountsByTaxRate,
                                                           Article art) {

        HashMap<String, CRBigDecimal> currentAmounts;
        currentAmounts = amountsByTaxRate.get(art.getTax().getTaxRate().doubleValue());

        currentAmounts.put("valorimpuesto", art.getTotalTaxAmount().add(currentAmounts.get("valorimpuesto")));
        currentAmounts.put("conimpuesto", art.getTotalCost().add(currentAmounts.get("conimpuesto")));
        currentAmounts.put("sinimpuesto", art.getNonTaxedTotalCost().add(currentAmounts.get("sinimpuesto")));
        return currentAmounts;
    }

    private void printTransactionAmounts(CRBigDecimal tmpTotalCost, CRBigDecimal tmpBaseAmount,
                                         CRBigDecimal tmpTaxAmount) {
        // System.out.println("Total en Transaccion: " + tmpTotalCost);
        // System.out.println("Total en Base: " + tmpBaseAmount);
        // System.out.println("Total en Impuesto: " + tmpTaxAmount);
    }

}
