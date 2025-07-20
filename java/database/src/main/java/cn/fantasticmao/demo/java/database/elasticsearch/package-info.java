/**
 * Elasticsearch
 * <p>
 * <ol>
 *     <li>启动 Elasticsearch Docker 容器</li>
 *     <li>创建索引：{@code curl -i -X PUT 'http://localhost:9200/bank' -H 'Content-Type: application/json' -d @bank_mapping.json}</li>
 *     <li>初始化数据：{@code curl -i -X POST 'http://localhost:9200/bank/_bulk' -H 'Content-Type: application/x-ndjson' --data-binary @bank_data.ndjson}</li>
 * </ol>
 *
 * @author fantasticmao
 * @see <a href="https://www.elastic.co/docs/solutions/search/get-started">Get started</a>
 * @see <a href="https://www.elastic.co/docs/deploy-manage/deploy/self-managed/install-elasticsearch-docker-basic">Start a single-node cluster in Docker</a>
 * @since 2021-12-24
 */
package cn.fantasticmao.demo.java.database.elasticsearch;
