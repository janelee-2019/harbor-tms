package com.lee.tms.domain.drawing;

public enum DrawingStatus
{
    NEW("new"),
    DRAWING_COMPLETED("drawing_completed"),

    DRAFT_ISSUED("draft_issued"),
    DRAFT_RECEIVED("draft_received"),
    DRAFT_SUBMITTED("draft_submitted"),

    PROOFREAD_ISSUED("proofread_issued"),
    PROOFREAD_RECEIVED("proofread_received"),
    PROOFREAD_SUBMITTED("proofread_submitted"),

    QC_ISSUED("qc_issued"),
    QC_RECEIVED("qc_received"),
    QC_SUBMITTED("qc_submitted"),

    SQC_ISSUED("sqc_issued"),
    SQC_RECEIVED("sqc_received"),
    SQC_SUBMITTED("sqc_submitted");


    private final String value;

    DrawingStatus(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }

    public static DrawingStatus fromValue(String value)
    {
        for (DrawingStatus status : values())
        {
            if (status.value.equals(value))
            {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown drawing status: " + value);
    }
}
