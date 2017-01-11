package mine.demo1.okhttp_qcloud;

import java.util.List;

/**
 * Created by jh on 2017/1/11.
 */

public class OkCityResult {

    /**
     * errNum : 0
     * errMsg : success
     * retData : [{"province_cn":"北京","district_cn":"北京","name_cn":"朝阳","name_en":"chaoyang","area_id":"101010300"}]
     */

    private int errNum;
    private String errMsg;
    /**
     * province_cn : 北京
     * district_cn : 北京
     * name_cn : 朝阳
     * name_en : chaoyang
     * area_id : 101010300
     */

    private List<RetDataBean> retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public List<RetDataBean> getRetData() {
        return retData;
    }

    public void setRetData(List<RetDataBean> retData) {
        this.retData = retData;
    }

    public static class RetDataBean {
        private String province_cn;
        private String district_cn;
        private String name_cn;
        private String name_en;
        private String area_id;

        public String getProvince_cn() {
            return province_cn;
        }

        public void setProvince_cn(String province_cn) {
            this.province_cn = province_cn;
        }

        public String getDistrict_cn() {
            return district_cn;
        }

        public void setDistrict_cn(String district_cn) {
            this.district_cn = district_cn;
        }

        public String getName_cn() {
            return name_cn;
        }

        public void setName_cn(String name_cn) {
            this.name_cn = name_cn;
        }

        public String getName_en() {
            return name_en;
        }

        public void setName_en(String name_en) {
            this.name_en = name_en;
        }

        public String getArea_id() {
            return area_id;
        }

        public void setArea_id(String area_id) {
            this.area_id = area_id;
        }
    }
}
