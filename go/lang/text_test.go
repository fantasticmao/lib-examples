package lang

import (
	"bytes"
	"github.com/stretchr/testify/assert"
	"testing"
	"text/template"
)

// see https://pkg.go.dev/text/template

type Inventory struct {
	Material string
	Count    uint
}

func TestTemplateSyntax(t *testing.T) {
	sweaters := Inventory{"wool", 17}

	tmpl, err := template.New("test").Parse("{{ .Count }} items are made of {{ .Material }}")
	assert.Nil(t, err)

	output := &bytes.Buffer{}
	err = tmpl.Execute(output, sweaters)
	assert.Nil(t, err)
	assert.Equal(t, "17 items are made of wool", output.String())
}

func TestTemplateSpaceTrim(t *testing.T) {
	tmpl, err := template.New("test").Parse("{{23 -}} < {{- 45}}")
	assert.Nil(t, err)

	output := &bytes.Buffer{}
	err = tmpl.Execute(output, nil)
	assert.Nil(t, err)
	assert.Equal(t, "23<45", output.String())
}
