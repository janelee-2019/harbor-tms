package com.lee.tms.modules.system.dto.body;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class AuthLoginBody
{
    @NotBlank (message = "参数 [loginName] 不能为空")
    private String loginName;

    @NotBlank (message = "参数 [loginPassword] 不能为空")
    private String loginPassword;

    //    @NotBlank(message = "参数 [uuid] 不能为空")
    //    private String uuid;

    //    @NotBlank(message = "参数 [code] 不能为空")
    //    private String code;
}
