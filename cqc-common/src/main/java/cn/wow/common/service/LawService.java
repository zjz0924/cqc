package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.Law;

public interface LawService {
    public Law selectOne(Long id);

    public int save(Law law);

    public int update(Law law);

    public int deleteByPrimaryKey(Long id);

    public List<Law> selectAllList(Map<String, Object> map);

}
