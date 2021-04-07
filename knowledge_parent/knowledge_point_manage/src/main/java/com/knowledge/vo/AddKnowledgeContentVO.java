package com.knowledge.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 添加知识点内容请求参数
 */
@Data
@ApiModel("添加知识点内容请求参数")
public class AddKnowledgeContentVO {

    @ApiModelProperty(value = "文字类型")
    private Integer textType;

    @ApiModelProperty(value = "文字")
    private String text;

    @ApiModelProperty(value = "文字链接")
    private String textUrl;

    @ApiModelProperty(value = "题目", required = true)
    @NotNull(message = "题目不能为空")
    private String subject;

    @ApiModelProperty(value = "图片")
    private String picture;

    @ApiModelProperty(value = "视频")
    private String video;

    @ApiModelProperty(value = "音频")
    private String mp3;
}
