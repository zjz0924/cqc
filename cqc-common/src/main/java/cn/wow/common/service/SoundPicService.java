package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.SoundPic;

public interface SoundPicService {
    public SoundPic selectOne(Long id);

    public int save(SoundPic soundPic);

    public int update(SoundPic soundPic);

    public int deleteByPrimaryKey(Long id);

    public List<SoundPic> selectAllList(Map<String, Object> map);

}
