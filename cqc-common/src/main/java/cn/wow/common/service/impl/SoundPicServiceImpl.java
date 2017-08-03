package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.utils.pagination.PageHelperExt;
import cn.wow.common.dao.SoundPicDao;
import cn.wow.common.domain.SoundPic;
import cn.wow.common.service.SoundPicService;

@Service
@Transactional
public class SoundPicServiceImpl implements SoundPicService{

    private static Logger logger = LoggerFactory.getLogger(SoundPicServiceImpl.class);

    @Autowired
    private SoundPicDao soundPicDao;

    public SoundPic selectOne(Long id){
    	return soundPicDao.selectOne(id);
    }

    public int save(SoundPic soundPic){
    	return soundPicDao.insert(soundPic);
    }

    public int update(SoundPic soundPic){
    	return soundPicDao.update(soundPic);
    }

    public int deleteByPrimaryKey(Long id){
    	return soundPicDao.deleteByPrimaryKey(id);
    }

    public List<SoundPic> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return soundPicDao.selectAllList(map);
    }

}
