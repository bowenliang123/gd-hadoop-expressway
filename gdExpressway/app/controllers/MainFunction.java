package controllers;
import play.*;
import play.mvc.*;
import service.impl.FileControl;
import service.inter.ReturnSystemTime;
import preset.jar.*;
import preset.tool.HadoopJobStatus;

import java.util.*;

import models.User;

import org.apache.hadoop.mapred.JobID;
import org.apache.hadoop.mapred.JobStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.GsonBuilder;
/** 
 * @author itzijing@gmail: 
 * @version 创建时间：2012-10-4 下午10:18:12 
 * 类说明 
 */
public class MainFunction extends Controller{
	/*
	 * 返回Hdfs文件类型（DataFile、 JarFile、ParaFile)
	 */
	public static void fileTypeChosen(String userName)
	{
		render(userName);
	}
	/*
	 * 预置job选择
	 */
	public static void jobChosen(String jobName)
	{
		render();
	}
	/*
	 * 用例
	 */
	public static void example(String example)
	{
		render();
	}
	/*
	 * Hdfs操作选择
	 */
	public static void hdfsOperationChosen(String operationType)
	{
		render();
	}
	public static void hdfsOperationChosenWithTabs(String operationType)
	{
		render();
	}
	public static void getUserInfo(String UserName)
	{
		
		render(UserName);
	}
	public static void getListFile(){
		FileControl user=new FileControl();
		user.setUsername(User.name);
		render();
	}
	public static void jobStatus(String jobStatus)
	{
		render();
	}
	/*
	 * 返回当前用户的各种文件
	 */
	public static void getFile(String file)
	{   
		
		/*if(FileType.equals("DataFile"))
		{
			//以List方式返回指定用户的在HDFS数据文件的列表
		}
		if(FileType.equals("JarFile"))
		{
			//以List方式返回指定用户的在HDFSjar文件的列表
		}
		if(FileType.equals("ParaFile"))
		{
			//以List方式返回指定用户的在HDFS参数文件的列表
		}
		
		List<String> files = null;
		//以List方式返回指定用户的在HDFS数据文件的列表
		render(FileType);*/
		try
		{
		FileControl user = new FileControl();
		//String tarUri="hdfs://localhost:9000/home/"+User.name+"/";
		//String Files[]=user.FileSystemCat(tarUri);
		user.setUsername(User.name);
		String []Files= user.ListStatus(new String[]{""});
		//String Files[]={ "hdfs://localhost:9000/home/amber/input", "hdfs://localhost:9000/home/amber/routeCountOutput"};
		System.out.println(Files.length);
		List<String> files= new ArrayList<String>(Files.length);
		for(int i=0;i<Files.length;i++)
		{
			files.add(Files[i]);
		}
		render(files);
		
		}
		catch (Exception e) {
			System.err.println("Exception error");
		}
	
		render();
	}
	/*
	 * 查看job执行情况页面
	 */
	public static void showStatus()
	{   
//		JSONArray ja=new JSONArray();
		JSONArray ja = HadoopJobStatus.getAllJobList();
		for (int i = 0 ; i< ja.length() ; i++ )
		{
			try {
				JSONObject jo = ja.getJSONObject(i);
				//System.out.println("JobName:"+jo.get("JobName"));
				//System.out.println("Status:"+jo.get("Status"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				System.out.println("print getJSONObject(i) error");
				e.printStackTrace();
			}
		}
//		try{
//		JSONObject jo =new JSONObject();
//		jo.put("JobName", "jobOne");
//		jo.put("Status", "succeed");
//		jo.put("xmlPath","/public/temp/data3.xml");
//		JSONObject jo2=new JSONObject();
//		jo2.put("JobName","jobTwo");
//		jo2.put("Status", "failed");
//		jo2.put("xmlPath","/public/temp/data3.xml");
//		ja.put(jo);
//		ja.put(jo2);
//		}
//		catch (JSONException e) {
//			// TODO Auto-generated catch block
//			System.out.println("print getJSONObject(i) error");
//			e.printStackTrace();
//		}
        //String toJson=ja.toString();//测试用的
//		getJobIDByJobName(jobClient, jobStatus, jobName_str)
//		renderText(js.getRunState());
		System.out.println(ja.toString());
		renderJSON(ja.toString());
	}
	
}
