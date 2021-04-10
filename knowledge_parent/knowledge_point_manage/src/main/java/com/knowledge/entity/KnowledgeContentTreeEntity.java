package com.knowledge.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 知识点内容-知识点树实体类
 */
@Data
@TableName("tb_knowledge_content_tree")
public class KnowledgeContentTreeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    // 知识点内容ID
    private Integer knowledgeContentId;

    // 知识点树ID
    private Integer knowledgeTreeId;
}
