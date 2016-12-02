Nginx配置
---
- `user nginx;` 运行用户
- `worker_processes auto;` 开启进程数，应当为CPU核的2陪
- `error_log /var/log/nginx/error.log warn;` 错误日志文件
- `pid /var/run/nginx.pid;` 进程号保存文件

# events
- `# use epoll` Linux下的开启，采用Linux内核epoll事件处理机制
- `worker_connections 1204;` 每个进程最大的连接数

# http
- `include mime.types;` 导入文件扩展名与文件类型映射表
- `default_type application/octet-stream;` 默认请求的返回文件类型
- `sendfile on;` 开启发送文件
- `keepalive_timeout 65;` 设置http持久连接

### listen
- 语法：`listen address[:port] [default_server] [ssl] [http2|spdy] [proxy_protocol] ...`
- 默认：`listen *:80 | *:8000;`
- 上下文：`server`

> Sets the address and port for IP, or the path for a UNIX-domain socket on which the server will
> accept requests. Both address and port, or only address or only port can be specified. An address may
> also be a hostname, for example:

为Nginx接受的请求，设置IP的`address`地址和`port`端口或设置UNIX域套接字`path`路径。可以指定地址和端口，或仅地址和仅端口。地址也可以是域名：

```
listen 127.0.0.1:8000;
listen 127.0.0.1;
listen 8000;
listen *:8000;
listen localhost:8000;
```

> IPv6 addresses (0.7.36) are specified in square brackets:

IPv6 address使用方括号指定：

```
listen [::]:8000;
listen [::1];
```

> UNIX-domain sockets (0.8.21) are specified with the “unix:” prefix: 

UNIX-domain sockets使用以`unix:`开头指定：

```
listen unix:/var/run/nginx.sock;
```

> If only address is given, the port 80 is used.

若仅有`address`，则默认使用80`port`。

> If the directive is not present then either *:80 is used if nginx runs with the superuser
> privileges, or *:8000 otherwise.

`listen`配置不存在的情况下，如果Nginx是以超级管理员权限运行，那么使用`*:80`，否则使用`*:8000`。

> The default_server parameter, if present, will cause the server to become the default server for the
> specified address:port pair. If none of the directives have the default_server parameter then the
> first server with the address:port pair will be the default server for this pair.

如果存在配置`default_server`参数，那么当前指定的`address:port`成为默认虚拟主机。如果不存在配置`default_server`参数，那么第一个`address:port`将会成为默认的虚拟主机。

详情见[官方文档](http://nginx.org/en/docs/http/ngx_http_core_module.html#listen)

### server_name
- 语法：`server_name name ...`
- 默认：`server_name "";`
- 上下文：`server`

> Sets names of a virtual server,for example:<br>
> 
> server {<br>
>   server_name example.com www.example.com;<br>
> }
> 
> The first name becomes the primary server name
> Server names can include an asterisk (“*”) replacing the first or last part of a name:
>
> server {<br>
>   server_name example.com *.example.com www.example.*;<br>
> }

详情见[官方文档](http://nginx.org/en/docs/http/ngx_http_core_module.html#server_name)

### [location](http://nginx.org/en/docs/http/ngx_http_core_module.html#location)
- 语法：`location [=|~|~*|^~] uri {...}` `location @name {...}`
- 默认：`—` 
- 上下文：`server`、`location`

> A location can either be defined by a prefix string, or by a regular expression.Regular
> expressions are specified with the preceding “~*” modifier (for case-insensitive matching), or
> the “~” modifier (for case-sensitive matching).

location可以被**前缀字符串**或者**正则表达式**定义。正则表达式通过`~*`修饰符（不敏感大小写）和`~`修饰符（敏感大小写）指定。

> If the longest matching prefix location has the “^~” modifier then regular expressions are not checked.

如果最长的location匹配了`^~`修饰的**前缀字符串**，那么正则表达式将不会被检查。

> Also, using the “=” modifier it is possible to define an 
> exact match of URI and location. If an exact match is found, the search terminates.

使用`=`修饰符定义URI和location的精确匹配。如果精确匹配成功，那么终止搜索。

```
location = / {
    [configuration A]
}
location / {
    [configuration B]
}
location /documents/ {
    [configuration C]
}
location ^~ /images/ {
    [configuration D]
}
location ~* \.(gif|jpg|jpeg)$ {
    [configuration E]
}
1. /                        --> configuration A
2. /index.html              --> configuration B
3. /documents/document.html --> configuration C
4. /images/1.gif            --> configuration D
5. /documents/1.jpg         --> configuration E
```
详情见[官方文档](http://nginx.org/en/docs/http/ngx_http_core_module.html#location)

#### proxy_pass
- 语法：`proxy_pass URL;`
- 默认：`—`
- 上下文：`location` `if in location` `limit_except`

> If the proxy_pass directive is specified with a URI, then when a request is passed to the 
> server, the part of a normalized request URI matching the location is replaced by a URI 
> specified in the directive:

当请求传递给Nginx时，若`proxy_pass`指定了URI，则规范化请求URI中匹配location的部分被`proxy_pass`指定的URI替换。（大白话就是/name/匹配了/remote/，将被替换）

```
location /name/ {
    proxy_pass http://127.0.0.1/remote/;
}
```

> If proxy_pass is specified without a URI, the request URI is passed to the server in the same 
> form as sent by a client when the original request is processed, or the full normalized 
> request URI is passed when processing the changed URI:

若`proxy_pass`未指定URI，则处理源请求URI时，请求URI以客户端发出的相同形式传递给Nginx，或者处理已改变的URI时，传递所有规范化的URI。

```
location /some/path/ {
    proxy_pass http://127.0.0.1;
}
```

```
if (proxy_pass指定URI) {
    // 规范化请求URI中匹配location的部分被proxy_pass指定的URI替换
    return http://127.0.0.1/path/......;
} else {
    if (处理源请求URI) {
        // 客户端发出的相同形式传递
        return http://127.0.0.1;
    } else {
        // 传递所有规范化的URI
        return http://127.0.0.1/all/; 
    } 
}
```
详情见[官方文档](http://nginx.org/en/docs/http/ngx_http_proxy_module.html#proxy_pass)