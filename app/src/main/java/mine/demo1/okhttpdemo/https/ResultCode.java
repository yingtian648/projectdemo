package mine.demo1.okhttpdemo.https;

/**
 * Created by Ailn on 16/11/15.
 */
public class ResultCode {


    public static final int Success = 1;    //成功
    public static final int No_Permisson = 2;    //授权非法，禁止访问
    public static final int Unlawful = 3;    //请求非法，禁止访问
    public static final int No_params = 101;    //缺少参数	参数没传或值为空
    public static final int Params_Unlawful = 102;    //参数非法	如参数要求1或2，实际传3
    public static final int No_Login = 200;    //请先登录
    public static final int Login_TimeOut = 201;    //登录已超时，请重新登录
    public static final int Login_Out = 202;    //已在其它设备登录
    public static final int Account_Error = 203;    //用户或密码错误
    public static final int Code_Error = 300;    //业务异常	一般业务异常，如验证码错误
    public static final int Code_TimeOut = 301;    //短信验证码超时
    public static final int Sys_Error = 500;    //服务器内部错误	服务器内部出现错误，请稍后重试或者联系客服
    public static final int Sys_data_Error = 501;    //数据错误	数据库数据不一致，需要系统管理员检查


    /**
     * 是否需要重新登陆
     */
    public static boolean isLoginAgain(int code) {
        if (code == No_Login || code == Login_TimeOut || code == Login_Out||code==No_Permisson) {
            return true;
        }
        return false;
    }
}
