package https;
import javax.naming.ConfigurationException;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ssl.SslSocketConnector;
import org.eclipse.jetty.util.log.StdErrLog;
import org.eclipse.jetty.http.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyHttps {
    /** port */
    private int port = 9000;
    protected String charset = "UTF-8";
    public JettyHttps() {
    }
    public JettyHttps(int port) {
        this.port = port;
    }
    public JettyHttps(int port, String charset) {
        super();
        this.port = port;
        this.charset = charset;
    }
    /**
     *  服务器启动。
     *  @throws ConfigurationException
     */
    public void start() {
        // 设置Jetty日志
        System.setProperty("org.eclipse.jetty.util.log.class", StdErrLog.class.getName());
        Server server = new Server();
        // 设置ssl连接器
        SslSocketConnector ssl_connector = new SslSocketConnector();
        ssl_connector.setPort(this.port);
        SslContextFactory cf = ssl_connector.getSslContextFactory();
        cf.setKeyStorePath("src/test/java/jetty/epayService.keystore");
        cf.setKeyStorePassword("123456");
        cf.setKeyManagerPassword("123456");
        server.addConnector(ssl_connector);
        // 设置context
        WebAppContext context = new WebAppContext();
        context.setResourceBase("./src/main/webapp");
        context.setContextPath("/");
        context.setDefaultsDescriptor("src/test/java/jetty/webdefault.xml");
        // PS:嵌入式的Jetty，应用当前工程的ClassPath，如果不设置将使用WebAppClassLoder，WEB-INF/lib目录加载jar。
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.setParentLoaderPriority(true);
        server.setHandler(context);
        // 启动Server
        try {
            server.start();
            server.join();
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        JettyHttps server = new JettyHttps();
        server.start();
    }
} 