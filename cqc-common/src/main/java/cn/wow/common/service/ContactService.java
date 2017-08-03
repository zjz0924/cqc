package cn.wow.common.service;

import cn.wow.common.domain.Contact;

public interface ContactService {
    public Contact selectOne(Long id);

    public int update(Contact contact);

}
