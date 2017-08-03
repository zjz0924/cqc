package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.NewsType;

public interface NewsTypeService {
    public NewsType selectOne(Long id);

    public int save(NewsType newsType);

    public int update(NewsType newsType);

    public int deleteByPrimaryKey(Long id);

    public List<NewsType> selectAllList(Map<String, Object> map);

}
