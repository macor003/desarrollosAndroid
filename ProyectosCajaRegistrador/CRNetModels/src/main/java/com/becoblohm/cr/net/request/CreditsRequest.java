/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.net.request;

import com.becoblohm.cr.net.models.Session;

/**
 */
public class CreditsRequest extends RMIServerRequest {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * Field REQUEST_INFO.
     * (value is 0)
     */
    public static final int REQUEST_INFO = 0;
    /**
     * Field REGISTER_OPERATION.
     * (value is 1)
     */
    public static final int REGISTER_OPERATION = 1;
    /**
     * Field CHANGE_PASSWORD.
     * (value is 2)
     */
    public static final int CHANGE_PASSWORD = 2;
    /**
     * Field REPRINT.
     * (value is 3)
     */
    public static final int REPRINT = 3;
    /**
     * Field REQUEST_INFO_PASSWD.
     * (value is 4)
     */
    public static final int REQUEST_INFO_PASSWD = 4;
    /**
     * Field REQUEST_ROLLBACK.
     * (value is 5)
     */
    public static final char REQUEST_ROLLBACK = 'R';


    /**
     * Constructor for CreditsRequest.
     * @param i int
     * @param mov Object
     * @param session Session
     */
    public CreditsRequest(int i, Object mov, Session session) {
        super(i, mov, session);
    }

    /**
     * Constructor for CreditsRequest.
     *
     * @param i
     *            int
     * @param mov
     *            Object
     */
    public CreditsRequest(int i, Object mov) {
        super(i, mov, null);
    }
}
