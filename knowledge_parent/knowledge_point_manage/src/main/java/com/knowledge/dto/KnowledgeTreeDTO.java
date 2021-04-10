package com.knowledge.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import java.util.Date;
import java.util.List;

/**
 * 查询知识点树接口响应参数
 */
@Data
@ApiModel("查询知识点树接口响应参数")
public class KnowledgeTreeDTO {

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
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 子菜单信息
    private List<KnowledgeTreeDTO> children;
}