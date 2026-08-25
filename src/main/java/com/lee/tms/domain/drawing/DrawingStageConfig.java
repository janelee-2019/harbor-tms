package com.lee.tms.domain.drawing;

import com.lee.tms.modules.task.entity.Drawing;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Getter
@AllArgsConstructor
public enum DrawingStageConfig {

    DRAFT(
            "draft",
            "绘图员",
            null, // 无前置节点
            Drawing::getDrafterId,
            Drawing::setDrafterId,
            Drawing::setDraftIssuerId,
            Drawing::getDraftIssuedDate,
            Drawing::setDraftIssuedDate,
            Drawing::getDraftReceivedDate,
            Drawing::setDraftReceivedDate,
            Drawing::getDraftSubmittedDate,
            Drawing::setDraftSubmittedDate
    ),

    PROOFREAD(
            "proofread",
            "校对员",
            DRAFT, // 前置节点为 DRAFT
            Drawing::getProofreaderId,
            Drawing::setProofreaderId,
            Drawing::setProofreadIssuerId,
            Drawing::getProofreadIssuedDate,
            Drawing::setProofreadIssuedDate,
            Drawing::getProofreadReceivedDate,
            Drawing::setProofreadReceivedDate,
            Drawing::getProofreadSubmittedDate,
            Drawing::setProofreadSubmittedDate
    ),

    QC(
            "qc",
            "初级质检员",
            PROOFREAD, // 前置节点为 PROOFREAD
            Drawing::getQcId,
            Drawing::setQcId,
            Drawing::setQcIssuerId,
            Drawing::getQcIssuedDate,
            Drawing::setQcIssuedDate,
            Drawing::getQcReceivedDate,
            Drawing::setQcReceivedDate,
            Drawing::getQcSubmittedDate,
            Drawing::setQcSubmittedDate
    ),

    SQC(
            "sqc",
            "高级质检员",
            QC, // 前置节点为 QC
            Drawing::getSqcId,
            Drawing::setSqcId,
            Drawing::setSqcIssuerId,
            Drawing::getSqcIssuedDate,
            Drawing::setSqcIssuedDate,
            Drawing::getSqcReceivedDate,
            Drawing::setSqcReceivedDate,
            Drawing::getSqcSubmittedDate,
            Drawing::setSqcSubmittedDate
    );

    public static final String ISSUED = "issued";
    public static final String RECEIVED = "received";
    public static final String SUBMITTED = "submitted";

    private final String code;
    private final String roleName;
    private final DrawingStageConfig previousStage;

    private final Function<Drawing, Long> assigneeGetter;
    private final BiConsumer<Drawing, Long> assigneeSetter;
    private final BiConsumer<Drawing, Long> issuerSetter;

    private final Function<Drawing, LocalDateTime> issuedDateGetter;
    private final BiConsumer<Drawing, LocalDateTime> issuedDateSetter;

    private final Function<Drawing, LocalDateTime> receivedDateGetter;
    private final BiConsumer<Drawing, LocalDateTime> receivedDateSetter;

    private final Function<Drawing, LocalDateTime> submittedDateGetter;
    private final BiConsumer<Drawing, LocalDateTime> submittedDateSetter;

    public String status(String action) {
        return code + "_" + action;
    }

    /**
     * 获取当前节点的下一个节点
     */
    public DrawingStageConfig getNextStage() {
        switch (this) {
            case DRAFT: return PROOFREAD;
            case PROOFREAD: return QC;
            case QC: return SQC;
            default: return null;
        }
    }
}