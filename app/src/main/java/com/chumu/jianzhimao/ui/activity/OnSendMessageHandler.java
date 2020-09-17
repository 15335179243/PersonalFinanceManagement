package com.chumu.jianzhimao.ui.activity;

public interface OnSendMessageHandler {
    /** 
     * 此方法在发送验证短信前被调用，传入参数为接收者号码
     * 返回true表示此号码无须实际接收短信
     */

    public boolean onSendMessage(String country, String phone);

}