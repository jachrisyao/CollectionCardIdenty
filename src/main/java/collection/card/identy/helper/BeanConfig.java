package collection.card.identy.helper;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
    private final String hostName = "101.35.120.252";
    private final int port = 9200;
    private final String userName = "elastic";
    private final String password = "111111";

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));

        RestClientBuilder builder = RestClient.builder(new HttpHost(hostName, port)).setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider));
        return new RestHighLevelClient(builder);

//        RestClientBuilder builder = RestClient.builder(new HttpHost(userName + ":" + password + "@" + hostName, port, "http"));
//        builder.setRequestConfigCallback(requestConfigBuilder -> {
//            requestConfigBuilder.setConnectTimeout(60000);
//            requestConfigBuilder.setSocketTimeout(30000);
//            requestConfigBuilder.setConnectionRequestTimeout(60000);
//            return requestConfigBuilder;
//        });
//        builder.setHttpClientConfigCallback(httpClientBuilder -> {
//            httpClientBuilder.setMaxConnTotal(30);
//            httpClientBuilder.setMaxConnPerRoute(10);
//            CredentialsProvider provider = new BasicCredentialsProvider();
//            AuthScope scope = new AuthScope(hostName, 80, "http");
//            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(userName, password);
//            provider.setCredentials(scope, credentials);
//            httpClientBuilder.setMaxConnTotal(30).setMaxConnPerRoute(10).setKeepAliveStrategy((httpRequest, httpResponse) -> 50000).setDefaultCredentialsProvider(provider);
//            return httpClientBuilder;
//        });
//        return new RestHighLevelClient(builder);
    }
}
