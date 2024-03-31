package xnet

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"golang.org/x/net/websocket"
	"testing"
)

func TestWebsocketClient(t *testing.T) {
	origin := "http://127.0.0.1:9090/"
	url := "ws://127.0.0.1:9090/profile/tracing"
	ws, err := websocket.Dial(url, "", origin)
	assert.Nil(t, err)

	message := make([]byte, 512)
	n, err := ws.Read(message)
	assert.Nil(t, err)

	fmt.Println(string(message[:n]))
}
