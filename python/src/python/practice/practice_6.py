#!/usr/local/bin/python3
# Python 练习6 - HTTP 和 Socket

import socket
import time
from threading import Thread

import requests


def http_get():
    response = requests.get("https://httpbin.org/get")
    if response.status_code == 200:
        print(response.json())
    else:
        print("请求失败 status code: {}".format(response.status_code))


def http_post():
    response = requests.post("https://httpbin.org/post", json=r'{"username":"MaoMao"}')
    if response.status_code == 200:
        print(response.json())
    else:
        print("请求失败 status code: {}".format(response.status_code))


def socket_server():
    server = socket.socket(family=socket.AF_INET, type=socket.SOCK_STREAM)
    host = socket.gethostname()
    port = 9999
    server.bind((host, port))
    server.listen(5)
    while True:
        client, addr = server.accept()
        print("接收客户端连接: {}".format(addr))
        client.send("hello client".encode("utf-8"))
        client.close()
        break


def socket_client():
    client = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    host = socket.gethostname()
    port = 9999
    client.connect((host, port))
    msg = client.recv(128)
    client.close()
    print("接收服务端数据: ", msg.decode("utf-8"))


def main():
    thread = Thread(target=socket_server)
    thread.start()
    time.sleep(3)
    socket_client()


if __name__ == "__main__":
    main()
