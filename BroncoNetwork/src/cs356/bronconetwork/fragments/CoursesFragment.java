package cs356.bronconetwork.fragments;

import cs356.bronconetwork.R;
import cs356.bronconetwork.R.id;
import cs356.bronconetwork.R.layout;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
@SuppressLint("ValidFragment")
public class CoursesFragment extends Fragment implements NetworkFragment {
	
	private String name = "Courses";
	private int icon = R.drawable.icon_courses;
	private Spinner spnr;
	private Context c;
	private String[] major = {
      "ABM",
      "ACC",
      "AG",
      "AGS",
      "AHS",
      "AMM",
      "ANT",
      "ARC",
      "ARO",
      "ART",
      "AVS",
      "BHS",
      "BIO",
      "BOT",
      "BUS",
      "CE",
      "CEU",
      "CHE",
      "CHM",
      "CIS",
      "CLS",
      "COM",
      "CPU",
      "CS",
      "CSA",
      "DAN",
      "EBZ",
      "EC",
      "ECE",
      "EDD",
      "EDS",
      "EDU",
      "EGR",
      "ELI",
      "ENG",
      "ENV",
      "ETC",
      "ETE",
      "ETM",
      "ETP",
      "ETT",
      "EWS",
      "FL",
      "FMA",
      "FN",
      "FRL",
      "FST",
      "GBA",
      "GED",
      "GEO",
      "GSC",
      "HPS",
      "HRT",
      "HST",
      "IA",
      "IBM",
      "IE",
      "IGE",
      "IME",
      "INA",
      "IPC",
      "KIN",
      "LA",
      "LIS",
      "LRC",
      "LS",
      "MAE",
      "MAT",
      "ME",
      "MFE",
      "MHR",
      "MIC",
      "MPA",
      "MSL",
      "MTE",
      "MU",
      "NSE",
      "PHL",
      "PHY",
      "PLS",
      "PLT",
      "PSY",
      "RS",
      "SCI",
      "SME",
      "SOC",
      "SPN",
      "SSC",
      "STA",
      "STS",
      "SW",
      "TED",
      "TH",
      "TOM",
      "UNDC",
      "URP",
      "ZOO"
  };
  
  
  public CoursesFragment(Context c) {
	  this.c = c;
  }
  
  
  @Override 
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	  return inflater.inflate(R.layout.courses, container, false);
  }

  


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		spnr = (Spinner) getView().findViewById(R.id.major_);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_item, major);
		spnr.setAdapter(adapter);
		spnr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
	        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				int position = spnr.getSelectedItemPosition();
	            Toast.makeText(c.getApplicationContext(),"You have selected "+major[position],Toast.LENGTH_LONG).show();
	        }
	        @Override
	        public void onNothingSelected(AdapterView<?> arg0) {
	               
	        }
		});
	}
	
	public String getName() {
		return name;
	}
  
	public int getDrawableId() {
		return icon;
	}
  
}