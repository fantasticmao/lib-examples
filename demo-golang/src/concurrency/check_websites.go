package concurrency

type WebsiteChecker func(string) bool

type result struct {
	string
	bool
}

func CheckWebsites(checker WebsiteChecker, urls []string) map[string]bool {
	results := make(map[string]bool)
	resultChannel := make(chan result)
	for _, url := range urls {
		// 开启一个新的 goroutine
		go func(u string) {
			// send statement
			resultChannel <- result{u, checker(u)}
		}(url)
	}
	for i := 0; i < len(urls); i++ {
		// receive expression
		result := <-resultChannel
		results[result.string] = result.bool
	}
	return results
}
