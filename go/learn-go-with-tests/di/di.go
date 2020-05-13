package main

import (
	"fmt"
	"io"
	"net/http"
)

// 使用 io.Writer 接口
func Greet(w io.Writer, name string) (n int, err error) {
	n, err = fmt.Fprintf(w, "Hello %s", name)
	return
}

func MyGreeterHandler(w http.ResponseWriter, r *http.Request) {
	_, _ = Greet(w, "MaoMao")
}

func main() {
	// 监听端口，启动服务
	_ = http.ListenAndServe("localhost:5000", http.HandlerFunc(MyGreeterHandler))
}
