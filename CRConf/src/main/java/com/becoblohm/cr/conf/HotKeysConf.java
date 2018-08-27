/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.conf;

import java.util.HashMap;
import java.util.Map;

import com.epa.mvc.core.AbstractEvent;

/**
 */
public class HotKeysConf {

    /**
     * Field Hotkeys.
     */
    public static Map<String, AbstractEvent> Hotkeys = new HashMap<String, AbstractEvent>();

    static {
        Hotkeys.put("A", Events.ACTION_EVENT_CLIENT);
        Hotkeys.put("P", Events.ACTION_EVENT_PAYMENT_BUTTON);
        Hotkeys.put("U", Events.ACTION_EVENT_QUERY);
        Hotkeys.put("R", Events.ACTION_EVENT_REBATE);
        Hotkeys.put("I", Events.ACTION_EVENT_RETENTION);
        Hotkeys.put("X", Events.ACTION_EVENT_TAXDEDUCTION);
        Hotkeys.put("V", Events.ACTION_EVENT_WAITING_SALE);
        Hotkeys.put("G", Events.ACTION_EVENT_DRAWER);
        Hotkeys.put("Q", Events.ACTION_EVENT_LOCKPOS);
        Hotkeys.put("O", Events.ACTION_EVENT_VPOSOPERATIONS_CALL);
        Hotkeys.put("C", Events.ACTION_EVENT_CREDITS);
        Hotkeys.put("F", Events.ACTION_EVENT_CREDITSEPA);
        Hotkeys.put("D", Events.ACTION_EVENT_REFUND);
        Hotkeys.put("N", Events.ACTION_EVENT_CANCELLATION_BUTTON);
        Hotkeys.put("M", Events.ACTION_EVENT_REPRINT_CALL);
        Hotkeys.put("L", Events.ACTION_EVENT_AUDITROLL);
        Hotkeys.put("Z", Events.ACTION_EVENT_CLOSURE_DAY);
        Hotkeys.put("B", Events.ACTION_EVENT_INIT_SALE);
        Hotkeys.put("S", Events.ACTION_EVENT_REQUEST_CHANGE_AUTHINFO);
        Hotkeys.put("T", Events.ACTION_EVENT_SELECT_ARTICLES);
        Hotkeys.put("E", Events.ACTION_EVENT_CASHIER);
        Hotkeys.put("J", Events.ACTION_EVENT_SELECTION_DELIVERY);
        Hotkeys.put("F1", Events.ACTION_EVENT_SHOWMENU);
        Hotkeys.put("F12", Events.ACTION_EVENT_REQUEST_APP_STATUS);
    }
}
