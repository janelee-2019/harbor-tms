package com.lee.tms.infrastructure.log;

import java.lang.annotation.*;

@Target (ElementType.METHOD)
@Retention (RetentionPolicy.RUNTIME)
@Documented
public @interface ApiLog
{
    String module();

    String tag();
}
