/*******************************************************************************
 * © 2012 Global Retail Information Ltd.
 ******************************************************************************/
package com.becoblohm.cr.interfaces;

import com.becoblohm.cr.models.CreditsMovement;
import com.becoblohm.cr.net.response.CreditsResponse;
import com.epa.mvc.core.AbstractIOModel;

/**
 */
public abstract class CreditsClientInterface extends AbstractIOModel {

    /**
     * Method execute.
     * 
     * @param requestType int
     * @param data Object
     * @return CreditsResponse
     */
    public abstract CreditsResponse execute(int requestType, Object data);

    /**
     * Method executeRegisterOperation.
     * 
     * @param data Object
     * @return CreditsResponse
     */
    public abstract CreditsResponse executeRegisterOperation(Object data);

    /**
     * Method isOnLine.
     * 
     * @return boolean
     */
    public abstract boolean isOnLine();

    /**
     * Method isPasswordRequired.
     * 
     * @param movementRequest CreditsMovement
     * @return boolean
     */
    public abstract boolean isPasswordRequired(CreditsMovement movementRequest);

}
