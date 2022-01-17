package xnet

import (
	"fmt"
	"github.com/stretchr/testify/assert"
	"strings"
	"testing"
)
import "golang.org/x/net/html"

func TestParse(t *testing.T) {
	s := `<p>golang.org/x/net:</p><ul><li>dns</li><li>html</li><li>icmp</li></ul>`
	doc, err := html.Parse(strings.NewReader(s))
	assert.Nil(t, err)

	var f func(*html.Node)
	f = func(n *html.Node) {
		// MDN Web Docs Node.nodeType https://developer.mozilla.org/en-US/docs/Web/API/Node/nodeType
		switch n.Type {
		case html.ElementNode:
			fmt.Printf("Element Node: %s\n", n.Data)
		case html.DocumentNode:
			fmt.Printf("Document Node: %s\n", n.Data)
		case html.TextNode:
			fmt.Printf("Text Node: %s\n", n.Data)
		}

		for c := n.FirstChild; c != nil; c = c.NextSibling {
			f(c)
		}
	}
	f(doc)
}
