package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.ScrollPic;

public interface ScrollPicService {
    public ScrollPic selectOne(Long id);

    public int save(ScrollPic scrollPic);

    public int update(ScrollPic scrollPic);

    public int deleteByPrimaryKey(Long id);

    public List<ScrollPic> selectAllList(Map<String, Object> map);

}
