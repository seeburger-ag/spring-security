/*
 * CloneableSecurityContext.java
 *
 * created at 22.08.2014 by Marcus Trautwig <m.trautwig@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package org.springframework.security.core.context;


import org.springframework.security.core.Authentication;


/**
 * A {@link SecurityContext} implementation which can be cloned. Required for request-specific copies of
 * {@link SecurityContext} for supporting concurrent requests with the same Session ID.
 */
public class CloneableSecurityContext
    extends SecurityContextImpl
    implements Cloneable
{

    private static final long serialVersionUID = 1L;


    /**
     * Creates a copy of this {@link SecurityContext}, initially pointing to the same {@link Authentication}
     */
    @Override
    public CloneableSecurityContext clone()
    {
        try
        {
            return (CloneableSecurityContext)super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException(e);
        }
    }
}
