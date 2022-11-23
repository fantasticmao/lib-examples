from redis.cluster import ClusterNode
from redis.cluster import RedisCluster

key_patterns = (
    "user:Tom:*",
    "user:Bob:*",
    "user:Annie:*",
    "user:Jason:*",
)
startup_nodes = [
    ClusterNode(host="192.168.1.100", port=6379),
    ClusterNode(host="192.168.1.101", port=6379),
    ClusterNode(host="192.168.1.102", port=6379)
]
redis_client = RedisCluster(startup_nodes=startup_nodes, password=None,
                            decode_responses=True)


def main():
    for key_pattern in key_patterns:
        cleanup(key_pattern)


def cleanup(pattern):
    count = 0
    print("scan keys by pattern [{}]".format(pattern))
    for key in redis_client.scan_iter(match=pattern, count=1000):
        redis_client.delete(key)
        count = count + 1
    print("[{}] keys matching pattern [{}] are deleted".format(count, pattern))


if __name__ == "__main__":
    main()
