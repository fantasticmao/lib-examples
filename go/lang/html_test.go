package lang

import (
	"github.com/stretchr/testify/assert"
	"html"
	"testing"
)

// see https://pkg.go.dev/html

func TestHtmlEscape(t *testing.T) {
	s := html.EscapeString("<script>eval(\"1+2\")</script>")
	assert.Equal(t, "&lt;script&gt;eval(&#34;1+2&#34;)&lt;/script&gt;", s)
}

func TestHtmlUnescape(t *testing.T) {
	s := html.UnescapeString("&lt;script&gt;eval(&#34;1+2&#34;)&lt;/script&gt;")
	assert.Equal(t, "<script>eval(\"1+2\")</script>", s)
}
