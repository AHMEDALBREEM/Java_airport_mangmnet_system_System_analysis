package ps.albreem.airman.ps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.AdapterView;
import android.graphics.Typeface;
import okhttp3.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class DyUpdateMainViewActivity extends AppCompatActivity {
	
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private boolean dateandtime_bool = false;
	private boolean place_bool = false;
	private String temp = "";
	private double position = 0;
	private HashMap<String, Object> map = new HashMap<>();
	
	private ArrayList<HashMap<String, Object>> lsm = new ArrayList<>();
	
	private LinearLayout update_main_form;
	private ListView listview1;
	private LinearLayout view_in_click;
	private LinearLayout linear5;
	private LinearLayout update_layer;
	private LinearLayout Deletion_layer;
	private Button button3;
	private TextView textview1;
	private LinearLayout selection_option;
	private EditText id;
	private Button edit_the_text;
	private Button button2;
	private LinearLayout linear8;
	private Button button1;
	private TextView textview2;
	private Button confirm_deletion;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private LinearLayout linear6;
	private LinearLayout linear7;
	private LinearLayout linear1;
	private TextView textview3;
	private TextView Duty_time_date;
	private TextView textview4;
	private TextView du_id;
	private TextView textview6;
	private TextView emploee;
	private TextView textview5;
	private TextView Duty_place;
	
	private DatabaseReference db = _firebase.getReference("db_emp_duties");
	private ChildEventListener _db_child_listener;
	private Intent j = new Intent();
	private Intent ns = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.dy_update_main_view);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		update_main_form = findViewById(R.id.update_main_form);
		listview1 = findViewById(R.id.listview1);
		view_in_click = findViewById(R.id.view_in_click);
		linear5 = findViewById(R.id.linear5);
		update_layer = findViewById(R.id.update_layer);
		Deletion_layer = findViewById(R.id.Deletion_layer);
		button3 = findViewById(R.id.button3);
		textview1 = findViewById(R.id.textview1);
		selection_option = findViewById(R.id.selection_option);
		id = findViewById(R.id.id);
		edit_the_text = findViewById(R.id.edit_the_text);
		button2 = findViewById(R.id.button2);
		linear8 = findViewById(R.id.linear8);
		button1 = findViewById(R.id.button1);
		textview2 = findViewById(R.id.textview2);
		confirm_deletion = findViewById(R.id.confirm_deletion);
		linear3 = findViewById(R.id.linear3);
		linear2 = findViewById(R.id.linear2);
		linear6 = findViewById(R.id.linear6);
		linear7 = findViewById(R.id.linear7);
		linear1 = findViewById(R.id.linear1);
		textview3 = findViewById(R.id.textview3);
		Duty_time_date = findViewById(R.id.Duty_time_date);
		textview4 = findViewById(R.id.textview4);
		du_id = findViewById(R.id.du_id);
		textview6 = findViewById(R.id.textview6);
		emploee = findViewById(R.id.emploee);
		textview5 = findViewById(R.id.textview5);
		Duty_place = findViewById(R.id.Duty_place);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				listview1.setVisibility(View.GONE);
				view_in_click.setVisibility(View.VISIBLE);
				Deletion_layer.setVisibility(View.GONE);
				update_layer.setVisibility(View.VISIBLE);
				button3.setVisibility(View.VISIBLE);
				map = new HashMap<>();
				map.put("duty_id", lsm.get((int)_position).get("duty_id").toString());
				map.put("duty_place", lsm.get((int)_position).get("duty_place").toString());
				map.put("duty_date_time", lsm.get((int)_position).get("duty_date_time").toString());
				map.put("duty_employee", lsm.get((int)_position).get("duty_employee").toString());
			}
		});
		
		button3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				db.child(map.get("duty_employee").toString()).removeValue();
				ns.setClass(getApplicationContext(), TasksformanagerActivity.class);
					ns.putExtra("email", getIntent().getStringExtra("email"));
					startActivity(ns);
					finish();
			}
		});
		
		edit_the_text.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				listview1.setVisibility(View.GONE);
				update_layer.setVisibility(View.GONE);
				Deletion_layer.setVisibility(View.VISIBLE);
				if (dateandtime_bool) {
					map.put("duty_date_time", id.getText().toString());
				}
				if (place_bool) {
					map.put("duty_place", id.getText().toString());
				}
				Duty_place.setText(map.get("duty_place").toString());
				emploee.setText(map.get("duty_employee").toString().concat(" @iut-cse21.ps"));
				du_id.setText(map.get("duty_id").toString());
				Duty_time_date.setText(map.get("duty_date_time").toString());
			}
		});
		
		button2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dateandtime_bool = false;
				place_bool = true;
			}
		});
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				dateandtime_bool = true;
				place_bool = false;
			}
		});
		
		confirm_deletion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (!emploee.getText().toString().equals("")) {
					db.child(map.get("duty_employee").toString()).removeValue();
					db.child(map.get("duty_employee").toString()).updateChildren(map);
					map.clear();
					j.setClass(getApplicationContext(), TasksformanagerActivity.class);
					j.putExtra("email", getIntent().getStringExtra("email"));
					startActivity(j);
					finish();
				}
			}
		});
		
		Duty_time_date.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				
			}
		});
		
		emploee.setOnLongClickListener(new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View _view) {
				
				return true;
			}
		});
		
		_db_child_listener = new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildChanged(DataSnapshot _param1, String _param2) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onChildMoved(DataSnapshot _param1, String _param2) {
				
			}
			
			@Override
			public void onChildRemoved(DataSnapshot _param1) {
				GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
				final String _childKey = _param1.getKey();
				final HashMap<String, Object> _childValue = _param1.getValue(_ind);
				
			}
			
			@Override
			public void onCancelled(DatabaseError _param1) {
				final int _errorCode = _param1.getCode();
				final String _errorMessage = _param1.getMessage();
				
			}
		};
		db.addChildEventListener(_db_child_listener);
	}
	
	private void initializeLogic() {
		view_in_click.setVisibility(View.GONE);
		db.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				lsm = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						lsm.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				listview1.setAdapter(new Listview1Adapter(lsm));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#0288D1")); }
		textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		edit_the_text.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		button2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		button1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		confirm_deletion.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		Duty_time_date.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		du_id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview6.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		emploee.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		Duty_place.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		button2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		confirm_deletion.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		button3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
		button3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
		edit_the_text.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)20, 0xFF0288D1));
	}
	
	@Override
	public void onBackPressed() {
		j.setClass(getApplicationContext(), TasksformanagerActivity.class);
		j.putExtra("email", getIntent().getStringExtra("email"));
		startActivity(j);
		finish();
	}
	public class Listview1Adapter extends BaseAdapter {
		
		ArrayList<HashMap<String, Object>> _data;
		
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.empview, null);
			}
			
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final LinearLayout linear6 = _view.findViewById(R.id.linear6);
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			final TextView Duty_time_date = _view.findViewById(R.id.Duty_time_date);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView du_id = _view.findViewById(R.id.du_id);
			final TextView textview5 = _view.findViewById(R.id.textview5);
			final TextView employee_email = _view.findViewById(R.id.employee_email);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView Duty_place = _view.findViewById(R.id.Duty_place);
			
			du_id.setText(lsm.get((int)_position).get("duty_id").toString());
			Duty_time_date.setText(lsm.get((int)_position).get("duty_date_time").toString());
			Duty_place.setText(lsm.get((int)_position).get("duty_place").toString());
			employee_email.setText(lsm.get((int)_position).get("duty_employee").toString().concat(" @iut-cse21.ps"));
			textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			Duty_time_date.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			du_id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			textview5.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			employee_email.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			Duty_place.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
			Animation animation; animation = AnimationUtils.loadAnimation( getApplicationContext(), android.R.anim.slide_in_left ); animation.setDuration(300); update_main_form.startAnimation(animation); animation = null;
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}