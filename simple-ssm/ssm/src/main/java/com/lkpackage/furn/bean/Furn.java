package com.lkpackage.furn.bean;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class Furn {
    private Integer id;

    @NotEmpty(message = "请输入家居名")
    private String furnName;
    @NotEmpty(message = "请输入制造商")
    private String maker;
    @NotNull(message = "请输入价格")
    @Min(value = 0L,message = "价格不能小于0")
    private BigDecimal price;
    @NotNull(message = "请输入销量")
    @Range(min = 0L,message = "销量不能小于0")
    private Integer sales;
    @NotNull(message = "请输入库存")
    @Range(min = 0L,message = "库存不能小于0")
    private Integer stock;

    private Byte isShow = 0;

    public Furn() {
    }

    public Furn(Integer id, String furnName, String maker, BigDecimal price, Integer sales, Integer stock, Byte isShow) {
        this.id = id;
        this.furnName = furnName;
        this.maker = maker;
        this.price = price;
        this.sales = sales;
        this.stock = stock;
        if (isShow != null) {
            this.isShow = isShow;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFurnName() {
        return furnName;
    }

    public void setFurnName(String furnName) {
        this.furnName = furnName == null ? null : furnName.trim();
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker == null ? null : maker.trim();
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Byte getIsShow() {
        return isShow;
    }

    public void setIsShow(Byte isShow) {
        if (isShow != null) {
            this.isShow = isShow;
        }
    }
}