import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.junit.Test;
public class PostJson {	@Test
	public void test1() throws Exception {
		String xmlString = "{\"club_name\": \"\",\"device_model\": \"\",\"device_token\": \"\",\"emblem_id\": 1,\"facebook_id\": null,\"field_uniform_items\": {\"gloves_item_id\": 2017,\"pants_item_id\": 2005,\"shirt_item_id\": 2001,\"shoes_item_id\": 2013,\"socks_item_id\": 2009},\"hometown_id\": 1,\"keeper_uniform_items\": {\"gloves_item_id\": 2037,\"pants_item_id\": 2025,\"shirt_item_id\": 2021,\"shoes_item_id\": 2033,\"socks_item_id\": 2029},\"twitter_id\": null,\"uuid\": \"550e8400-e29b-41d4-a716-446655440000\"}";
		try {
			URL url = new URL("http://bfb.localhost/api/v1/sign_up?client_type=2&client_version=28");
			URLConnection con = url.openConnection();
			con.setDoOutput(true);
			con.setRequestProperty("Pragma:", "no-cache");
			con.setRequestProperty("Cache-Control", "no-cache");
			con.setRequestProperty("Content-Type", "text/json");
			con.setRequestProperty("X-BFB-REQUESTHASH", "0b98392836e47d94d0b7e0f3d104e679ae0f5cc597040ed60bdc4943c6ff0ff7");
			OutputStreamWriter out = new OutputStreamWriter(
					con.getOutputStream());
			out.write(new String(xmlString.getBytes()));
			out.flush();
			out.close();
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String line = "";
			for (line = br.readLine(); line != null; line = br.readLine()) {
				System.out.println(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}