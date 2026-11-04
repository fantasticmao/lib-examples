package lang

import (
	"log/slog"
	"os"
	"testing"
)

// see https://pkg.go.dev/log/slog

func TestLog(t *testing.T) {
	slog.Info("hello", "count", 3)
	slog.Warn("hello", "count", 3)
	slog.Error("hello", "count", 3)
}

func TestTextHandler(t *testing.T) {
	logger := slog.New(slog.NewTextHandler(os.Stderr, &slog.HandlerOptions{
		Level: slog.LevelInfo,
	}))
	logger.Debug("hello", "count", 3)
	logger.Info("hello", "count", 3)
}

func TestJSONHandler(t *testing.T) {
	logger := slog.New(slog.NewJSONHandler(os.Stderr, nil))
	logger.Info("hello", "count", 3)
}
