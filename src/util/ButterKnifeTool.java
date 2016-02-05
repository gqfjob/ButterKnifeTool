package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ButterKnifeTool {
	public static void main(String[] args) {
		ButterKnifeTool clx = new ButterKnifeTool();
		try {
			String local = "/layout";
			String out = "/output";
			String input = clx.getAbselutPath(local);
			String output = clx.getAbselutPath(out);
			File root = new File(input);
			try {
				List<String> paths = showAllFiles(root);
				int size = paths.size();
				if (size > 0) {
					for (int i = 0; i < size; i++) {
						String fileUrl = paths.get(i);
						Map<String, String> map = LayoutUtil.ParseLayout(fileUrl);
						String parseText = "";
						for (Map.Entry<String, String> entry : map.entrySet()) {
							parseText += SpellUtil.SpellUnit(entry.getValue(), entry.getKey()) + "\n";
						}
						//System.out.println(parseText);
						// write file
						String outUrl = output + fileUrl.substring(fileUrl.lastIndexOf("/"));
						//System.out.println(outUrl);
						writeToFile(parseText, outUrl);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAbselutPath(String local) throws IOException {
		File directory = new File("");
		String courseFile = directory.getCanonicalPath();
		return courseFile + local;
	}

	final static List<String> showAllFiles(File dir) throws Exception {
		File[] fs = dir.listFiles();
		ArrayList<String> paths = new ArrayList<>();
		for (int i = 0; i < fs.length; i++) {
			String path = fs[i].getAbsolutePath();
			paths.add(path);
			//System.out.println(path);
			if (fs[i].isDirectory()) {
				try {
					showAllFiles(fs[i]);
				} catch (Exception e) {
				}
			}
		}
		return paths;
	}

	public static void writeToFile(String data, String url) throws Exception {

		File file = new File(url);

		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data);
		bw.close();
		System.out.println("Done with :"+ url);
	}
}
