/**
 * Elasticsearch
 * <p>
 * <ol>
 *     <li>启动 Elasticsearch Docker 容器 {@code docker run -d -p 9200:9200 -e "discovery.type=single-node" -e "xpack.security.enabled=false" --rm --name elasticsearch-test docker.elastic.co/elasticsearch/elasticsearch:7.16.2}</li>
 *     <li>创建索引：{@code curl -i -X PUT 'http://localhost:9200/bank' -H 'Content-Type: application/json' -d @bank_mapping.json}</li>
 *     <li>初始化数据：{@code curl -i -X POST 'http://localhost:9200/bank/_bulk' -H 'Content-Type: application/x-ndjson' --data-binary @bank_data.ndjson}</li>
 * </ol>
 *
 * @author fantasticmao
 * @see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/current/getting-started.html">Quick start</a>
 * @since 2021-12-24
 */
package cn.fantasticmao.demo.java.database.elasticsearch;