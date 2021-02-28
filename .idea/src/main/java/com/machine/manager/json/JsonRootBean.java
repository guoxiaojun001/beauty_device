/**
  * Copyright 2020 json.cn 
  */
package com.machine.manager.json;
import java.util.List;

/**
 * Auto-generated: 2020-12-17 14:55:55
 *
 * @author json.cn (i@json.cn)
 * @website http://www.json.cn/java2pojo/
 */
public class JsonRootBean {

    private List<ProviderInfo> providerInfo;
    private FxRateDetails fxRateDetails;
    private FxConvertedAmountDetails fxConvertedAmountDetails;
    private FxpDetails fxDetails;
    public void setProviderInfo(List<ProviderInfo> providerInfo) {
         this.providerInfo = providerInfo;
     }
     public List<ProviderInfo> getProviderInfo() {
         return providerInfo;
     }

    public void setFxRateDetails(FxRateDetails fxRateDetails) {
         this.fxRateDetails = fxRateDetails;
     }
     public FxRateDetails getFxRateDetails() {
         return fxRateDetails;
     }

    public void setFxConvertedAmountDetails(FxConvertedAmountDetails fxConvertedAmountDetails) {
         this.fxConvertedAmountDetails = fxConvertedAmountDetails;
     }
     public FxConvertedAmountDetails getFxConvertedAmountDetails() {
         return fxConvertedAmountDetails;
     }

    public void setFxDetails(FxpDetails fxDetails) {
         this.fxDetails = fxDetails;
     }
     public FxpDetails getFxDetails() {
         return fxDetails;
     }

}