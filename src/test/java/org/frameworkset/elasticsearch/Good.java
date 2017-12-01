package org.frameworkset.elasticsearch;/*
 *  Copyright 2008 biaoping.yin
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import org.frameworkset.elasticsearch.entity.ESBaseData;

public class Good extends ESBaseData {
	private String isNew;
	private Integer maxSalePrice;
	private Integer minMarketPrice;
	private Integer minSalePrice;
	private String secondCateCode  ;
	private String goodsShortDesc;
	private String firstCateCode;

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

	public Integer getMaxSalePrice() {
		return maxSalePrice;
	}

	public void setMaxSalePrice(Integer maxSalePrice) {
		this.maxSalePrice = maxSalePrice;
	}

	public Integer getMinMarketPrice() {
		return minMarketPrice;
	}

	public void setMinMarketPrice(Integer minMarketPrice) {
		this.minMarketPrice = minMarketPrice;
	}

	public Integer getMinSalePrice() {
		return minSalePrice;
	}

	public void setMinSalePrice(Integer minSalePrice) {
		this.minSalePrice = minSalePrice;
	}

	public String getSecondCateCode() {
		return secondCateCode;
	}

	public void setSecondCateCode(String secondCateCode) {
		this.secondCateCode = secondCateCode;
	}

	public String getGoodsShortDesc() {
		return goodsShortDesc;
	}

	public void setGoodsShortDesc(String goodsShortDesc) {
		this.goodsShortDesc = goodsShortDesc;
	}

	public String getFirstCateCode() {
		return firstCateCode;
	}

	public void setFirstCateCode(String firstCateCode) {
		this.firstCateCode = firstCateCode;
	}
}
