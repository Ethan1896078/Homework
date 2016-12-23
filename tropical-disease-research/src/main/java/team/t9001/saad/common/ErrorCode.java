package team.t9001.saad.common;

import java.util.HashMap;
import java.util.Map;

/**
 * desc:
 * Created by huangzhe on 2016/11/19.
 */
public interface ErrorCode {
    /** 错误代码 - 请求参数有误 */
    String INPUT_ERROR = "E001";
    /** 错误代码 - 访问数据库异常 */
    String DB_ACCESS_ERROR = "E002";
    /** 错误代码 - 数据库操作影响条数为0 */
    String NO_ROW_AFFECTED = "E003";
    /** 错误代码 - 未能找到该实体对象 */
    String ENTITY_NOT_FOUND = "E004";
    /** 错误代码 - 实体对象状态为不可用 */
    String ENTITY_STATUS_NOT_OK = "E005";
    /** 错误代码 - 没有权限 */
    String PERMISSION_DENIED = "E006";
    /** 错误代码 - 用户未登录 */
    String USER_NOT_LOGIN = "E007";
    /** 错误代码 - 账号或密码错误 */
    String ACCOUNT_PASSWORD_NOT_MATCH = "E008";

    Map<String, String> ERROR_MSG_MAP = new HashMap<String, String>(){{
        put(INPUT_ERROR, "请求参数有误");
        put(DB_ACCESS_ERROR, "访问数据库异常");
        put(NO_ROW_AFFECTED, "数据库操作影响条数为0");
        put(ENTITY_NOT_FOUND, "未能找到该实体对象");
        put(ENTITY_STATUS_NOT_OK, "实体对象状态为不可用");
        put(PERMISSION_DENIED, "没有权限");
        put(USER_NOT_LOGIN, "用户未登录");
        put(ACCOUNT_PASSWORD_NOT_MATCH, "账号或密码错误");
    }};

}
