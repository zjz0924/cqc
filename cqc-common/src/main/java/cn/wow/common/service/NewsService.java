package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.News;

public interface NewsService {
    public News selectOne(Long id);

    public int save(News news);

    public int update(News news);

    public int deleteByPrimaryKey(Long id);

    public List<News> selectAllList(Map<String, Object> map);

}
