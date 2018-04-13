package com.system.service.impl;

import com.system.mapper.UserloginMapper;
import com.system.po.Userlogin;
import com.system.po.UserloginExample;
import com.system.service.UserloginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Jacey on 2017/6/29.
 */
@Service
public class UserloginServiceImpl implements UserloginService {

    @Autowired
    private UserloginMapper userloginMapper;


    @Override
    public Userlogin findByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        List<Userlogin> list = userloginMapper.selectByExample(userloginExample);

        return list.size()>0 ? list.get(0):null;
    }

    public void save(Userlogin userlogin) throws Exception {
        userloginMapper.insert(userlogin);
    }

    public void removeByName(String name) throws Exception {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginMapper.deleteByExample(userloginExample);
    }

    @Override
    public void updateByName(String name, Userlogin userlogin) {
        UserloginExample userloginExample = new UserloginExample();

        UserloginExample.Criteria criteria = userloginExample.createCriteria();
        criteria.andUsernameEqualTo(name);

        userloginMapper.updateByExample(userlogin, userloginExample);
    }

}
