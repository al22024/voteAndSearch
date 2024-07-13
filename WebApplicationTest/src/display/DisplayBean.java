package display;

import java.io.*;
import java.security.*;
import java.util.*;

import dbtest.*;

public class DisplayBean implements Serializable {
	public List<String[]> list;
	public String name;	//イベント名
	public String date;
	public String query;
	
	///
	public List<String> genre;
	public List<String> budget1;
	public List<String> budget2;
	public List<String> review;
 	///
	
	public static String findMode(List<String> list) {
        if (list == null/* || list.isEmpty()*/) {
            return "和菓子"; // リストが空の場合は空文字を返す
        }
        else if (list.isEmpty()) {
        	return "洋菓子";
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
	
	public List<String[]> getList() {
		return list;
	}
	
	public void setList(List<String[]> list) {
		this.list = list;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getQuery() {
		return query;
	}
	
	public void setQuery(String query) {
		this.query = query;
	}
	
	public void makeList(int KEY){
		
		list = new ArrayList<String[]>();
		
		RequestVotingContent rvc = new RequestVotingContent();
		String[] temp = new String[3];// = rvc.request(KEY);	//KEYにデータベース用KEY値入れろ
		
		
		int projectID = KEY;
		
		
		UserAndProjectInfo userAndProjectInfo = new UserAndProjectInfo();
    	UserAndProjectInfo UAPI = userAndProjectInfo.getVoteInfo(projectID);
		/*UserAndProjectInfo UAPI = new UserAndProjectInfo();
		UAPI.getVoteInfo(projectID);*/
    	
    	
    	
    	//int projectID = UAPI.projectID;
    	
    	
    	
    	ProjectInfo projectinfo = new ProjectInfo();
    	ProjectInfo pi = projectinfo.getProjectInfo(projectID);
    	String region = pi.region;
    	
    	/*List<String> */genre = UAPI.genreList;
    	/*List<String> */budget1 = UAPI.budget1List;
    	/*List<String> */budget2 = UAPI.budget2List;
    	review = UAPI.reviewList;
    	
    	
    	
    	if (genre == null || budget1 == null || budget2 == null || review == null) {
    		genre = new ArrayList<String>();
        	budget1 = new ArrayList<String>();
        	budget2 = new ArrayList<String>();
    		genre.add("どら焼き");
    		budget1.add("2000");
    		budget2.add("4500");
    	}
    	
    	
    	
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
    	
    	double r = 0;
    	for (int i = 0; i < review.size();i++) {
    		r += Double.parseDouble(review.get(i));
    	}
    	if (!review.isEmpty() || review != null) {
    		r /= review.size();
    	}
    	else {
    		r = 5.0;
    	}
    	
    	
    	//ret[0] = "つくば市 ハンバーガー";	//仮のクエリ
    	/*ret*/temp[0] = region + " " + findMode(genre) + "&minprice=" + b1 + "&maxprice=" + b2;
    	/*ret*/temp[1] = pi.projectName;	//イベント名
    	/*ret*/temp[2] = "" + pi.dateTime;
		
		this.query = temp[0];
		this.name = temp[1];
		this.date = temp[2];
		
		try {
			list = rvc.search(query, r);
		} catch (GeneralSecurityException | IOException e) {
			e.printStackTrace();
		}
		
	}
}
