package lang

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"io"
	"net/http"
	"testing"
)

var message = "hello world"

func TestHttpServe(t *testing.T) {
	handler := func(writer http.ResponseWriter, request *http.Request) {
		_, _ = fmt.Fprint(writer, message)
	}
	http.HandleFunc("/hello", handler)

	err := http.ListenAndServe(":8080", nil)
	assert.Nil(t, err)
}

func TestHttpClient(t *testing.T) {
	client := http.Client{}
	resp, err := client.Get("http://localhost:8080/hello")
	assert.Nil(t, err)
	defer resp.Body.Close()

	content, err := io.ReadAll(resp.Body)
	assert.Nil(t, err)
	assert.Equal(t, message, string(content))
}
