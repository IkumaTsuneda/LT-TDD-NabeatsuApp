package com.nabeatsu.nabeatsu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.nabeatsu.nabeatsu.model.NabeatsuBean;
import com.nabeatsu.nabeatsu.service.NabeatsuService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NabeatsuController {

    // サービスクラス.
    private final NabeatsuService service;

    /**
     * 初期表示処理.
     * 
     * @param nabeatsuBean DTO
     * @param result       バリデーションの検証結果
     * @param model        初期画面
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String init(
            @ModelAttribute @Validated NabeatsuBean nabeatsuBean,
            BindingResult result,
            Model model) {
        nabeatsuBean.setInitFlg(true);
        model.addAttribute("nabeatsuBean", nabeatsuBean);
        return "index";
    }

    /**
     * 再表示処理.
     * 
     * @param nabeatsuBean DTO
     * @param result       バリデーションの検証結果
     * @param model        再表示画面
     * @return
     */
    @RequestMapping(value = "/judge", method = RequestMethod.POST)
    public String judge(
            @ModelAttribute @Validated NabeatsuBean nabeatsuBean,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("errMsg", "半角数字を入力してください。");
        }
        model.addAttribute("nabeatsuBean", service.judge(nabeatsuBean));
        return "index";
    }
}
