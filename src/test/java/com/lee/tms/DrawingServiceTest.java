package com.lee.tms;

import com.lee.tms.domain.drawing.DrawingStageConfig;
import com.lee.tms.infrastructure.auth.AuthUser;
import com.lee.tms.infrastructure.rest.RestException;
import com.lee.tms.modules.system.dto.vo.UserInfoVo;
import com.lee.tms.modules.system.service.UserService;
import com.lee.tms.modules.task.dto.body.DrawingTaskIssueBody;
import com.lee.tms.modules.task.dto.body.DrawingTaskSubmitPassedBody;
import com.lee.tms.modules.task.dto.body.DrawingTaskTransferBody;
import com.lee.tms.modules.task.entity.Drawing;
import com.lee.tms.modules.task.mapper.DrawingMapper;
import com.lee.tms.modules.task.service.impl.DrawingServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith (MockitoExtension.class)
class DrawingServiceTest
{

    @Spy
    @InjectMocks
    private DrawingServiceImpl drawingService;

    @Mock
    @SuppressWarnings ("unused")
    private DrawingMapper drawingMapper;

    @Mock
    private UserService userService;

    @Mock
    private Subject subject;

    // 模拟 Shiro 的主体上下文
    private MockedStatic<SecurityUtils> securityUtilsMockedStatic;

    private Drawing mockDrawing;
    private final Long drawingId = 100L;
    private final Long operatorId = 1L;
    private final Long userDrafter = 10L;
    private final Long userProofreader = 20L;
    private final Long userQc = 30L;
    private final Long userSqc = 40L;

    @BeforeEach
    void setUp()
    {
        // 防御性清理：如果前一个测试意外没有关闭静态 Mock，先进行关闭
        if (securityUtilsMockedStatic != null && !securityUtilsMockedStatic.isClosed()) {
            securityUtilsMockedStatic.close();
        }

        // 1. 静态 Mock Shiro 的 SecurityUtils
        securityUtilsMockedStatic = mockStatic(SecurityUtils.class);
        securityUtilsMockedStatic.when(SecurityUtils::getSubject).thenReturn(subject);

        // 2. 默认模拟当前登录用户为小组长 (minRoleLevel = 50L)
        AuthUser authUser = new AuthUser();
        authUser.setId(operatorId);
        authUser.setMinRoleLevel(AuthUser.LEVEL_GROUP_LEADER);
        lenient()
                .when(subject.getPrincipal())
                .thenReturn(authUser);

        mockDrawing = new Drawing();
        mockDrawing.setId(drawingId);
        mockDrawing.setDrawingNo("DWG-2026-001");

        lenient()
                .when(userService.getUserInfo(anyLong()))
                .thenReturn(new UserInfoVo());
        lenient()
                .doReturn(Collections.singletonList(mockDrawing))
                .when(drawingService)
                .listByIds(anyCollection());
        lenient()
                .doReturn(true)
                .when(drawingService)
                .updateBatchById(anyCollection());
    }

    @AfterEach
    void tearDown()
    {
        // 每个测试用例结束后，必须显式 close 释放当前线程的 static mock
        if (securityUtilsMockedStatic != null)
        {
            securityUtilsMockedStatic.close();
        }
    }

    @Test
    @DisplayName ("测试非领导用户操作他人任务时触发权限异常")
    void testNonLeaderOperateOtherTaskFails()
    {
        // 模拟切换为普通工程师用户 (minRoleLevel = 60L > 50L)[cite: 6]
        AuthUser normalUser = new AuthUser();
        normalUser.setId(999L);
        normalUser.setMinRoleLevel(AuthUser.LEVEL_ENGINEER);
        when(subject.getPrincipal()).thenReturn(normalUser);

        // 图纸指派给别人
        mockDrawing.setDrafterId(userDrafter);
        mockDrawing.setDraftIssuedDate(java.time.LocalDateTime.now());

        RestException exception = assertThrows(RestException.class, () -> drawingService.receiveDraftTask(List.of(drawingId)));
        assertEquals("只能操作自己的任务", exception.getMessage());
    }

