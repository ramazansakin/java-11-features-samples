package com.rsakin.java11.http;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.stream.Collectors.toList;

// HttpClient sample code blocks
@Slf4j
public class Example {

    private static final String RESPONSE_BODY = "Response Body : ";
    private static final String RESPONSE_CODE = "Response Code : ";
    private static final String GOOGLE_COM = "https://www.google.com";
    private static final String HOSTNAME = "127.0.0.1";
    private static final String HTTPS_LABS_CONSOL = "https://labs.consol.de/";
    private static final String HTTP_LOCALHOST_UPLOAD = "http://localhost:8080/upload/";
    private static final String SEPERATOR = "###----------------------------------------------------###";
    private static final String HTTPS_LABS_CONSOL_URI = "https://labs.consol.de";
    private static final String HTTPS_HTTP_2_AKAMAI_COM_DEMO = "https://http2.akamai.com/demo";

    // GET sync uri
    public static void syncGet(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        log.info("Status code : " + response.statusCode());
        log.info("Response body :  " + response.body());
    }

    // GET - Asynchronous call
    public static void asyncGet(String uri) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .build();

        CompletableFuture<HttpResponse<String>> responseCompletableFuture = client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        responseCompletableFuture.whenComplete((resp, t) -> {
            if (t != null) {
                t.printStackTrace();
            } else {
                log.info(RESPONSE_BODY + resp.body());
                log.info(RESPONSE_CODE + resp.statusCode());
            }
        }).join();
    }

    // Download File
    public static void downloadFile(String uri) throws IOException, URISyntaxException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

        Path tempFile = Files.createTempFile("consol-labs-home", ".html");
        HttpResponse<Path> response = client.send(request, HttpResponse.BodyHandlers.ofFile(tempFile));
        log.info(RESPONSE_CODE + response.statusCode());
        log.info(RESPONSE_BODY + response.body());
    }

    // 上传文件
    public static void uploadFile(String uri) throws IOException, URISyntaxException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .POST(HttpRequest.BodyPublishers.ofFile(Paths.get("/tmp/files-to-upload.txt")))
                .build();

        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());
        log.info("Status Code : " + response.statusCode());
    }

    // proxying
    public static void proxy() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .proxy(ProxySelector.of(new InetSocketAddress(HOSTNAME, 1080)))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(GOOGLE_COM))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(RESPONSE_CODE + response.statusCode());
        log.info(RESPONSE_BODY + response.body());
    }

    // basic auth
    public static void basicAuth() throws URISyntaxException, IOException, InterruptedException {
        HttpClient client = HttpClient.newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("username", "password".toCharArray());
                    }
                })
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(HTTPS_LABS_CONSOL_URI))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info(RESPONSE_BODY + response.statusCode());
        log.info(RESPONSE_CODE + response.body());
    }

    // try HTTP2
    public static void http2() throws URISyntaxException {
        HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .version(HttpClient.Version.HTTP_2)
                .build()
                .sendAsync(HttpRequest.newBuilder()
                                .uri(new URI(HTTPS_HTTP_2_AKAMAI_COM_DEMO))
                                .GET()
                                .build(),
                        HttpResponse.BodyHandlers.ofString())
                .whenComplete((resp, t) -> {
                    if (t != null) {
                        t.printStackTrace();
                    } else {
                        log.info(RESPONSE_BODY + resp.body());
                        log.info(RESPONSE_CODE + resp.statusCode());
                    }
                }).join();
    }

    // parallel request
    public void getURIs(List<URI> uris) {
        HttpClient client = HttpClient.newHttpClient();
        List<HttpRequest> requests = uris.stream()
                .map(HttpRequest::newBuilder)
                .map(HttpRequest.Builder::build)
                .collect(toList());

        CompletableFuture.allOf(requests.stream()
                .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .toArray(CompletableFuture<?>[]::new))
                .join();
    }

    // driver
    public static void main(String[] args) throws Exception {
        // test
        log.info("### syncGet test");
        syncGet(GOOGLE_COM);
        log.info(SEPERATOR);
        log.info("### http2 test");
        http2();
        log.info(SEPERATOR);
        log.info("### download file");
        downloadFile(HTTPS_LABS_CONSOL);
        log.info(SEPERATOR);
        log.info("### upload file");
        uploadFile(HTTP_LOCALHOST_UPLOAD);
    }

}
