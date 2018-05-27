package org.frameworkset.elasticsearch.bulk;

/**
 * Created by CYC on 18-05-26.
 */
public class OnlineGoodsInfoUpdateParams extends UpdateParams{

    private String goodsName;
    private int brandId;
    private String brandName;
    private int shopCustomCategoryId;
    private String goodsType;



    private Short quantityOfPacking;
    private Byte freePostage;
    private Double postage;
    private String goodsDescription;
    private String packingDescription;
    private String salesUnit;
    private String minimumUnit;

    private double minSalesPrice;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public int getShopCustomCategoryId() {
        return shopCustomCategoryId;
    }

    public void setShopCustomCategoryId(int shopCustomCategoryId) {
        this.shopCustomCategoryId = shopCustomCategoryId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }



    public Short getQuantityOfPacking() {
        return quantityOfPacking;
    }

    public void setQuantityOfPacking(Short quantityOfPacking) {
        this.quantityOfPacking = quantityOfPacking;
    }

    public Byte getFreePostage() {
        return freePostage;
    }

    public void setFreePostage(Byte freePostage) {
        this.freePostage = freePostage;
    }

    public Double getPostage() {
        return postage;
    }

    public void setPostage(Double postage) {
        this.postage = postage;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public String getPackingDescription() {
        return packingDescription;
    }

    public void setPackingDescription(String packingDescription) {
        this.packingDescription = packingDescription;
    }

    public String getSalesUnit() {
        return salesUnit;
    }

    public void setSalesUnit(String salesUnit) {
        this.salesUnit = salesUnit;
    }

    public String getMinimumUnit() {
        return minimumUnit;
    }

    public void setMinimumUnit(String minimumUnit) {
        this.minimumUnit = minimumUnit;
    }

    public double getMinSalesPrice() {
        return minSalesPrice;
    }

    public void setMinSalesPrice(double minSalesPrice) {
        this.minSalesPrice = minSalesPrice;
    }


}
