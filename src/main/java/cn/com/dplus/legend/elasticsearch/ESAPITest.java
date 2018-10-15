package cn.com.dplus.legend.elasticsearch;

import org.elasticsearch.action.admin.indices.alias.get.GetAliasesResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.metadata.IndexMetaData;
import org.elasticsearch.common.collect.ImmutableOpenMap;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @Description:
 * @Author: 詹军政|zhanjz@sondon.net
 * @Date: Created in 上午11:30 18-2-24
 * @Modified By:
 */
public class ESAPITest {

    static final String host = "10.3.5.20";
    public static Client client;

    static {
        Settings settings = Settings.settingsBuilder().put("client.transport.sniff", true).build();
        try {
            //创建节点客户端，9300位节点的端口，不是集群端口
            client = TransportClient.builder().settings(settings).build()
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), 9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static GetResponse get(String id) {
        return client.prepareGet("service-access-2018.02.22", "fluentd", id).get();
    }

    public static SearchResponse search() {
        return client.prepareSearch("service-access*").setTypes("fluentd").setSearchType(SearchType.QUERY_AND_FETCH)
                .addSort(SortBuilders.fieldSort("@timestamp").order(SortOrder.DESC))
                .setFrom(0).setSize(10).setExplain(true).get();
    }

    public static void searchByIndex() {
        GetAliasesResponse getAliasesResponse = client.admin().indices().prepareGetAliases().setIndices("service-access-*").get();
        System.out.println(getAliasesResponse);
    }

    public static void getAllIndex() {
//        String[] indexList = client.admin().cluster().prepareState().execute().actionGet().getState().getMetaData().concreteAllIndices();
//        for(String index: indexList){
//            System.out.println(index);
//        }
        String[] concreteAllIndices = client.admin().cluster()
                .prepareState().execute()
                .actionGet().getState()
                .getMetaData().getConcreteAllOpenIndices();
//        Set<String> collect = StreamSupport.stream(aliasMap.spliterator(), false).flatMap((e) -> {
//            Iterable<String> iterable = () -> e.value.getAliases().keysIt();
//            return StreamSupport.stream(iterable.spliterator(), false);
//        }).collect(Collectors.toSet());
//        collect.forEach(System.out::println);
        System.out.println("ok");
    }

    public static void main(String[] args) {
//        GetResponse response = get("AWG7VJeM1AGN8OBTj5is");
//        SearchResponse response = search();
////        System.out.println(response);
//        SearchHit[] hits = response.getHits().hits();
//
//        for(SearchHit hit : hits){
//            System.out.println(hit.getSource().get("message"));
//        }
//        hits.forEach(h -> System.out.println(h.getSourceAsString()));

//        searchByIndex();
        getAllIndex();
    }
}