    @Test
    @DisplayName ("覆盖项 1: 四个节点均可以独立下发")
    void testIndependentIssueAllNodes()
    {
        DrawingTaskIssueBody body = new DrawingTaskIssueBody();
        body.setIds(List.of(drawingId));

        // 未执行 DRAFT，直接下发 SQC/QC/PROOFREAD
        body.setUserId(userSqc);
        assertDoesNotThrow(() -> drawingService.issueSqcTask(body));
        assertEquals("sqc_issued", mockDrawing.getDrawingStatus());
        assertEquals(userSqc, mockDrawing.getSqcId());
        assertNotNull(mockDrawing.getSqcIssuedDate());

        body.setUserId(userQc);
        assertDoesNotThrow(() -> drawingService.issueQcTask(body));
        assertEquals("qc_issued", mockDrawing.getDrawingStatus());

        body.setUserId(userProofreader);
        assertDoesNotThrow(() -> drawingService.issueProofreadTask(body));
        assertEquals("proofread_issued", mockDrawing.getDrawingStatus());

        body.setUserId(userDrafter);
        assertDoesNotThrow(() -> drawingService.issueDraftTask(body));
        assertEquals("draft_issued", mockDrawing.getDrawingStatus());
    }

    @Test
    @DisplayName ("覆盖项 2: PROOFREAD 在 DRAFT 未提交时不能接收")
    void testProofreadReceiveFailsWhenDraftNotSubmitted()
    {
        DrawingTaskIssueBody issueBody = new DrawingTaskIssueBody();
        issueBody.setIds(List.of(drawingId));
        issueBody.setUserId(userProofreader);
        drawingService.issueProofreadTask(issueBody);

        RestException exception = assertThrows(RestException.class, () -> drawingService.receiveProofreadTask(List.of(drawingId)));
        assertTrue(exception
                           .getMessage()
                           .contains("上一节点 [绘图员] 尚未提交"));
    }

    @Test
    @DisplayName ("覆盖项 3: QC 在 PROOFREAD 未提交时不能接收")
    void testQcReceiveFailsWhenProofreadNotSubmitted()
    {
        issueReceiveSubmit(DrawingStageConfig.DRAFT, userDrafter);

        DrawingTaskIssueBody issueBody = new DrawingTaskIssueBody();
        issueBody.setIds(List.of(drawingId));
        issueBody.setUserId(userQc);
        drawingService.issueQcTask(issueBody);

        RestException exception = assertThrows(RestException.class, () -> drawingService.receiveQcTask(List.of(drawingId)));
        assertTrue(exception
                           .getMessage()
                           .contains("上一节点 [校对员] 尚未提交"));
    }

    @Test
    @DisplayName ("覆盖项 4: SQC 在 QC 未提交时不能接收")
    void testSqcReceiveFailsWhenQcNotSubmitted()
    {
        issueReceiveSubmit(DrawingStageConfig.DRAFT, userDrafter);
        issueReceiveSubmit(DrawingStageConfig.PROOFREAD, userProofreader);

        DrawingTaskIssueBody issueBody = new DrawingTaskIssueBody();
        issueBody.setIds(List.of(drawingId));
        issueBody.setUserId(userSqc);
        drawingService.issueSqcTask(issueBody);

        RestException exception = assertThrows(RestException.class, () -> drawingService.receiveSqcTask(List.of(drawingId)));
        assertTrue(exception
                           .getMessage()
                           .contains("上一节点 [初级质检员] 尚未提交"));
    }

