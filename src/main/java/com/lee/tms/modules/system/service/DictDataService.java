package com.lee.tms.modules.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lee.tms.modules.system.dto.vo.DictDataSimpleListVo;
import com.lee.tms.modules.system.entity.DictData;

import java.util.List;

public interface DictDataService extends IService<DictData>
{
    List<DictDataSimpleListVo> listDictDataByDictKey(String dictKey);

    String getDictLabel(String dictKey, String dictValue);

    String getDictValue(String dictKey, String dictLabel);

    boolean checkDictDataExistByKeyAndValue(String dictKey, String dictValue);

    boolean checkDictDataExistByKeyAndLabel(String dictKey, String dictLabel);
}
