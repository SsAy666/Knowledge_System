package com.knowledge.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 *  知识点内容实体类
 */
@Data
@TableName("tb_knowledge_content")
public class KnowledgeContentEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    // 文字类型
    private Integer textType;

    // 文字
    private String text;

    // 文字链接
    private String textUrl;

    // 题目
    private String subject;

    // 图片
    private String picture;

    // 视频
    private String video;

    // 音频
    private String mp3;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
