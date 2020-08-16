package com.cmc.mall.product.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apiguardian.api.API;

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
	@TableId
    @ApiModelProperty(value = "分类id")
	private Long catId;
	/**
	 * 分类名称
	 */
    @ApiModelProperty(value = "分类名称",example = "测试")
	private String name;
	/**
	 * 父分类id
	 */
    @ApiModelProperty(value = "父分类id",example = "0")
	private Long parentCid;
	/**
	 * 层级
	 */
    @ApiModelProperty(value = "层级",example = "0")
	private Integer catLevel;
	/**
	 * 是否显示[0-不显示，1显示]
	 */
	@TableLogic(value = "1",delval = "0")
    @ApiModelProperty(value = "是否显示[0-不显示，1显示]",example = "1")
	private Integer showStatus;
	/**
	 * 排序
	 */
    @ApiModelProperty(value = "排序",example = "0")
	private Integer sort;
	/**
	 * 图标地址
	 */
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
	@JsonIgnore
	private Integer productCount;

    /**
     * 子分类
     */
    @JsonIgnore
    @TableField(exist = false)
	private List<CategoryEntity> children;
}
