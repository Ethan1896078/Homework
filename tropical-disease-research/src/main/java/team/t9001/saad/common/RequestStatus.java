package team.t9001.saad.common;

/**
 * desc:
 * Created by huangzhe on 2016/11/19.
 */
public class RequestStatus {
    /** 状态 */
    private int status = Constants.STATUS_OK;
    /** 错误码 */
    private String errorCode = "";
    /** 错误信息 */
    private String errorMsg = "";
    /** 数据 */
    private String data = "";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
