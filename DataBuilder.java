import java.io.*;
import java.util.*;

class DataBuilder
{
	public static void main(String[] args) throws Exception
	{
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
		String line;
		br.readLine();
		ArrayList<String[]> li = new ArrayList<String[]>();
		while ((line = br.readLine()) != null)
		{
			String[] strs = line.split(",");
			if (line.matches("^(-|[0-9]|,|\\.)+$") && strs.length == 5) // hard code
			{
				li.add(strs);
			}
		}

		for (int i = 0; i < li.size(); i++)
			for (int j = 0; j < li.size(); j++)
			if (i != j)
			{
				String[] i_data = li.get(i);
				int i_hip_number = Integer.parseInt(i_data[0]);
				double i_ra_deg = Double.parseDouble(i_data[1]);
				double i_dec_deg = Double.parseDouble(i_data[2]);
				double i_light_year = Double.parseDouble(i_data[3]);
				double i_vmag = Double.parseDouble(i_data[4]);

				String[] j_data = li.get(j);
				int j_hip_number = Integer.parseInt(j_data[0]);
				double j_ra_deg = Double.parseDouble(j_data[1]);
				double j_dec_deg = Double.parseDouble(j_data[2]);
				double j_light_year = Double.parseDouble(j_data[3]);
				double j_vmag = Double.parseDouble(j_data[4]);

				double angle = Math.atan(Math.sqrt(Math.pow(Math.tan(Math.toRadians(i_ra_deg  - j_ra_deg )), 2)
								   + Math.pow(Math.tan(Math.toRadians(i_dec_deg - j_dec_deg)), 2)));
				double distance = Math.sqrt(Math.pow(i_light_year, 2) + Math.pow(j_light_year, 2)
					       	- 2 * i_light_year * j_light_year * Math.cos(angle));
				System.out.println(i_hip_number + "," + j_hip_number + "," + (int)(distance+0.5));
			}		
	}
}
