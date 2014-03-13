package https;

import org.eclipse.jetty.server.HttpConfiguration;
import org.eclipse.jetty.server.HttpConnectionFactory;
import org.eclipse.jetty.server.SecureRequestCustomizer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.SslConnectionFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.webapp.WebAppContext;
/**
第一步，命令行下使用jdk的keytool工具生成keystore
keytool -keystore keystore -alias jetty -genkey -keyalg RSA
第二步，生成证书
keytool -export -alias jetty -file jetty.crt -keystore keystore
第三步，生成OBA文件,这里的yoursslpassword是第一步填写的密码
java -cp jetty-all-9.0.5.v20130815.jar org.eclipse.jetty.util.security.Password yoursslpassword
第四步，通过浏览器访问 https://localhost:8443/myapp访问应用。浏览器会提示证书不可信任，是否继续访问，点是即可。
 */
public class JettyHttps {
    public static void main(String[] args) {
    	Server server = new Server();

    	HttpConfiguration https_config = new HttpConfiguration();
    	https_config.setSecureScheme("https");
    	https_config.setSecurePort(8443);
    	https_config.setOutputBufferSize(32768);
    	https_config.addCustomizer(new SecureRequestCustomizer());

    	SslContextFactory sslContextFactory = new SslContextFactory();
    	sslContextFactory.setKeyStorePath("keystore");
    	sslContextFactory.setKeyStorePassword("OBF:1xtb1uo71wg41y0q1y7z1y101wfu1unr1xu7");
    	sslContextFactory.setKeyManagerPassword("OBF:1xtb1uo71wg41y0q1y7z1y101wfu1unr1xu7");

    	ServerConnector httpsConnector = new ServerConnector(server,
    	        new SslConnectionFactory(sslContextFactory,"http/1.1"),
    	        new HttpConnectionFactory(https_config));
    	httpsConnector.setPort(8443);
    	httpsConnector.setIdleTimeout(500000);
    	server.addConnector(httpsConnector);

    	WebAppContext webApp = new WebAppContext();
    	webApp = new WebAppContext();
    	webApp.setContextPath("/myapp");
    	webApp.setResourceBase("WebRoot");
    	webApp.setInitParameter("org.eclipse.jetty.servlet.Default.dirAllowed", "false");
    	webApp.setInitParameter("org.eclipse.jetty.servlet.Default.useFileMappedBuffer", "false");
    	server.setHandler(webApp);
    	try {
    		server.start();
    		server.join();
    	} catch (Exception e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
} 