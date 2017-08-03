package cn.wow.common.service;

import java.util.List;
import java.util.Map;
import cn.wow.common.domain.Train;

public interface TrainService {
    public Train selectOne(Long id);

    public int save(Train train);

    public int update(Train train);

    public int deleteByPrimaryKey(Long id);

    public List<Train> selectAllList(Map<String, Object> map);

}
