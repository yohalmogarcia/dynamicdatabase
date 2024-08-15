package sv.com.grupogd.clientsdte.config;

public class DatabaseContextHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static void setCurrentCompany(String companyId) {
        CONTEXT.set(companyId);
    }

    public static String getCurrentCompany() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }

}
