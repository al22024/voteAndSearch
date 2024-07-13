/******************************************************************* 
***  File Name  : RequestVotingContent.java 
***  Version  : v1.0
***  Designer  : 井上 泰輝 
***  Date   : 2024.06.12 
***  Purpose        : GooglePlacesAPIを呼び出して、データベースから取得した検索条件から作成したクエリ(検索ワード)での検索結果を返す。 
*** 
*******************************************************************/

package display;
import java.io.*;
import java.net.*;
import java.security.*;
import java.util.*;

import com.google.api.client.util.Key;
import com.google.gson.*;

import dbtest.*;


public class RequestVotingContent {
    //private static final String API_KEY = "AIzaSyBPzkG_kAhyOqpXSfnMH-TIbtevcFvtjsk";
	String API_KEY = "AIzaSyBPzkG_kAhyOqpXSfnMH-TIbtevcFvtjsk";
	//private static final String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";
	String PLACES_SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json";
	
	String name;
	String date;
    
	public static String findMode(List<String> list) {
        if (list == null || list.isEmpty()) {
            return ""; // リストが空の場合は空文字を返す
        }

        // 出現回数を記録するためのマップ
        Map<String, Integer> frequencyMap = new HashMap<>();

        // リストをイテレートして出現回数をカウント
        for (String s : list) {
            frequencyMap.put(s, frequencyMap.getOrDefault(s, 0) + 1);
        }

        // 最大出現回数を求める
        int maxFrequency = Collections.max(frequencyMap.values());

        // 最頻値を特定
        String mode = null;
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                mode = entry.getKey();
                break;
            }
        }

        return mode;
    }

    
    // C8 企画情報管理部に企画ID に対応した企画の投票内容を取得させる.
    public String[] request(int arg) {
    	String ret[] = new String[3];
    	int KEY = arg;
    	UserAndProjectInfo userAndProjectInfo = new UserAndProjectInfo();
    	UserAndProjectInfo UAPI = userAndProjectInfo.getVoteInfo(KEY);
    	int projectID = UAPI.projectID;
    	ProjectInfo projectinfo = new ProjectInfo();
    	ProjectInfo pi = projectinfo.getProjectInfo(projectID);
    	String region = pi.region;
    	
    	List<String> genre = UAPI.genreList;
    	List<String> budget1 = UAPI.budget1List;
    	int b1 = 0;
    	for (int i = 0; i < budget1.size(); i++) {
    		b1 += Integer.parseInt(budget1.get(i));
    	}
    	
    	if (budget1.size() == 0 || budget1 == null) {
    		b1 = 334264;
    	}
    	else {
    		b1 /= budget1.size();
    	}
    	
    	List<String> budget2 = UAPI.budget2List;
    	int b2 = 0;
    	for (int i = 0; i < budget2.size(); i++) {
    		b2 += Integer.parseInt(budget2.get(i));
    	}
    	
    	if (budget2.size() == 0 || budget2 == null) {
    		b2 = 334;
    	}
    	else {
    		b2 /= budget2.size();
    	}
    	
    	
    	if (b1 < 1000){
    		b1 = 1;
    	}
    	else if (b1 >=1000 && b1 < 3000) {
    		b1 = 2;
    	}
    	else if (b1 >= 3000 && b1 < 5000) {
    		b1 = 3;
    	}
    	else if (b1 >= 5000) {
    		b1 = 4;
    	}
    	else {
    		b1 = 0;
    	}
    	
    	
    	if (b2 < 1000){
    		b2 = 1;
    	}
    	else if (b2 >=1000 && b2 < 3000) {
    		b1 = 2;
    	}
    	else if (b2 >= 3000 && b2 < 5000) {
    		b2 = 3;
    	}
    	else if (b2 >= 5000) {
    		b2 = 4;
    	}
    	else {
    		b2 = 4;
    	}
    	//ret[0] = "つくば市 ハンバーガー";	//仮のクエリ
    	ret[0] = region + " " + findMode(genre) + "&minprice=" + b1 + "&maxprice=" + b2;
    	ret[1] = pi.projectName;	//イベント名
    	ret[2] = "" + pi.dateTime;
    	
    	
    	
    	
    	
    	
    	
    	
    	return ret;
    }
    
    
    // 投票結果をもとに検索する. 
    public List<String[]> search(String args, double r) throws GeneralSecurityException, IOException {
    	
    	
    	
        
        List<String[]> list = new ArrayList<String[]>();
        if (args != null && !args.isEmpty()) {
            String query = URLEncoder.encode(args, "UTF-8");
            String s_url = PLACES_SEARCH_URL + "?query=" + query + "&key=" + API_KEY + "&language=ja";
            URL url = new URL(s_url);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()), "UTF-8"));
            JsonObject googleApiResponseJsonObject = (new Gson()).fromJson(br, JsonObject.class);

            if (googleApiResponseJsonObject.has("results")) {
                JsonArray results = googleApiResponseJsonObject.getAsJsonArray("results");
                for (int n = 0; n < results.size(); n++) {
                    JsonObject result = results.get(n).getAsJsonObject();
                    String[] temp = new String[3];
                    if (result.has("name")) {
                    	temp[0] = result.get("name").getAsString();
                    	String[] sp = temp[0].split(" ");
                    	temp[0] = "";
                    	for (int i = 0; i < sp.length; i++) {
                    		temp[0] += sp[i];
                    	}
                    }
                    if (result.has("formatted_address")) {
                    	temp[1] = result.get("formatted_address").getAsString();
                    	String[] sp = temp[1].split(" ");
                    	temp[1] = "";
                    	for (int i = 1; i < sp.length; i++) {
                    		temp[1] += sp[i];
                    	}
                    	
                    }
                    
                    /*if (result.has("price_level")) {
                    	temp[3] = result.get("price_level").getAsString();
                    	String[] sp = temp[3].split(" ");
                    	temp[3] = "";
                    	for (int i = 0; i < sp.length; i++) {
                    		temp[3] = sp[i];
                    	}
                    	
                    	if (temp[3] == null) {
                    		temp[3] = "情報なし";
                    	}
                    	else if (temp[3].equals("0")) {
                    		temp[3] = "無料";
                    	}
                    	else if (temp[3].equals("1")) {
                    		temp[3] = "安い";
                    	}
                    	else if (temp[3].equals("2")) {
                    		temp[3] = "お手頃";
                    	}
                    	else if (temp[3].equals("3")) {
                    		temp[3] = "比較的高価";
                    	}
                    	else if (temp[3].equals("4")) {
                    		temp[3] = "高価";
                    	}
                    	else if (temp[3].equals("")) {
                    		temp[3] = "情報なし";
                    	}
                    	else {
                    		temp[3] = "情報なし";
                    	}
                    }*/
                    
                    if (result.has("rating")) {
                    	temp[2] = result.get("rating").getAsString();
                    }
                    if (Double.parseDouble(temp[2]) < r){
                    	continue;
                    }
                    
                    list.add(temp);
                    
                }
            } else {
            	String[] temp = {"name", "formatted_address", "rating"};
            	list.add(temp);
            }

            br.close();
            conn.disconnect();
        } else {
        	String[] temp = {"null", "null", "null"};
        	list.add(temp);
        }
        Collections.sort(list, new PlaceListComparator());
        return list;
    }

    
    // レスポンスを格納するクラス
    public static class PlacesSearchResponse {
        @Key
        public List<Place> results;
    }

    
    // レスポンスの形式用クラス
    public static class Place {
        @Key
        public String name;

        @Key
        public String formatted_address;

        @Key
        public float rating;
        
    }
    
    public class PlaceListComparator implements Comparator<String[]> {

		@Override
		public int compare(String[] o1, String[] o2) {
			if (Double.parseDouble(o2[2]) - Double.parseDouble(o1[2]) > 0) {
				return 1;
			}
			
			else {
				return -1;
			}
		}
    }
}