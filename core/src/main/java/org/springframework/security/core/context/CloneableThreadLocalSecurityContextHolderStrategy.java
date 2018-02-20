/*
 * CloneableThreadLocalSecurityContextHolderStrategy.java
 *
 * created at 20.11.2014 by m.nikolov <m.nikolov@seeburger.de>
 *
 * Copyright (c) SEEBURGER AG, Germany. All Rights Reserved.
 */
package org.springframework.security.core.context;


import org.springframework.util.Assert;


/**
 * Makes {@code SecurityContextHolder} use {@link CloneableSecurityContext} by reusing
 * the idea of {@code ThreadLocalSecurityContextHolderStrategy}.
 * The only difference is that {@link CloneableThreadLocalSecurityContextHolderStrategy#createEmptyContext}
 * creates {@link CloneableSecurityContext}.
 *
 * @author m.nikolov
 */
public class CloneableThreadLocalSecurityContextHolderStrategy
    implements SecurityContextHolderStrategy
{

    private static final ThreadLocal<SecurityContext> threadContextHolder = new ThreadLocal<SecurityContext>();


    @Override
    public void clearContext()
    {
        threadContextHolder.remove();
    }


    @Override
    public SecurityContext getContext()
    {
        SecurityContext ctx = threadContextHolder.get();

        if (ctx == null)
        {
            ctx = createEmptyContext();
            threadContextHolder.set(ctx);
        }

        return ctx;
    }


    @Override
    public void setContext(SecurityContext context)
    {
        Assert.notNull(context, "Accepting only non-null SecurityContext instances.");
        threadContextHolder.set(context);
    }


    @Override
    public SecurityContext createEmptyContext()
    {
        return new CloneableSecurityContext();
    }

}