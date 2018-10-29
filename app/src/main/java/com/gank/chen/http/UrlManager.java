package com.gank.chen.http;

/**
 * Creat by chen on 2018/10/17
 * Describe:
 *
 * @author chenbo
 */
public class UrlManager {
    /**
     * 注册
     */
    public static final String REGISTER = "user/register";
    /**
     * 登录
     */
    public static final String LOGIN = "user/login";
    /**
     * 退出
     */
    public static final String LOGOUT = "user/logout/json";
    /**
     * 获取banner
     */
    public static final String BANNER = "banner/json";
    /**
     * 首页文章列表
     */
    public static final String ARTICLE_LIST = "article/list/{page}/json";
    /**
     * 收藏文章的列表
     */
    public static final String COLLECT_LIST = "lg/collect/list/{page}/json";
    /**
     * 收藏站内文章
     */
    public static final String COLLECT = "lg/collect/{id}/json";
    /**
     * 取消收藏站内文章(文章列表)
     */
    public static final String UN_COLLECT = "lg/uncollect_originId/{id}/json";
    /**
     * 获取公众号列表
     */
    public static final String CHAPTERS = "wxarticle/chapters/json";
    /**
     * 查看某个公众号历史数据
     */
    public static final String CHAPTERS_LIST = "wxarticle/list/{id}/{page}/json";
}
