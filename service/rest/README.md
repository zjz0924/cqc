## rest 是由 Jersey 做成的 Restful 服务 ##

使用提示：

1. **APIRequestAuthFilter**：权限过滤器，用来进行权限控制
   **RestApplication**: 用来注册一些功能类

2. 只要在实体类上配置上适当的注解，会自动去检查数据的合法性

3. rest 会打包成 jar 包, 使用的步骤是：
   - 在pom.xml文件中引入 rest 
   - 通过servlet和filter来配置上面两个文件(web.xml配置)
    
    ``` java
    <!-- jersey 访问权限检查过滤器（APIRequestAuthFilter 必须已经注册成bean） -->
	<filter>
        <filter-name>APIRequestAuthFilter</filter-name>
        <filter-class>
          org.springframework.web.filter.DelegatingFilterProxy
        </filter-class>
        <init-param>
            <param-name>targetFilterLifecycle</param-name>
            <param-value>true</param-value>
        </init-param>
    </filter>
    <filter-mapping>
        <filter-name>APIRequestAuthFilter</filter-name>
        <url-pattern>/api/\*</url-pattern> <!-- 把\去掉，markdown需要转义 -->
    </filter-mapping>
	
	<!-- jersey 配置 -->
	<servlet>
		<servlet-name>jersey-servlet</servlet-name>
		<servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
		<init-param>
			<param-name>javax.ws.rs.Application</param-name>
			<param-value>cn.wow.rest.RestApplication</param-value>
		</init-param>
		<init-param>
			<param-name>jersey.config.server.provider.packages</param-name>
			<param-value>cn.wow.rest.service</param-value> <!-- service 的路径 -->
		</init-param>
		<load-on-startup>1</load-on-startup>
	</servlet>
	<servlet-mapping>
		<servlet-name>jersey-servlet</servlet-name>
		<url-pattern>/api/\*</url-pattern> <!-- 把\去掉，markdown需要转义 -->
	</servlet-mapping>
    ```



   (注意是否已经自动注册成bean)
	
4. 访问地址：
   http://ip:port/api/path名称/  (api在web.xml文件中配置的)

5. api 访问的参数： (在header中加上)
Content-type:  application/json   
Authorization:	Basic YWRtaW46d2Fwd2FwMTI=
（YWRtaW46d2Fwd2FwMTI= 是 用户名:密码 用来base64加密而成）
