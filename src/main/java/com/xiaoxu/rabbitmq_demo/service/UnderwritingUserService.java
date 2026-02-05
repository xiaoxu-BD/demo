package com.xiaoxu.rabbitmq_demo.service;

import com.xiaoxu.rabbitmq_demo.mapper.UserInfoMapper;
import com.xiaoxu.rabbitmq_demo.masking.MaskingUtils;
import com.xiaoxu.rabbitmq_demo.model.UserInfo;
import com.xiaoxu.rabbitmq_demo.model.UserInfoView;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UnderwritingUserService {
    private final UserInfoMapper userInfoMapper;

    public UnderwritingUserService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    @Transactional
    public Long saveUser(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
        return userInfo.getId();
    }

    @Transactional(readOnly = true)
    public UserInfoView queryMaskedUser(Long id) {
        UserInfo userInfo = userInfoMapper.findById(id);
        if (userInfo == null) {
            return null;
        }
        UserInfoView view = new UserInfoView();
        view.setId(userInfo.getId());
        view.setName(MaskingUtils.maskName(userInfo.getName()));
        view.setIdNumber(MaskingUtils.maskIdNumber(userInfo.getIdNumber()));
        view.setPhone(MaskingUtils.maskPhone(userInfo.getPhone()));
        view.setAddress(MaskingUtils.maskAddress(userInfo.getAddress()));
        return view;
    }
}
