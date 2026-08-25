package com.lee.tms.infrastructure.util;

public class PageUtil
{
    public static int getStart(int pageNo, int pageSize)
    {
        if (pageNo < 1)
        {
            pageNo = 1;
        }
        if (pageSize < 1)
        {
            pageSize = 0;
        }
        return (pageNo - 1) * pageSize;
    }

    public static int getEnd(int pageNo, int pageSize)
    {
        final int start = getStart(pageNo, pageSize);
        return getEndByStart(start, pageSize);
    }

    private static int getEndByStart(int start, int pageSize)
    {
        if (pageSize < 1)
        {
            pageSize = 0;
        }
        return start + pageSize;
    }

    public static int[] toStartEnd(int pageNo, int pageSize)
    {
        final int start = getStart(pageNo, pageSize);
        return new int[]{start, getEndByStart(start, pageSize)};
    }
}
