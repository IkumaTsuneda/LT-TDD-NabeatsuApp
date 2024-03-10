package com.nabeatsu.nabeatsu.model;

import org.springframework.format.annotation.NumberFormat;

import lombok.Data;

@Data
public class NabeatsuBean {
    /*
     * 初期表示フラグ
     * true: 初期表示
     * false: 再表示
     */
    private boolean initFlg;
    /*
     * アホフラグ
     * true: アホになる
     * false: アホにならない
     */
    private boolean ahoFlg;
    // 入力値
    // @Pattern(regexp = "^[0-9]*$")
    @NumberFormat(pattern = "^[0-9]*$")
    private int num;
}
