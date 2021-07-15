package pl.com.pt4q.product_manager.view_utils;

public abstract class UrlLinkWithParamCreator {

    public static String createLinkWithParam(String url, String paramName, Long id) {
        return url + "?" + paramName + "=" + id.toString();
    }
}
