package com.nabeatsu.nabeatsu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.WebApplicationContext;

import com.nabeatsu.nabeatsu.model.NabeatsuBean;
import com.nabeatsu.nabeatsu.service.NabeatsuService;

import lombok.var;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class NabeatsuControllerTest {

    private MockMvc mockMvc;
    NabeatsuService service = new NabeatsuService();
    NabeatsuBean dto = new NabeatsuBean();

    @Autowired
    WebApplicationContext webApplicationContext;

    @BeforeEach
    void setup() {
        // @AutoConfigureMockMvcというアノテーションを使うとこの初期化は不要だが、
        // 問題が起きることもあるので手動で初期化している。
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    /**
     * 初期表示
     * 
     * @throws Exception
     */
    @Test
    @DisplayName("/を表示した場合")
    void initSuccess() throws Exception {
        // / にアクセスした場合のテストを行う
        this.mockMvc
                // リクエストを送信
                .perform(get("/"))
                // レスポンスが期待通りか確認
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attributeExists("nabeatsuBean"));
    }

    /**
     * submit時
     * 
     * @param num 入力値
     * @throws Exception
     */
    @ParameterizedTest
    @DisplayName("/judgeを表示した場合")
    @ValueSource(ints = { 0, 2, 3 })
    void judgeSuccess(int num) throws Exception {
        // DTOに数字をセット
        dto.setNum(num);
        // メソッドを実行
        dto = service.judge(dto);
        // /judgeにアクセスした場合のテストを行う
        this.mockMvc
                // リクエストを送信
                .perform(post("/judge").flashAttr("nabeatsuBean", dto))
                // レスポンスが期待通りか確認
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("nabeatsuBean", dto));
    }

    /**
     * バリデーションエラーがある場合
     * 
     * @param input 入力値
     * @throws Exception
     */
    @ParameterizedTest
    @DisplayName("/judgeを表示時、バリデーションエラーがある場合")
    @ValueSource(strings = { "aaa", "あああ", "ｑｑｑ" })
    void judgeError(String input) throws Exception {
        // /judgeにアクセスした場合のテストを行う
        this.mockMvc
                // リクエストを送信
                .perform(post("/judge").param("num", input))
                // レスポンスが期待通りか確認input
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(model().attribute("nabeatsuBean", dto))
                .andExpect(model().attribute("errMsg", "半角数字を入力してください。"));
    }
}
