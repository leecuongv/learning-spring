package com.cuonglv.learning_spring.utility.helper;

import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
// import com.cuonglv.learning_spring.utility.consts.Result;
// import com.cuonglv.learning_spring.utility.consts.Error;

public class LogHelper {

    // public static JsonObject writeLogToDB(String module, String processInfo,
    // String input, JsonObject result,
    // String domain, String logDataSource) throws Exception {
    //
    // // log-info from [result]
    // String dataOutput = null;
    // JsonElement dataJE = result.get(Result.DATA);
    // if (dataJE.isJsonNull()) {
    // dataOutput = null;
    // } else if (dataJE.isJsonPrimitive()) {
    // dataOutput = dataJE.getAsString();
    // } else {
    // dataOutput = dataJE.toString();
    // }
    //
    // JsonObject errorInfo = result.get(Error.ERROR_INFO).getAsJsonObject();
    // String errorCode = errorInfo.get(Error.ERROR_CODE).getAsString();
    // String errorMessage = errorInfo.get(Error.ERROR_MESSAGE).getAsString();
    // String stacktrace = errorInfo.get(Error.STACK_TRACE).toString();
    //
    // UUID logCode = UUID.randomUUID();
    //
    // // insert log to DB
    // String sql = "INSERT INTO SI_LOG (si_module, process_info, case_id,
    // case_id_ref, bpm_input, bpm_output, si_input, si_output, error_code,
    // error_message, stacktrace, si_domain) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
    // String params = String.format("%s||%s||%s||%s||%s||%s||%s||%s||%s||%s",
    // logCode, module, processInfo, input,
    // dataOutput, null, null, errorCode, errorMessage, stacktrace, domain);
    //
    // PostgreJDBC.updateDataMultiParams(sql, params, logDataSource);
    //
    // // get [log-ID] from DB
    // JsonArray logDataQuery = PostgreJDBC.queryData("select id from si_log where
    // log_code=?", logCode.toString(),
    // logDataSource);
    // String logId = logDataQuery.get(0).getAsJsonObject().get("ID").getAsString();
    //
    // // adding [log-ID] to [message]
    // errorInfo.addProperty(Error.ERROR_MESSAGE, String.format("Log-ID=[%s]. %s",
    // logId, errorMessage));
    //
    // return result;
    // }
}
