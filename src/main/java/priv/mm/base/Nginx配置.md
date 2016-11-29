Nginx配置
---
Nginx配置主要分成四部分：main、server、upstream、location
  1. main设置的指令将影响其它所有部分的设置。
  2. server部分的指令主要用于指定虚拟主机域名、IP和端口。
  3. upstream的指令用于设置一系列的后端服务器，设置反向代理及后端服务器的负载均衡。
  4. location部分用于匹配网页位置（比如，根目录“/”,“/images”,等等）。

### main
- `woker_processes`