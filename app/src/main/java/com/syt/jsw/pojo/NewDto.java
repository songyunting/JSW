package com.syt.jsw.pojo;

import lombok.Data;

/**
 * 新闻 实体类
 *
 * @author syt
 * created in 2021/5/9 14:11
 */
@Data
public class NewDto {

    /**
     * uniquekey	新闻ID
     * title	    新闻标题
     * date		    新闻时间
     * category		新闻分类
     * author_name	新闻来源
     * url		    新闻访问链接
     * thumbnail_pic_s		新闻图片链接
     * is_content	是否有新闻内容,1表示有 可以通过查询新闻详细内容小接口获取新闻内容
     */

    private String uniquekey;
    private String title;
    private String date;
    private String category;
    private String author_name;
    private String url;
    private String thumbnail_pic_s;
    private String is_content;
}
