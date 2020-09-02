package com.cmc.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.cmc.common.valid.AddGroup;
import com.cmc.common.valid.UpdateGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌id
	 */
	@Null(groups = AddGroup.class,message = "新增不能添加id")
    @NotNull(groups = UpdateGroup.class,message = "修改品牌id不能为空")
	@TableId
    @ApiModelProperty(value = "品牌id")
	private Long brandId;
	/**
	 * 品牌名
	 */
    @ApiModelProperty(value = "品牌名",example = "测试")
    @NotBlank(message = "品牌名不能为空",groups = {AddGroup.class})
	private String name;
	/**
	 * 品牌logo地址
	 */
	@NotBlank(groups = AddGroup.class)
	@URL(message = "品牌logo必须是合法的url地址",groups = {AddGroup.class,UpdateGroup.class})
    @ApiModelProperty(value = "品牌logo地址")
	private String logo;
	/**
	 * 介绍
	 */
    @ApiModelProperty(value = "介绍")
	private String descript;
	/**
	 * 显示状态[0-不显示；1-显示]
	 */
    @ApiModelProperty(value = "显示状态[0-不显示，1显示]",example = "1")
	@NotNull(groups = AddGroup.class)
	private Integer showStatus;
	/**
	 * 检索首字母
	 */
    @ApiModelProperty(value = "检索首字母")
    @NotEmpty(groups = AddGroup.class)
    @Pattern(regexp = "^[A-Za-z]+$",message = "检索首字母必须是一个字母",groups = {AddGroup.class,UpdateGroup.class})
	private String firstLetter;
	/**
	 * 排序
	 */
    @ApiModelProperty(value = "排序",example = "0")
    @NotNull(groups = AddGroup.class)
    @Min(value = 0,message = "排序必须大于等于0",groups = {AddGroup.class,UpdateGroup.class})
	private Integer sort;

}
