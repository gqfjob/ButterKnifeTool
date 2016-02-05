package util;

public class SpellUtil {
	public static String SpellUnit(String unit,String id){
		
		StringBuffer parseText=new StringBuffer();
		id=id.replace("@+id/", "");
		parseText.append("@Bind(R.id."+id+")"+" ");
		parseText.append(unit+" "+id+";");
		return parseText.toString();
	}
}
