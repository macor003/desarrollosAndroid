/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.utils.report;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.becoblohm.cr.models.Article;
import com.becoblohm.cr.models.Client;
import com.becoblohm.cr.models.Payment;
import com.becoblohm.cr.types.CRBigDecimal;

public interface ReportPrintingBehaviour {

	public ArrayList<String> printDetail(
			Map<String, ReportsDetailsContainer> detailsMap,
			CRBigDecimal totalDonation, CRBigDecimal creditsTotal,
			String currencySymbol);
		
	public boolean isTaxesLeyendVisible();
	
	public String printFinalArticleCost(Article article);
	
	public boolean salesLabelVisible();
	
	public boolean isCancellationTotalsVisible();
	
	public boolean isTotalsSeparatorVisible();
	
	public boolean printRightBoldTotal();
	
	public Collection<Payment> regroupPayments(Collection<Payment> originalPaymentOrder);
	
	public Client changeClientDisplayInfo(Client originalClient);	
	
	public boolean activateElectronicJournal();
	
}
