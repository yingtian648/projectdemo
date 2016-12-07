package mine.demo1.util;

import org.litepal.crud.DataSupport;

/**
 * Created by jh on 2016/11/29.
 */

public class ExceptionRequestBody extends DataSupport {
    public static final int success = 1;
    public static final int failed = 2;
    int id;
    private String telephone;
    private int uploadStatus;//0——未上传,1——已上传
    private String logMsg;

    private String mechinecode;

    /**
     * app版本
     */
    private String version;

    /**
     * 操作系统 1-android 2-ios 3-其它
     */
    private Integer os;

    private String osVersion;

    /**
     * 手机型号
     */
    private String phoneType;

    /**
     * 分辨率
     */
    private String resolution;

    /**
     * 品牌
     */
    private String brand;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUploadStatus() {
        return uploadStatus;
    }

    public void setUploadStatus(int uploadStatus) {
        this.uploadStatus = uploadStatus;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMechinecode() {
        return mechinecode;
    }

    public void setMechinecode(String mechinecode) {
        this.mechinecode = mechinecode;
    }

    public String getLogMsg() {
        return logMsg;
    }

    public void setLogMsg(String logMsg) {
        this.logMsg = logMsg;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public String getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(String phoneType) {
        this.phoneType = phoneType;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


}
