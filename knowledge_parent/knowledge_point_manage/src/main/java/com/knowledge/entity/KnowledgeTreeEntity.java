package com.knowledge.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 知识点树实体类
 */
@Data
@TableName("tb_knowledge_tree")
public class KnowledgeTreeEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    // 分支类型
    private Integer branchType;

    // 分支名称
    private String branchName;

    // 父节点ID
    private Integer parentId;

    // 相同父节点的顺序号
    private Integer orderNum;

    // 创建时间
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    // 更新时间
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
}
