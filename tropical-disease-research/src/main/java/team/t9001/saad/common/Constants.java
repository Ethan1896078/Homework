package team.t9001.saad.common;

/**
 * desc:
 * Created by huangzhe on 2016/11/19.
 */
public interface Constants {
    /** 返回状态 - 正常 */
    int STATUS_OK = 1;
    /** 返回状态 - 异常 */
    int STATUS_NOT_OK = 0;

    /** 用户类型 - 超级管理员 */
    int USER_TYPE_SUPER_ADMIN = 1;
    /** 用户类型 - 管理员 */
    int USER_TYPE_ADMIN = 2;
    /** 用户类型 - 实验室用户 */
    int USER_TYPE_LAB_WORKER = 3;
}
