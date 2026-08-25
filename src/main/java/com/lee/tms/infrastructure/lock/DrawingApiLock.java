package com.lee.tms.infrastructure.lock;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

@Target (ElementType.METHOD)
@Retention (RetentionPolicy.RUNTIME)
@Documented
public @interface DrawingApiLock
{
    long timeout();

    TimeUnit unit();
}
