package com.zcx.exam.common;

public interface ApiConst {
    static String STATIC_JS = "/**/*.js";
    static String STATIC_CSS = "/**/*.css";

    static String ALL_1 = "/*";
    static String ALL_2 = "/**";
    static String ALL_3 = "/*.*";

    static String API_LOGIN = "/login";
    static String API_LOGOUT = "/logout";
    static String API_HOME_CONSOLE = "/home";

    /*用户相关*/
    static String API_USER = "/user";
    static String API_USER_LIST = "/userlist";
    static String API_USER_CREATE = "/create";
    static String API_USER_UPDATE = "/{id}/update";
    static String API_USER_DELETE = "/{id}/delete";
    static String API_USER_PWD_CHANGE = "/{id}/changePassword";
    /*老人相关*/
    static String API_OLDMAN = "/oldMan";
    static String API_OLDMAN_LIST = "/oldManList";
    static String API_OLDMAN_CREATE = "/create";
    static String API_OLDMAN_UPDATE = "/{id}/update";
    static String API_OLDMAN_DELETE = "/{id}/delete";
    static String API_OLDMAN_DETAIL = "/{id}/detail";
    /*问题反馈相关*/
    static String API_QUESTION = "/question";
    static String API_QUESTION_LIST = "/questionList";
    static String API_QUESTION_CREATE = "/create";
    static String API_QUESTION_UPDATE = "/{id}/update";
    static String API_QUESTION_DETAIL = "/{id}/detail";
    /*外出相关*/
    static String API_LEAVE = "/leave";
    static String API_LEAVE_LIST = "/leaveList";
    static String API_LEAVE_CREATE = "/create";
    static String API_LEAVE_UPDATE = "/{id}/update";
    static String API_LEAVE_DELETE = "/{id}/delete";
    static String API_LEAVE_DETAIL = "/{id}/detail";
    /*房间相关*/
    static String API_ROOM = "/room";
    static String API_ROOM_LIST = "/roomList";
    static String API_ROOM_CREATE = "/create";
    static String API_ROOM_UPDATE = "/{id}/update";
    static String API_ROOM_DELETE = "/{id}/delete";
    static String API_ROOM_DETAIL = "/{id}/detail";
    static String API_ROOM_ONE_LIVE = "/{id}/live";
    static String API_ROOM_TWO_LIVE = "/{id}/live2";
    /*护工相关*/
    static String API_WORKER = "/worker";
    static String API_WORKER_LIST = "/workerList";
    static String API_WORKER_CREATE = "/create";
    static String API_WORKER_UPDATE = "/{id}/update";
    static String API_WORKER_DELETE = "/{id}/delete";
    static String API_WORKER_WORK = "/{id}/work";
    /*贴吧相关*/
    static String API_TB = "/TB";

    /*考卷相关*/
    static String API_PAPER = "";
    /**
     * 生成文件存放路径
     */
    public static final String FILE_PATH = "C:\\Users\\Administrator\\Desktop\\";

    /**
     * 表格默认名称
     */
    public static final String FILE_NAME = "TEST.xls";

}
