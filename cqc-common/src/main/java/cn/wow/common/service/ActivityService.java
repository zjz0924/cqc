package cn.wow.common.service;

import java.util.List;
import java.util.Map;

import cn.wow.common.domain.Activity;
import cn.wow.common.domain.SoundPic;

public interface ActivityService {
    public Activity selectOne(Long id);

    public int save(Activity activity);

    public int update(Activity activity);

    public int deleteByPrimaryKey(Long id);

    public List<Activity> selectAllList(Map<String, Object> map);

}
