package cs356.bronconetwork.fragments;

import java.util.ArrayList;

import cs356.bronconetwork.MainEntry;
import cs356.bronconetwork.Post;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

class LoadProfile extends AsyncTask<String, Integer, String> {

	private MainEntry mainEntry;
	private ProgressDialog dialog;
	private AlertDialog.Builder builder;
	private LayoutInflater inflater;
	private View profileLayout;
	private ListView courseList;
	private ArrayList<String> courses = new ArrayList<String>();
	
	@Override
	protected String doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
