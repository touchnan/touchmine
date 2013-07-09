package cn.touchin.interceptor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.SerializationParams;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import cn.touchin.dto.ErrorCodeVo;
import cn.touchin.dto.ErrorVo;
import cn.touchin.dto.ResponseVo;
import cn.touchin.util.Constants;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ExceptInterceptor extends AbstractInterceptor {
    private static final long serialVersionUID = -6820660364564684944L;

    private boolean wrapWithComments = false;
    private boolean prefix;
    private boolean enableSMD = false;
    private boolean enableGZIP = true;
    private boolean noCache = false;
    private int statusCode;
    private int errorCode;
    private String callbackParameter;
    private String wrapPrefix;
    private String wrapSuffix;

    protected static final Log log = Logs.getLog(ExceptInterceptor.class);

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        try {
            return invocation.invoke();
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.error(e);
            }
            HttpServletRequest req = ServletActionContext.getRequest();
            HttpServletResponse resp = ServletActionContext.getResponse();
//            if (isAjax4Json(req, getContentType(req))) {
            if (isAjax(req)) {
                ResponseVo vo = new ResponseVo();
                vo.setErrorVo(new ErrorVo(e, ErrorCodeVo.EXCEPTION_UNCATCHED, log.isDebugEnabled()));
                writeJSONToResponse(req, resp, vo);
                return ActionSupport.NONE;
            }
            return ActionSupport.ERROR;
        }
    }

    protected boolean isAjax(HttpServletRequest req) {
        return Constants.X_Requested_With_AJAX.equals(req.getHeader(Constants.X_Requested_With));
    }

    protected boolean isAjax4Json(HttpServletRequest req, String contentType) {
        if (isAjax(req)) {
            if (Strings.isBlank(contentType) || Constants.CONTENTTYPE_DEFAULT.equals(contentType)) {
                return true;// 默认为JSON
            }
            return isJson(contentType) || isJsonRpc(contentType);
        }
        return false;
    }

    protected boolean isJson(String contentType) {
        return Constants.CONTENTTYPE_JSON.equals(contentType);
    }

    protected boolean isJsonRpc(String contentType) {
        return Constants.CONTENTTYPE_JSON_RPC.equals(contentType);
    }

    protected String getContentType(HttpServletRequest req) {
        String contentType = req.getHeader("content-type");
        if (contentType != null) {
            int iSemicolonIdx;
            if ((iSemicolonIdx = contentType.indexOf(";")) != -1)
                contentType = contentType.substring(0, iSemicolonIdx);
        }
        return contentType;
    }

    protected void setContentType(HttpServletResponse resp, String contentType) {
        resp.setContentType(contentType + ";charset=" + Constants.DEFAULT_ENCODING);
    }

    protected void writeJSONToResponse(HttpServletRequest req, HttpServletResponse resp, Object rootObject)
            throws IOException {
        try {
            writeJSONToResponse(req, resp, createJSONString(req, rootObject));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            throw new IOException(e);
        }
    }

    protected void writeJSONToResponse(HttpServletRequest req, HttpServletResponse resp, String serializedJSON)
            throws IOException {
        JSONUtil.writeJSONToResponse(new SerializationParams(resp, Constants.DEFAULT_ENCODING, wrapWithComments,
                serializedJSON, enableSMD, enableGzip(req), noCache, statusCode, errorCode, prefix,
                Constants.CONTENTTYPE_JSON, wrapPrefix, wrapSuffix));
    }

    private String createJSONString(HttpServletRequest request, Object rootObject) throws JSONException {
        String json = JSONUtil.serialize(rootObject);
        json = addCallbackIfApplicable(request, json);
        return json;
    }

    private boolean enableGzip(HttpServletRequest request) {
        return enableGZIP && JSONUtil.isGzipInRequest(request);
    }

    protected String addCallbackIfApplicable(HttpServletRequest request, String json) {
        if (!Strings.isBlank(callbackParameter)) {
            String callbackName = request.getParameter(callbackParameter);
            if ((callbackName != null) && (callbackName.length() > 0))
                json = callbackName + "(" + json + ")";
        }
        return json;
    }
}
