package com.qilinxx.rms.service.impl;

import com.qilinxx.rms.domain.mapper.MajorMapper;
import com.qilinxx.rms.domain.mapper.UserInfoMapper;
import com.qilinxx.rms.domain.model.UserInfo;
import com.qilinxx.rms.domain.model.eo.UserInfoEo;
import com.qilinxx.rms.service.ExportExcelService;
import com.qilinxx.rms.util.ExportExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


/**
 * @auther: dqsdc
 * @Date: 2019-03-25 16:17
 * @Description:
 */
@Service
public class ExportExcelServiceImpl implements ExportExcelService {

    @Autowired
    UserInfoMapper userInfoMapper;

    @Autowired
    MajorMapper majorMapper;

    @Override
    public void exportUserInfo(int[] ids,HttpServletResponse response) {
        ArrayList<UserInfoEo> eos = new ArrayList<>();
        for (int id : ids) {
            UserInfo eo = userInfoMapper.selectByPrimaryKey(id);
            eos.add(userInfo2Eo(eo));
        }
        ExportExcelUtil<UserInfoEo> util = new ExportExcelUtil<>();
        String[] columnNames = {"工号", "姓名", "密码","性别","专业","院系"};
        try {
            util.exportExcel("用户导出", columnNames, eos,
                    response.getOutputStream(), ExportExcelUtil.EXCEl_FILE_2007);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private UserInfoEo userInfo2Eo(UserInfo u){
        UserInfoEo eo=new UserInfoEo();
        eo.setId(u.getUid());
        eo.setName(u.getName());
        eo.setPassword(u.getPassword());
        eo.setSex(u.getSex());
        eo.setBelong(u.getBelong());
        eo.setMajor(majorMapper.selectByPrimaryKey(u.getMid()).getName());
        return eo;
    }
}
