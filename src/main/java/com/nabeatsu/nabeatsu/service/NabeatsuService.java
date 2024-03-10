package com.nabeatsu.nabeatsu.service;

import org.springframework.stereotype.Service;

import com.nabeatsu.nabeatsu.model.NabeatsuBean;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NabeatsuService {
    /**
     * アホになるか否かを判定するメソッド.
     * 
     * @param nabeatsuBean DTOオブジェクト
     * @return nabeatsuBean DTOオブジェクト
     */
    public NabeatsuBean judge(NabeatsuBean nabeatsuBean) {
        int num = nabeatsuBean.getNum();
        String numStr = Integer.toString(num);
        nabeatsuBean.setInitFlg(false);
        if (num != 0 && num % 3 == 0 || numStr.contains("3")) {
            nabeatsuBean.setAhoFlg(true);
        } else {
            nabeatsuBean.setAhoFlg(false);
        }
        return nabeatsuBean;
    }
}
