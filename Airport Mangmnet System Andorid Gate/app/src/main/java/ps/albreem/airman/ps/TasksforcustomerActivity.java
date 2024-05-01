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
import android.widget.ImageView;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.view.View;
import android.graphics.Typeface;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.DateTimeFormatter;

public class TasksforcustomerActivity extends AppCompatActivity {
	
	private Timer _timer = new Timer();
	private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
	
	private HashMap<String, Object> m = new HashMap<>();
	private String jsonData = "";
	private double hj = 0;
	private double s = 0;
	private double var = 0;
	private double length = 0;
	private double vara = 0;
	private boolean enable = false;
	private boolean enable_timer = false;
	
	private ArrayList<HashMap<String, Object>> list = new ArrayList<>();
	private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
	private ArrayList<Double> ass = new ArrayList<>();
	private ArrayList<String> ads = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear3;
	private LinearLayout linear2;
	private LinearLayout linear4;
	private LinearLayout linear5;
	private ListView listview1;
	private ImageView imageview5;
	private EditText edittext1;
	private ImageView imageview6;
	private ImageView imageview1;
	private ImageView imageview2;
	private ImageView imageview4;
	private ImageView imageview3;
	
	private Intent mover = new Intent();
	private DatabaseReference viewer = _firebase.getReference("db/db_tickets");
	private ChildEventListener _viewer_child_listener;
	private SharedPreferences a;
	private RequestNetwork test;
	private RequestNetwork.RequestListener _test_request_listener;
	private TimerTask g;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.tasksforcustomer);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear3 = findViewById(R.id.linear3);
		linear2 = findViewById(R.id.linear2);
		linear4 = findViewById(R.id.linear4);
		linear5 = findViewById(R.id.linear5);
		listview1 = findViewById(R.id.listview1);
		imageview5 = findViewById(R.id.imageview5);
		edittext1 = findViewById(R.id.edittext1);
		imageview6 = findViewById(R.id.imageview6);
		imageview1 = findViewById(R.id.imageview1);
		imageview2 = findViewById(R.id.imageview2);
		imageview4 = findViewById(R.id.imageview4);
		imageview3 = findViewById(R.id.imageview3);
		a = getSharedPreferences("a", Activity.MODE_PRIVATE);
		test = new RequestNetwork(this);
		
		imageview6.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if ("".equals(edittext1.getText().toString())) {
					if ("1".equals(a.getString("ab", ""))) {
						a.edit().putString("ab", "a").commit();
						var = 0;
						for(int _repeat10 = 0; _repeat10 < (int)(list.size()); _repeat10++) {
							if (list.get((int)var).get("from").toString().equals(edittext1.getText().toString())) {
								
							}
							else {
								if (list.get((int)var).get("to").toString().equals(edittext1.getText().toString())) {
									
								}
								else {
									if (list.get((int)var).get("id").toString().equals(edittext1.getText().toString())) {
										
									}
									else {
										list.remove((int)(var));
										g = new TimerTask() {
											@Override
											public void run() {
												runOnUiThread(new Runnable() {
													@Override
													public void run() {
														viewer.addListenerForSingleValueEvent(new ValueEventListener() {
															@Override
															public void onDataChange(DataSnapshot _dataSnapshot) {
																list = new ArrayList<>();
																try {
																	GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
																	for (DataSnapshot _data : _dataSnapshot.getChildren()) {
																		HashMap<String, Object> _map = _data.getValue(_ind);
																		list.add(_map);
																	}
																}
																catch (Exception _e) {
																	_e.printStackTrace();
																}
																listview1.setAdapter(new Listview1Adapter(list));
																((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
															}
															@Override
															public void onCancelled(DatabaseError _databaseError) {
															}
														});
														a.edit().putString("ab", "1").commit();
													}
												});
											}
										};
										_timer.schedule(g, (int)(10000));
									}
								}
							}
							var++;
						}
					}
				}
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mover.setClass(getApplicationContext(), BuyTkActivity.class);
				mover.putExtra("key", "buy");
				mover.putExtra("email", getIntent().getStringExtra("email"));
				startActivity(mover);
				finish();
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mover.setClass(getApplicationContext(), BuyTkActivity.class);
				mover.putExtra("key", "return");
				mover.putExtra("email", getIntent().getStringExtra("email"));
				startActivity(mover);
				finish();
			}
		});
		
		imageview4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mover.setClass(getApplicationContext(), InformationActivity.class);
				mover.putExtra("email", getIntent().getStringExtra("email"));
				startActivity(mover);
				finish();
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				mover.setClass(getApplicationContext(), LoginActivity.class);
				mover.putExtra("email", getIntent().getStringExtra("email"));
				mover.putExtra("key", "0");
				startActivity(mover);
				finish();
			}
		});
		
		_viewer_child_listener = new ChildEventListener() {
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
		viewer.addChildEventListener(_viewer_child_listener);
		
		_test_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				m = new Gson().fromJson(_response, new TypeToken<HashMap<String, Object>>(){}.getType());
				if (m.get("country").toString().equals("Bangladesh")) {
					imageview5.setVisibility(View.VISIBLE);
					imageview5.setImageResource(R.drawable.ad_banr);
				}
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				
			}
		};
	}
	
	private void initializeLogic() {
		test.startRequestNetwork(RequestNetworkController.GET, "https://api.myip.com", "ip", _test_request_listener);
		imageview5.setVisibility(View.GONE);
		viewer.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot _dataSnapshot) {
				list = new ArrayList<>();
				try {
					GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
					for (DataSnapshot _data : _dataSnapshot.getChildren()) {
						HashMap<String, Object> _map = _data.getValue(_ind);
						list.add(_map);
					}
				}
				catch (Exception _e) {
					_e.printStackTrace();
				}
				listview1.setAdapter(new Listview1Adapter(list));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			@Override
			public void onCancelled(DatabaseError _databaseError) {
			}
		});
		if ("1".equals(a.getString("a", ""))) {
			imageview5.setVisibility(View.VISIBLE);
			imageview5.setImageResource(R.drawable.ads_banner_2);
		}
		else {
			if ("2".equals(a.getString("a", ""))) {
				imageview5.setVisibility(View.VISIBLE);
				imageview5.setImageResource(R.drawable.ads_banner_1);
			}
			else {
				
			}
		}
		edittext1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 1);
		if (Build.VERSION.SDK_INT >= 21) { Window
			w = this.getWindow();
			w.setNavigationBarColor(Color.parseColor("#0288D1")); }
		a.edit().putString("ab", "1").commit();
		length = 0;
	}
	
	@Override
	public void onBackPressed() {
		length++;
		g = new TimerTask() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						if (length > 1) {
							mover.setClass(getApplicationContext(), LoginActivity.class);
							mover.putExtra("email", getIntent().getStringExtra("email"));
							mover.putExtra("key", "0");
							startActivity(mover);
							ads.clear();
							finish();
						}
						else {
							length = 0;
						}
					}
				});
			}
		};
		_timer.schedule(g, (int)(500));
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
				_view = _inflater.inflate(R.layout.custview, null);
			}
			
			final LinearLayout linear1 = _view.findViewById(R.id.linear1);
			final LinearLayout linear2 = _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = _view.findViewById(R.id.linear4);
			final LinearLayout linear5 = _view.findViewById(R.id.linear5);
			final TextView textview1 = _view.findViewById(R.id.textview1);
			final TextView tk_from = _view.findViewById(R.id.tk_from);
			final TextView textview2 = _view.findViewById(R.id.textview2);
			final TextView tk_to = _view.findViewById(R.id.tk_to);
			final TextView textview3 = _view.findViewById(R.id.textview3);
			final TextView tk_id = _view.findViewById(R.id.tk_id);
			final TextView textview4 = _view.findViewById(R.id.textview4);
			final TextView tk_price = _view.findViewById(R.id.tk_price);
			
			LocalDate today = LocalDate.now();
			
			String dateString = list.get((int)_position).get("date").toString(); 
			
			String dateString2 = list.get((int)_position).get("date_d").toString(); 
			
			int hashedDate_end = Integer.valueOf(dateString); 
			
			int hashedDate_start = Integer.valueOf(dateString2); 
			try {
				    int hashedToday = today.toString().hashCode();
				
				    if (hashedDate_end > hashedToday &&(hashedDate_start < hashedToday)) {
					        tk_from.setText(list.get((int)_position).get("from").toString());
					        tk_to.setText(list.get((int)_position).get("to").toString());
					        tk_id.setText(list.get((int)_position).get("id").toString());
					        tk_price.setText(list.get((int)_position).get("price").toString());
					  
					    } else {
					        // Date is after today
					        tk_from.setText(list.get((int)_position).get("from").toString());
					        tk_to.setText(list.get((int)_position).get("to").toString());
					        tk_id.setText("Not Available");
					        tk_price.setText(list.get((int)_position).get("price").toString());
					    }
				          textview1.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
				        tk_from.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
				        textview2.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
				        tk_to.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
				        textview3.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
				        tk_id.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
				        textview4.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
				        tk_price.setTypeface(Typeface.createFromAsset(getAssets(),"fonts/asd.ttf"), 0);
				        Animation animation;
				        animation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left);
				        animation.setDuration(300);
				        linear1.startAnimation(animation);
				        animation = null;
			} catch (DateTimeParseException e) {
				    System.exit(1);
			}
			
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