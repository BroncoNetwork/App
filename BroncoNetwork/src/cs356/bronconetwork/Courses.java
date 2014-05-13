package cs356.bronconetwork;

import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
public class Courses extends Activity {
  Spinner spnr;
  String[] major = {
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
  
  
  @Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    spnr = (Spinner)findViewById(R.id.major_);
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(
        this, android.R.layout.simple_spinner_item, major);
    spnr.setAdapter(adapter);
    spnr.setOnItemSelectedListener(
              new AdapterView.OnItemSelectedListener() {
                  @Override
                  public void onItemSelected(AdapterView<?> arg0, View arg1,
                          int arg2, long arg3) {
                    int position = spnr.getSelectedItemPosition();
                    Toast.makeText(getApplicationContext(),"You have selected "+major[+position],Toast.LENGTH_LONG).show();
                      // TODO Auto-generated method stub
                  }
                  @Override
                  public void onNothingSelected(AdapterView<?> arg0) {
                      // TODO Auto-generated method stub
                  }
              }
          );
  }
}