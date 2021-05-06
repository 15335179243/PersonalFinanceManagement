package com.example.common_base;

public class NetConfig {
    public static String BaseUrl;
    public static int API_TYPE = 1;//1:正式服务器 2：外测服务器 3：内测服务器
    public static String BASE_URL2 = "http://rdks.iioog.com:9091";
    public static String BASE_URL3 = "http://192.168.40.21:9090/";

    static {
        if (API_TYPE == 1) BaseUrl = "http://1.117.59.7:6999/";
        else if (API_TYPE == 2) BaseUrl = BASE_URL2;
        else BaseUrl = BASE_URL3;
    }
}
