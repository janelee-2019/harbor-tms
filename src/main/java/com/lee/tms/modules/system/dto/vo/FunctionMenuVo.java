package com.lee.tms.modules.system.dto.vo;

import cn.hutool.core.bean.BeanUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.lee.tms.infrastructure.rest.vo.TreeVo;
import com.lee.tms.modules.system.entity.Function;
import com.lee.tms.modules.system.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public final class FunctionMenuVo extends TreeVo<FunctionMenuVo, Long>
{
    @JsonProperty ("path")
    private String vuePath;

    @JsonProperty ("component")
    private String vueComponent;

    @JsonProperty ("redirect")
    private String vueRedirect;

    @JsonProperty ("name")
    private String vueName;

    @JsonIgnore
    private Long functionSort;

    private FunctionMenuMetaVo meta;

    public static FunctionMenuVo fromModel(Function function)
    {
        FunctionMenuVo vo = new FunctionMenuVo();
        BeanUtil.copyProperties(function, vo);

        FunctionMenuMetaVo metaVo = new FunctionMenuMetaVo();
        metaVo.title = function.getVueTitle();
        metaVo.icon = function.getVueIcon();
        metaVo.affix = function.getVueAffix();
        metaVo.badge = function.getVueBadge();
        metaVo.permissions = function.getRoles().stream().map(Role::getRoleKey).collect(Collectors.toList());

        vo.meta = metaVo;

        return vo;
    }

    @Getter
    @Setter
    private static class FunctionMenuMetaVo
    {
        private String title;

        private String icon;

        private String affix;

        private String badge;

        private List<String> permissions;
    }
}
