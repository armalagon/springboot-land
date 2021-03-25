package ni.jug.greeting.boundary;

import javax.servlet.http.HttpServletRequest;

public final class ServletRequestSupport {

    private ServletRequestSupport() {
    }

    public static String lang(HttpServletRequest request) {
        String lang = request.getHeader("accept-language");
        return lang == null ? "*" : request.getLocale().getLanguage();
    }

    public static String methodName(HttpServletRequest request) {
        String uri = request.getRequestURI();
        int pos = uri.lastIndexOf('/');
        if (pos == -1 || pos == uri.length() - 1) {
            return "root";
        } else {
            return uri.substring(pos + 1);
        }
    }

    public static String customHeader(HttpServletRequest request, String suffix) {
        return "x-" + methodName(request) + '-' + suffix;
    }
}
