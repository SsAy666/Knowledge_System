package com.knowledge.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 知识点历史记录实体类
 */
@Data
@TableName("tb_knowledge_history_record")
public class KnowledgeHistoryRecordEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type= IdType.AUTO)
    private Integer id;

    // 操作人
    private String operator;

    // 操作方式(1.新增 2.编辑 3.删除)
    private Integer operateStyle;

    // 操作前内容
    private String operateBeforeContent;

    // 操作后内容
    private String operateAfterContent;

    // 操作时间
    @TableField(fill = FieldFill.INSERT)
    private Date operateTime;
}
