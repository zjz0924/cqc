package cn.wow.common.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wow.common.dao.TrainDao;
import cn.wow.common.domain.Train;
import cn.wow.common.service.LawService;
import cn.wow.common.service.TrainService;
import cn.wow.common.utils.pagination.PageHelperExt;

@Service
@Transactional
public class TrainServiceImpl implements TrainService{

    private static Logger logger = LoggerFactory.getLogger(TrainServiceImpl.class);

    @Autowired
    private TrainDao trainDao;

    public Train selectOne(Long id){
    	return trainDao.selectOne(id);
    }

    public int save(Train train){
    	return trainDao.insert(train);
    }

    public int update(Train train){
    	return trainDao.update(train);
    }

    public int deleteByPrimaryKey(Long id){
    	return trainDao.deleteByPrimaryKey(id);
    }

    public List<Train> selectAllList(Map<String, Object> map){
    	PageHelperExt.startPage(map);
    	return trainDao.selectAllList(map);
    }

}
