package com.nabeatsu.nabeatsu;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import com.nabeatsu.nabeatsu.model.NabeatsuBean;
import com.nabeatsu.nabeatsu.service.NabeatsuService;

@SpringBootTest
class NabeatsuUnitTest {

	NabeatsuService service = new NabeatsuService();
	NabeatsuBean dto = new NabeatsuBean();

	/**
	 * 3の倍数または、3の付く数値が入力された場合
	 * (アホになる場合)
	 * 
	 * @param num 入力値
	 */
	@ParameterizedTest
	@ValueSource(ints = { 3, 6, 9, 12, 13, 23, 24 })
	void judgeTrueTest(int num) {
		// DTOに数字をセット
		dto.setNum(num);
		// メソッドを実行
		dto = service.judge(dto);
		// 実行結果を判定
		assertAll(
				() -> {
					assertTrue(dto.isAhoFlg());
				},
				() -> {
					assertFalse(dto.isInitFlg());
				});
	}

	/**
	 * 3の倍数または、3の付く数値以外が入力された場合
	 * (アホにならない場合)
	 * 
	 * @param num 入力値
	 */
	@ParameterizedTest
	@ValueSource(ints = { 0, 2, 4 })
	void judgeFlaseTest(int num) {
		// DTOに数字をセット
		dto.setNum(num);
		// メソッドを実行
		dto = service.judge(dto);
		// 実行結果を判定
		assertAll(
				() -> {
					assertFalse(dto.isAhoFlg());
				},
				() -> {
					assertFalse(dto.isInitFlg());
				});
	}
}
