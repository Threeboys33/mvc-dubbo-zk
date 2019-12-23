package dtstack.san;

/**
 * <p>
 *
 * @author Threeboys33
 * @version 1.0.0
 **/
import java.util.Properties;
import com.alibaba.edas.acm.ConfigService;
import com.alibaba.edas.acm.exception.ConfigException;
import com.alibaba.edas.acm.listener.ConfigChangeListener;
// Sample code, for illustration only
public class ACMTest {

    // Properties/Switch
    private static String config = "DefaultValue";

    private static Properties acmProperties = new Properties();

    public static String testAcm() {
        String content = null;
        try {

            /**
             *  In production environment, parameters are passed through JVM so that you can use multiple environment，
             *  -Daddress.server.domain=jmenv-dncs.cn-xian-xagjyx-am550002-b.res.xagjyx.com
             *  -Dtenant.id=b706960c-0455-4815-9a25-621182f777b8
             *  -Dproject.name=acmtest
             *  -Dspas.identity=${home}\.spas_key\acmtest
             *  Input accessKey/secretKey in your local acmtest file in the following format.
             *  accessKey=$accessKey
             *  secretKey=$secretKey
             */
            Properties properties = new Properties();
            properties.put("endpoint", "jmenv-dncs.cn-xian-xagjyx-am550002-b.res.xagjyx.com");
            properties.put("namespace", "b706960c-0455-4815-9a25-621182f777b8");
            properties.put("accessKey", "5d8d1a613a2e47d0b76df90c80f7826b");
            properties.put("secretKey", "psEFoS2Fa1if5XCywiWdGfaZhZc=");

            ConfigService.init(properties);
            // Get configuration proactively
            content = ConfigService.getConfig("com.acm.myapp.app.cfg", "myapp", 6000);
            System.out.println(content);
            // Add listener for the configuration during initialization, so that configuration changes will trigger callback notifications.
            ConfigService.addListener("com.acm.myapp.app.cfg", "myapp", new ConfigChangeListener() {
                public void receiveConfigInfo(String configInfo) {
                    // When the configuration is updated, the callback function will send the new value to the user.
                    // Note that you should not perform any block operations in the callback function. Otherwise the thread will be blocked.
                    config = configInfo;
                    System.out.println(configInfo);
                }
            });

            return content;
            /**
             * If the configuration value is in the format of properties (key=value), you can use the following listener to configure multiple configuration items in one configuration.
             */

            /**
             ConfigService.addListener("com.acm.myapp.app.cfg", "myapp", new PropertiesListener() {

            @Override
            public void innerReceive(Properties properties) {
            // TODO Auto-generated method stub
            acmProperties = properties;
            System.out.println(properties);
            }
            });

             **/

        } catch (ConfigException e) {
            e.printStackTrace();
        }

        // In this sample, the main thread does not exit, because the configuration subscription is the daemon thread, and it will exit if the main threads exits. The following code is not needed in real scenarios.
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public static void main(String[] args) {
        try {

            /**
             *  In production environment, parameters are passed through JVM so that you can use multiple environment，
             *  -Daddress.server.domain=jmenv-dncs.cn-xian-xagjyx-am550002-b.res.xagjyx.com
             *  -Dtenant.id=b706960c-0455-4815-9a25-621182f777b8
             *  -Dproject.name=acmtest
             *  -Dspas.identity=${home}\.spas_key\acmtest
             *  Input accessKey/secretKey in your local acmtest file in the following format.
             *  accessKey=$accessKey
             *  secretKey=$secretKey
             */
            Properties properties = new Properties();
            properties.put("endpoint", "jmenv-dncs.cn-xian-xagjyx-am550002-b.res.xagjyx.com");
            properties.put("namespace", "b706960c-0455-4815-9a25-621182f777b8");
            properties.put("accessKey", "5d8d1a613a2e47d0b76df90c80f7826b");
            properties.put("secretKey", "psEFoS2Fa1if5XCywiWdGfaZhZc=");

            ConfigService.init(properties);
            // Get configuration proactively
            String content = ConfigService.getConfig("com.acm.myapp.app.cfg", "myapp", 6000);
            System.out.println(content);
            // Add listener for the configuration during initialization, so that configuration changes will trigger callback notifications.
            ConfigService.addListener("com.acm.myapp.app.cfg", "myapp", new ConfigChangeListener() {
                public void receiveConfigInfo(String configInfo) {
                    // When the configuration is updated, the callback function will send the new value to the user.
                    // Note that you should not perform any block operations in the callback function. Otherwise the thread will be blocked.
                    config = configInfo;
                    System.out.println(configInfo);
                }
            });

            /**
             * If the configuration value is in the format of properties (key=value), you can use the following listener to configure multiple configuration items in one configuration.
             */

            /**
             ConfigService.addListener("com.acm.myapp.app.cfg", "myapp", new PropertiesListener() {

            @Override
            public void innerReceive(Properties properties) {
            // TODO Auto-generated method stub
            acmProperties = properties;
            System.out.println(properties);
            }
            });

             **/

        } catch (ConfigException e) {
            e.printStackTrace();
        }

        // In this sample, the main thread does not exit, because the configuration subscription is the daemon thread, and it will exit if the main threads exits. The following code is not needed in real scenarios.
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Expose the configuration value with the GET interface
    public static String getConfig() {
        return config;
    }

    // Expose the configuration value with the GET interface
    public static Object getPorpertiesValue(String key) {
        if (acmProperties != null) {
            return acmProperties.get(key);
        }
        return null;
    }

}