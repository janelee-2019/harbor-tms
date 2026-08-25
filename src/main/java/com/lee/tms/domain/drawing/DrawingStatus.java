package com.lee.tms.domain.drawing;

public enum DrawingStatus
{
    NEW("new"),
    DRAWING_CANCELLED("drawing_cancelled"),
    DRAWING_COMPLETED("drawing_completed"),

    DRAFT_ISSUED("draft_issued"),
    DRAFT_RECEIVED("draft_received"),
    DRAFT_TRANSFERRED("draft_transferred"),
    DRAFT_SUBMITTED("draft_submitted"),
    DRAFT_CANCELLED("draft_cancelled"),

    PROOFREAD_ISSUED("proofread_issued"),
    PROOFREAD_RECEIVED("proofread_received"),
    PROOFREAD_TRANSFERRED("proofread_transferred"),
    PROOFREAD_SUBMITTED("proofread_submitted"),
    PROOFREAD_CANCELLED("proofread_cancelled"),
    PROOFREAD_RETURNED("proofread_returned"),

    QC_ISSUED("qc_issued"),
    QC_RECEIVED("qc_received"),
    QC_TRANSFERRED("qc_transferred"),
    QC_SUBMITTED("qc_submitted"),
    QC_CANCELLED("qc_cancelled"),
    QC_RETURNED("qc_returned"),

    SQC_ISSUED("sqc_issued"),
    SQC_RECEIVED("sqc_received"),
    SQC_TRANSFERRED("sqc_transferred"),
    SQC_SUBMITTED("sqc_submitted"),
    SQC_CANCELLED("sqc_cancelled"),
    SQC_RETURNED("sqc_returned");


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
