package com.eodessa.common;

/**
 * Created by luda bruksha on 2/19/17.
 */
public interface EndPoint {

    String GET_PUSHCONFIGURATION = "/GetPushConfiguration/company/1/widget/1";
    String GET_PUSHCONFIGURATION_PATH_PARAM ="/GetPushConfiguration/company/{companyId}/widget/{widgetId}";
    String GET_EMPLOYEE_QUERY_PARAM = "/employee/getEmployeeQuery";
    String POST_EMPLOYEE_PARAM = "/employee/addEmployee";
}