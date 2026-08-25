package com.lee.tms.modules.system.controller;

import com.lee.tms.infrastructure.rest.R;
import com.lee.tms.modules.system.dto.query.DictDataListQuery;
import com.lee.tms.modules.system.service.DictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 字典管理
 */
@RestController
@RequestMapping ("/sys/dict")
@RequiredArgsConstructor
public class DictDataController
{
    private final DictDataService dictDataService;

    /**
     * 获取字典列表（根据字典类型）
     */
    @GetMapping ("list")
    public R getDictDataOfType(@Validated DictDataListQuery query)
    {
        return R.ok(dictDataService.listDictDataByDictKey(query.getDictKey()));
    }
}
