package com.el.qr.login.dto;


/**
 * <>
 * @author 许恕
 * @version 1.0
 * @Time 2018-06-04 09:13:23
 */

public class QRLoginDto implements java.io.Serializable{
	private static final long serialVersionUID = 1L;


	/**
     * 二维码唯一标识
     */
	private String qrCode;

	/**
	 * 二维码绑定用户
	 */
	private String qrUser;

	public String getQrCode() {
		return qrCode;
	}

	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}

	public String getQrUser() {
		return qrUser;
	}

	public void setQrUser(String qrUser) {
		this.qrUser = qrUser;
	}
}
