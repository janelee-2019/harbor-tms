package com.lee.tms.infrastructure.lock;

import com.lee.tms.infrastructure.rest.RestCode;
import com.lee.tms.infrastructure.rest.RestException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
@Component
public class DrawingApiLockAspect
{
    private static final Lock DRAWING_OP_LOCK = new ReentrantLock(true);

    @Pointcut ("@annotation(com.lee.tms.infrastructure.lock.DrawingApiLock)")
    public void pointCut()
    {
    }

    @Around ("pointCut()")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable
    {
        DrawingApiLock annotation = getAnnotation(joinPoint);

        boolean locked = false;

        try
        {
            locked = DRAWING_OP_LOCK.tryLock(annotation.timeout(), annotation.unit());

            if (!locked)
            {
                throw new RestException(RestCode.SYS_BUSY);
            }

            return joinPoint.proceed();

        }
        catch (InterruptedException e)
        {
            Thread
                    .currentThread()
                    .interrupt();
            throw new RestException(RestCode.SYS_BUSY, e);

        }
        finally
        {
            if (locked)
            {
                DRAWING_OP_LOCK.unlock();
            }
        }
    }

    private DrawingApiLock getAnnotation(JoinPoint joinPoint)
    {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        return methodSignature
                .getMethod()
                .getAnnotation(DrawingApiLock.class);
    }
}
