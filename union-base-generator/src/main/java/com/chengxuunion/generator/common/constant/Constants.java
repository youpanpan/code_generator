package com.chengxuunion.generator.common.constant;

/**
 * @Author youpanpan
 * @Description:
 * @Date:创建时间: 2019-01-08 16:25
 * @Modified By:
 */
public class Constants {
    private Constants() {

    }

    /**
     * 启用
     */
    public static final String ENABLE = "1";
    /**
     * 禁用
     */
    public static final String DISABLE = "0";

    /**
     * 单模版
     */
    public static final String TEMPLATE_TYPE_SINGLE = "1";
    /**
     * 组合模版
     */
    public static final String TEMPLATE_TYPE_GROUP = "2";

    /**
     * 请求方法数组
     */
    public static final String[] REQUEST_METHODS = new String[]{"GET", "POST", "PUT", "DELETE"};

    /**
     * 分隔符
     */
    public static final String SPLIT_STR = ",";

    /**
     * 开发环境
     */
    public static final String DEV = "dev";

    /**
     * 管理员角色标识code
     */
    public static final String ROLE_ADMIN = "admin";
    /**
     * 开发者角色标识code
     */
    public static final String ROLE_DEVELOPER =  "developer";
    /**
     * 项目管理员角色标识code
     */
    public static final String ROLE_PROJECT = "project";

    /**
     * 用户对象Session属性键名
     */
    public static final String USER = "user";

    /**
     * 语言Sesssion属性键名
     */
    public static final String LANGUAGE = "language";

    /**
     * URL列表属性键名
     */
    public static final String URL_LIST = "url_list";

    /**
     * 用户能访问的菜单属性键名
     */
    public static final String MENU_LIST = "menu_list";

}
