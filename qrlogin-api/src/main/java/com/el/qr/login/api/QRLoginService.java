package com.el.qr.login.api;

import com.el.qr.login.dto.QRLoginDto;
import com.eloancn.framework.sevice.api.ResultDTO;

/**
 * <>
 * @author 许恕
 * @version 1.0
 * @Time 2018-06-04 09:13:23
 */
public interface QRLoginService {

	/**
	 * 初始化二维码
	 * @param qrLoginDto 参数
	 * @return
	 */
	ResultDTO<Boolean> initQRCode(QRLoginDto qrLoginDto);

	/**
	 * 检查二维码是否与用户有绑定
	 * @param qrLoginDto
	 * @return
	 */
	ResultDTO<String> checkQRCodeBindUser(QRLoginDto qrLoginDto);

	/**
	 * 绑定二维码与用户的关系：APP调用
	 * @param qrLoginDto
	 * @return
	 */
	ResultDTO<Boolean> bindQRCodeUser(QRLoginDto qrLoginDto);

}