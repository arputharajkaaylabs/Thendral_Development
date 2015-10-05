package com.kaaylabs.thendral.util;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.Socket;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLHttpClient {
	 public DefaultHttpClient getHttpClient()
	            throws IOException,
	            NoSuchAlgorithmException, KeyManagementException,
	            URISyntaxException, KeyStoreException, UnrecoverableKeyException {
	        SSLContext ctx = SSLContext.getInstance("TLS");
	        ctx.init(null, new TrustManager[] { new CustomX509TrustManager() },
	                new SecureRandom());

	        HttpClient client = new DefaultHttpClient();

	        SSLSocketFactory ssf = new CustomSSLSocketFactory(ctx);
	        ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
	        ClientConnectionManager ccm = client.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        sr.register(new Scheme("https", ssf, 443));
	        DefaultHttpClient sslClient = new DefaultHttpClient(ccm,
	                client.getParams());


	        return sslClient;
	    }
}

class CustomSSLSocketFactory extends SSLSocketFactory {
   SSLContext sslContext = SSLContext.getInstance("TLS");

   public CustomSSLSocketFactory(KeyStore truststore)
           throws NoSuchAlgorithmException, KeyManagementException,
           KeyStoreException, UnrecoverableKeyException {
       super(truststore);

       TrustManager tm = new CustomX509TrustManager();

       sslContext.init(null, new TrustManager[] { tm }, null);
   }

   public CustomSSLSocketFactory(SSLContext context)
           throws KeyManagementException, NoSuchAlgorithmException,
           KeyStoreException, UnrecoverableKeyException {
       super(null);
       sslContext = context;
   }

   @Override
   public Socket createSocket(Socket socket, String host, int port,
           boolean autoClose) throws IOException {
       return sslContext.getSocketFactory().createSocket(socket, host, port,
               autoClose);
   }

   @Override
   public Socket createSocket() throws IOException {
       return sslContext.getSocketFactory().createSocket();
   }
}

class CustomX509TrustManager implements X509TrustManager {

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {
    }

    @Override
    public void checkServerTrusted(X509Certificate[] certs,
            String authType) throws CertificateException {

        // Here you can verify the servers certificate. (e.g. against one which is stored on mobile device)

        // InputStream inStream = null;
        // try {
        // inStream = MeaApplication.loadCertAsInputStream();
        // CertificateFactory cf = CertificateFactory.getInstance("X.509");
        // X509Certificate ca = (X509Certificate)
        // cf.generateCertificate(inStream);
        // inStream.close();
        //
        // for (X509Certificate cert : certs) {
        // // Verifing by public key
        // cert.verify(ca.getPublicKey());
        // }
        // } catch (Exception e) {
        // throw new IllegalArgumentException("Untrusted Certificate!");
        // } finally {
        // try {
        // inStream.close();
        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        // }
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}