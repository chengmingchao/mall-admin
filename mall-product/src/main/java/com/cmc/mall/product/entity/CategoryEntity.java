package com.cmc.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.cmc.common.valid.AddGroup;
import com.cmc.common.valid.UpdateGroup;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apiguardian.api.API;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * 商品三级分类
 * 
 * @author chengmingchao
 * @email 502130705@qq.com
 * @date 2020-08-08 10:08:05
 */
@Data
@TableName("pms_category")
@ApiModel(value = "商品分类")
public class CategoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 分类id
	 */
	@Null(groups = AddGroup.class,message = "新增操作，分类id必须为空")
    @NotNull(groups = UpdateGroup.class,message = "修改操作，分类id不能为空")
	@TableId
    @ApiModelProperty(value = "分类id")
	private Long catId;
	/**
	 * 分类名称
	 */
	@NotBlank(groups = {AddGroup.class,UpdateGroup.class})
    @ApiModelProperty(value = "分类名称",example = "测试")
	private String name;
	/**
	 * 父分类id
	 */
	@NotNull(groups = AddGroup.class)
    @ApiModelProperty(value = "父分类id",example = "0")
	private Long parentCid;
	/**
	 * 层级
	 */
	@NotNull(groups = AddGroup.class)
    @ApiModelProperty(value = "层级",example = "0")
	private Integer catLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
    @NotNull(groups = AddGroup.class)
	@TableLogic(value = "1",delval = "0")
    @ApiModelProperty(value = "是否显示[0-不显示，1显示]",example = "1")
	private Integer showStatus;
	/**
	 * 排序
	 */
    @NotNull(groups = AddGroup.class)
    @Min(value = 0,message = "排序必须大于等于0",groups = {AddGroup.class,UpdateGroup.class})
    @ApiModelProperty(value = "排序",example = "0")
	private Integer sort;
	/**
	 * 图标地址
	 */
    @URL(message = "图标地址必须是合法的url地址",groups = {AddGroup.class,UpdateGroup.class})
    @ApiModelProperty(value = "图标地址")
	private String icon;
	/**
	 * 计量单位
	 */
    @ApiModelProperty(value = "计量单位",example = "个")
	private String productUnit;
	/**
	 * 商品数量
	 */
	private Integer productCount;

    /**
     * 子分类
     */
    @TableField(exist = false)
	private List<CategoryEntity> children;
}
