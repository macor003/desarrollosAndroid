package com.becoblohm.cr.types;

import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Iterator;

import com.becoblohm.cr.interfaces.Calculator;
import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Transaction;

public class TransactionCalculatorFiscal extends Calculator {

    /**
     * Method updateArticleCosts.
     * 
     * @param art Article
     */
    @Override
    public void updateArticleCosts(Article art) {
        CRBigDecimal taxRate;
        if (art.getTax() == null) {
            taxRate = CRBigDecimal.ZERO;
        } else if (art.getTax().getTaxRate().compareTo(CRBigDecimal.ZERO) != 0) {
            taxRate = art.getTax().getTaxRate();
        } else {
            taxRate = CRBigDecimal.ZERO;
        }
        double tmp = art.getItems().getValue().multiply(art.getTaxAmount().getValue()).doubleValue();
        CRBigDecimal taxtmp = new CRBigDecimal(tmp, 5); // (itemCost*(taxRate/100));
        art.setItemTaxedCost(art.getItemCost()
                .add(art.getItemCost().multiply(taxRate.divide(CRBigDecimal.HUNDRED))));

        art.setItemTaxedCost(new CRBigDecimal(art.getItemCost().getValue().add(art.getTaxAmount().getValue())
                .setScale(2, RoundingMode.DOWN).doubleValue()));
        art.setNonTaxedTotalCost(art.getItems().multiply(art.getItemCost()));
        art.setTotalTaxAmount(taxtmp);
        art.setTotalCost(taxtmp.add(art.getItems().multiply(art.getItemCost())));//
        art.setRebateAmount(art.getOriginalItemCost().subtract(art.getItemCost()));

    }

    /**
     * Method updateTransactionCosts.
     * 
     * @param trans Transaction
     */
    @Override
    public void updateTransactionCosts(Transaction trans) {
        CRBigDecimal tmpTotalCost = CRBigDecimal.ZERO;
        CRBigDecimal tmpBaseAmount = CRBigDecimal.ZERO;
        CRBigDecimal tmpTaxAmount = CRBigDecimal.ZERO;
        CRBigDecimal tmpTotalRebate = CRBigDecimal.ZERO;
        CRBigDecimal tmpArticleCount = CRBigDecimal.ZERO;
        String rbtdsc = "";
        if (trans.getArticles() != null || !trans.getArticles().isEmpty() || trans.getArticles().size() != 0) {
            HashMap<Double, HashMap> taxes = new HashMap<Double, HashMap>();
            for (Article art : trans.getArticles()) {
                HashMap<String, CRBigDecimal> tax_i = new HashMap<String, CRBigDecimal>();

                if (taxes.containsKey(art.getTax().getTaxRate().doubleValue())) {
                    tax_i = taxes.get(art.getTax().getTaxRate().doubleValue());

                    tax_i.put("valorimpuesto", art.getTotalTaxAmount().add(tax_i.get("valorimpuesto")));
                    tax_i.put("conimpuesto", art.getTotalCost().add(tax_i.get("conimpuesto")));
                    tax_i.put("sinimpuesto", art.getNonTaxedTotalCost().add(tax_i.get("sinimpuesto")));

                    taxes.put(art.getTax().getTaxRate().doubleValue(), tax_i);
                } else {
                    tax_i = new HashMap();

                    tax_i.put("valorimpuesto", art.getTotalTaxAmount());
                    tax_i.put("conimpuesto", art.getTotalCost());
                    tax_i.put("sinimpuesto", art.getNonTaxedTotalCost());

                    taxes.put(art.getTax().getTaxRate().doubleValue(), tax_i);
                }

                if (art.getDiscountType() != null && !art.getDiscountType().getName().equalsIgnoreCase("N")) {
                    String tmp = art.getDiscountType().getName() + ":" + art.getDiscountType().getDescription()
                            + " ";

                    if (!rbtdsc.contains(tmp)) {
                        rbtdsc = rbtdsc.concat(tmp);
                    }
                }

                tmpTotalRebate = tmpTotalRebate.add(art.getTotalRebateAmount());
                tmpArticleCount = tmpArticleCount.add(art.getItems());
            }

            for (Iterator iterator = taxes.keySet().iterator(); iterator.hasNext();) {
                Double taxvalue2 = (Double) iterator.next();
                HashMap<String, CRBigDecimal> tax_i = taxes.get(taxvalue2);
                CRBigDecimal taxvalue = new CRBigDecimal(taxvalue2);

                /*******************/
                CRBigDecimal nontaxedartcost = tax_i.get("sinimpuesto");
                CRBigDecimal taxedartcost = nontaxedartcost.multiply(taxvalue.divide(CRBigDecimal.HUNDRED));
                tmpTotalCost = tmpTotalCost.add(nontaxedartcost.add(taxedartcost));
                tmpBaseAmount = tmpBaseAmount.add(nontaxedartcost);
                tmpTaxAmount = tmpTaxAmount.add(taxedartcost);
                /*****************/
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

    }
}
