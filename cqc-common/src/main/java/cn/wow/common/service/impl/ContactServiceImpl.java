package cn.wow.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.wow.common.dao.ContactDao;
import cn.wow.common.domain.Contact;
import cn.wow.common.service.ContactService;

@Service
@Transactional
public class ContactServiceImpl implements ContactService{

    private static Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    @Autowired
    private ContactDao contactDao;

    public Contact selectOne(Long id){
    	return contactDao.selectOne(id);
    }

    public int update(Contact contact){
    	return contactDao.update(contact);
    }
}
