package dcgpt.test;


import java.io.Serializable;

/**
 * @Author: LiuXingKun
 * @Date: 2023/7/12 15:38
 */

public class DeviceComposeVo implements Serializable  {

    private static final long serialVersionUID = 8335389878898884894L;


    private Long id;

    private String deviceTypeNameFirst;

    private String deviceTypeNameSecond;

    private String deviceTypeNameThird;

    private String supplierName;

    private Integer shelfLife;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceTypeNameFirst() {
        return deviceTypeNameFirst;
    }

    public void setDeviceTypeNameFirst(String deviceTypeNameFirst) {
        this.deviceTypeNameFirst = deviceTypeNameFirst;
    }

    public String getDeviceTypeNameSecond() {
        return deviceTypeNameSecond;
    }

    public void setDeviceTypeNameSecond(String deviceTypeNameSecond) {
        this.deviceTypeNameSecond = deviceTypeNameSecond;
    }

    public String getDeviceTypeNameThird() {
        return deviceTypeNameThird;
    }

    public void setDeviceTypeNameThird(String deviceTypeNameThird) {
        this.deviceTypeNameThird = deviceTypeNameThird;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }
}
