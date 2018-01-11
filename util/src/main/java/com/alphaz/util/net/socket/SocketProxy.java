package com.alphaz.util.net.socket;


import com.alphaz.util.net.http.HttpProxy;
import com.alphaz.util.valid.ValideHelper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * ProjectName: alphaz
 * PackageName: com.alphaz.util.net
 * User: C0dEr
 * Date: 2017/3/18
 * Time: 下午5:32
 * Description:This is a class of com.alphaz.util.net
 */
public class SocketProxy {
    private static SocketProxy socketProxy = null;//单例
    private static Map<String, SocketParameters> parameters;
    public SocketParameters ps;

    private SocketProxy() {

    }

    public static SocketProxy Instance(String apiName) {
        if (socketProxy == null) {
            socketProxy = new SocketProxy();
            init();
        }
        socketProxy.ps = parameters.get(apiName) == null ? new SocketParameters() : parameters.get(apiName);
        return socketProxy;
    }

    private static void init() {

        try {
            byte[] json = Files.readAllBytes(Paths.get(HttpProxy.class.getResource("/socketConfig.json").toURI()));
            parameters = new ObjectMapper().readValue(new String(json), new TypeReference<Map<String, SocketParameters>>() {
            });
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Do request api response model.
     *
     * @param callback the callback
     * @return the api response model
     */
    public void DoRequest(HandleResponse callback) {
        if (ValideHelper.isNullOrEmpty(ps.getUrl())) {
            return;
        }
        Socket socket = null;
        OutputStream write = null;
        BufferedReader in = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(ps.getUrl(), ps.getPort()), 1000 * 2);
            socket.setSoTimeout(1000 * 60);
            write = socket.getOutputStream();
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                if (callback.HandResponse(in, write)) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (write != null) {
                    write.close();
                }
                if (in != null) {
                    in.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * The interface Handle response callback.
     */
    public interface HandleResponse {

        boolean HandResponse(BufferedReader in, OutputStream out);
    }

}
