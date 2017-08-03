package cn.wow.common.service;

import cn.wow.common.domain.Introduction;

public interface IntroductionService {
    public Introduction selectOne(Long id);

    public int update(Introduction introduction);

}