    @Test
    @DisplayName ("覆盖项 5: 正常的 DRAFT -> PROOFREAD -> QC -> SQC 执行流程")
    void testNormalFullWorkflow()
    {
        issueReceiveSubmit(DrawingStageConfig.DRAFT, userDrafter);
        assertEquals("draft_submitted", mockDrawing.getDrawingStatus());

        issueReceiveSubmit(DrawingStageConfig.PROOFREAD, userProofreader);
        assertEquals("proofread_submitted", mockDrawing.getDrawingStatus());

        issueReceiveSubmit(DrawingStageConfig.QC, userQc);
        assertEquals("qc_submitted", mockDrawing.getDrawingStatus());

        issueReceiveSubmit(DrawingStageConfig.SQC, userSqc);
        assertEquals("sqc_submitted", mockDrawing.getDrawingStatus());
    }

    @Test
    @DisplayName ("覆盖项 6: 合法操作后 DrawingStatus、人员及时间字段正确更新")
    void testLegalOperationUpdatesFieldsCorrectly()
    {
        DrawingTaskIssueBody issueBody = new DrawingTaskIssueBody();
        issueBody.setIds(List.of(drawingId));
        issueBody.setUserId(userDrafter);

        drawingService.issueDraftTask(issueBody);
        assertEquals("draft_issued", mockDrawing.getDrawingStatus());
        assertEquals(userDrafter, mockDrawing.getDrafterId());
        assertEquals(operatorId, mockDrawing.getDraftIssuerId());
        assertNotNull(mockDrawing.getDraftIssuedDate());

        drawingService.receiveDraftTask(List.of(drawingId));
        assertEquals("draft_received", mockDrawing.getDrawingStatus());
        assertNotNull(mockDrawing.getDraftReceivedDate());

        drawingService.submitDraftTask(List.of(drawingId));
        assertEquals("draft_submitted", mockDrawing.getDrawingStatus());
        assertNotNull(mockDrawing.getDraftSubmittedDate());
    }

    @Test
    @DisplayName ("覆盖项 7: 非法操作不会修改图纸数据")
    void testIllegalOperationDoesNotModifyData()
    {
        String originalStatus = "draft_issued";
        mockDrawing.setDrawingStatus(originalStatus);
        mockDrawing.setDraftIssuedDate(null); // 未下发状态

        // 试图接收未下发的任务
        assertThrows(RestException.class, () -> drawingService.receiveDraftTask(List.of(drawingId)));

        // 校验原始数据没有发生任何改变
        assertEquals(originalStatus, mockDrawing.getDrawingStatus());
        assertNull(mockDrawing.getDraftReceivedDate());
    }

    // ==================== 辅助测试工具方法 ====================

    private void issueAndReceive(DrawingStageConfig stage, Long userId)
    {
        DrawingTaskIssueBody issueBody = new DrawingTaskIssueBody();
        issueBody.setIds(List.of(drawingId));
        issueBody.setUserId(userId);

        switch (stage)
        {
            case DRAFT ->
            {
                drawingService.issueDraftTask(issueBody);
                drawingService.receiveDraftTask(List.of(drawingId));
            }
            case PROOFREAD ->
            {
                drawingService.issueProofreadTask(issueBody);
                drawingService.receiveProofreadTask(List.of(drawingId));
            }
            case QC ->
            {
                drawingService.issueQcTask(issueBody);
                drawingService.receiveQcTask(List.of(drawingId));
            }
            case SQC ->
            {
                drawingService.issueSqcTask(issueBody);
                drawingService.receiveSqcTask(List.of(drawingId));
            }
        }
    }

    private void issueReceiveSubmit(DrawingStageConfig stage, Long userId)
    {
        issueAndReceive(stage, userId);

        DrawingTaskSubmitPassedBody submitBody = new DrawingTaskSubmitPassedBody();
        submitBody.setIds(List.of(drawingId));
        submitBody.setIsPassed(true);

        switch (stage)
        {
            case DRAFT -> drawingService.submitDraftTask(List.of(drawingId));
            case PROOFREAD -> drawingService.submitProofreadTask(submitBody);
            case QC -> drawingService.submitQcTask(submitBody);
            case SQC -> drawingService.submitSqcTask(submitBody);
        }
    }
}